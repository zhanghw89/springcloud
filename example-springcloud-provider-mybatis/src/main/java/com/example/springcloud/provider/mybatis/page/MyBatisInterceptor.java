package com.example.springcloud.provider.mybatis.page;

import com.example.springcloud.provider.mybatis.NoNeedOffice;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * Created by shiwen on 2017/9/10.
 * 自定义mybatis拦截器
 * 1.可以选择开启关闭  ,在mybatisConfig中可以开启或关闭。默认关闭
 * 2.如果有@NeedOffice注解则关闭，没有注解则默认开启
 * 3.如果用户已经注入了officeId怎么办？如果用户已经注入则，默认使用用户的officeID
 * <p>
 * 4.复杂sql如何处理？多个where条件如何处理？
 * 5.如果sql中没有where条件如何处理
 * <p>
 * #目前最简单的实现方式：1.判断是否有@NoOffice注解
 */

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})
})
public class MyBatisInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyBatisInterceptor.class);

    /**
     * 修改sql，在sql中增加officeId通用业务字段
     *
     * @param invocation
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {

        if (invocation.getTarget() instanceof RoutingStatementHandler) {

            //1.获取原始sql语句
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
            StatementHandler delegate = (StatementHandler) ReflectHelper.getFieldValue(statementHandler, "delegate");

            //2.根据方法名反射，判断是否包含@NoNeedOffice注解，来决定是否需要修改sql语句
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getFieldValue(delegate, "mappedStatement");
            String sqlId = mappedStatement.getId();

            //如果没有注解则在sql中增加officeId
            if (!hasNoNeedOfficeAnnotation(sqlId)) {
                //3.修改原始sql语句
                modifySql(delegate.getBoundSql());
            }

        }

        return invocation.proceed();
    }

    /**
     * @param sqlId 方法全路径：类路径+方法名，例如：com.example.Demo.getName
     * @return 是否有 NoNeedOffice注解
     */
    private boolean hasNoNeedOfficeAnnotation(String sqlId) {
        //1.得到类路径和方法路径
        int lastIndexOfDot = sqlId.lastIndexOf(".");
        String className = sqlId.substring(0, lastIndexOfDot);
        String methodName = sqlId.substring(lastIndexOfDot + 1);

        //2.得到类上的注解
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Annotation[] classAnnotations = clazz.getAnnotations();

        if (containsNoNeedOffice(classAnnotations)) {
            return true;
        }


        //3.得到方法上的注解
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (methodName.equals(name)) {
                Annotation[] methodAnnotations = method.getAnnotations();
                if (containsNoNeedOffice(methodAnnotations)) {
                    return true;
                }
            }
        }

        return false;


    }

    /**
     * 判断注解中是否包含 NoNeedOffice的注解
     * @param classAnnotations
     * @return
     */
    private boolean containsNoNeedOffice(Annotation[] classAnnotations) {
        for (Annotation annotation : classAnnotations) {
            if (annotation instanceof NoNeedOffice) {
                return true;
            }
        }
        return false;
    }


    /**
     *  TODO 此处的逻辑很重要
     * 修改原始sql语句，在sql中增减officeId
     *
     * @param boundSql BoundSql 对象
     */
    private void modifySql(BoundSql boundSql) {
        String officeId = getOfficeId();
        String sql = boundSql.getSql();//获取原来的sql
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        //判断sql中是否包含where条件，是否包含多个where
        ReflectHelper.setFieldValue(boundSql, "sql", sql);
    }


    /**
     * 获取当前用户的officeId
     *
     * @return officeId
     */
    private String getOfficeId() {
        return "123";
    }


    @Override
    public Object plugin(Object o) {
        logger.info(">>>>>>>>>>>>>>>>>plugin");
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        logger.info(">>>>>>>>>>>>>>>>>setProperties");
    }
}
