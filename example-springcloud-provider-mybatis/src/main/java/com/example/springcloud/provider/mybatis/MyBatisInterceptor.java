package com.example.springcloud.provider.mybatis;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by shiwen on 2017/9/10.
 * 自定义mybatis拦截器
 * 1.可以选择开启关闭
 * 2.如果有注解则关闭，没有注解则默认开启
 * 3.如果用户已经入住了officeId怎么办？如果用户已经注入officeID则覆盖
 * 4.复杂sql如何处理？多个where条件如何处理？
 * 5.如果sql中没有where条件如何处理
 * <p>
 * #目前最简单的实现方式：1.判断是否有@NoOffice注解
 */

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})
})
public class MyBatisInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyBatisInterceptor.class);

    private String officeId = "123";


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info(">>>>>>>>>>>>>>>>intercept");
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String sqlId = mappedStatement.getId();//获取sqlid
        //TODO 注解解析,存在内存中，避免每次解析
        int index = sqlId.lastIndexOf(".");


        String fullClassName = sqlId.substring(0, index);
        String shortethodName = sqlId.substring(index + 1);

        //TODO 有没有可能是内部类？
        Class<?> clazz = Class.forName(fullClassName);
        Annotation[] annotations = clazz.getAnnotations();

        //需要传入参数类型
//        Method method = clazz.getMethod(shortethodName);
//
//        Annotation[] methodAnnotations = method.getAnnotations();
//        Arrays.binarySearch(annotations)
//        String namespace = sqlId.substring(0, sqlId.indexOf('.'));
//        Executor exe = (Executor) invocation.getTarget();


        String resource = mappedStatement.getResource();
        ParameterMap parameterMap = mappedStatement.getParameterMap();
        Cache cache = mappedStatement.getCache();
        String databaseId = mappedStatement.getDatabaseId();
        String[] keyColumns = mappedStatement.getKeyColumns();
        String[] keyProperties = mappedStatement.getKeyProperties();
        KeyGenerator keyGenerator = mappedStatement.getKeyGenerator();

        MapperMethod.ParamMap mapperMethod = (MapperMethod.ParamMap) invocation.getArgs()[1];
        mapperMethod.put("office_id", officeId);
        mapperMethod.put("param2", officeId);


        String methodName = invocation.getMethod().getName();
//        Class target = invocation.getTarget().getClass().;
//        Method method = invocation.getMethod();
//
//        Annotation[] classAnnotations = target.getAnnotations();
//        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();


        if (methodName.equals("query") || methodName.equals("update")) {
            Object parameter = invocation.getArgs()[1];
            if (parameter instanceof Office) {
                ((Office) parameter).setOfficeId(officeId);
            }

//            ReflectHelper.setFieldValue();

            //通过反射设置sql的值
            SqlSource sqlSource = mappedStatement.getSqlSource();
            BoundSql boundSql = mappedStatement.getBoundSql(sqlId);
            String sql = boundSql.getSql();


        }


        return invocation.proceed();
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
