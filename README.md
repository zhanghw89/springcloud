# springcloud
springcloud's examples


注意：mybits的注解扫描必须是

@MapperScan(basePackages = "com.example.springcloud.provider.*.dao")
只扫描dao层的接口，如果扫描到@MapperScan(basePackages = "com.example.springcloud.provider") 那么所有的service都被注入成mapper，那么service就会注入失败
<br/>
spring和mybatis的包扫描要分开。<br/>
另外使用Autowired注入时service的名字是需要和实现类的名字相同的。注意autowired和resource的区别，注入的几种类型。
