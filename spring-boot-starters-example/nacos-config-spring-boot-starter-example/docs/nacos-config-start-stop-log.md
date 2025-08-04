

配置中心启动关闭日志剖析
======
> 从日志信息线索了解配置中心的关键核心操作流程


## 1.启动日志
```shell

#NacosConfigEnvironmentProcessor
#预加载日志配置开启
[Nacos Config Boot] : The preload log configuration is enabled
#NacosLoggingListener
#nacos配置属性集构建
2023-07-23 16:30:56.208  INFO 2128 --- [           main] c.a.b.n.c.u.NacosConfigPropertiesUtils   : nacosConfigProperties : NacosConfigProperties{serverAddr='127.0.0.1:8848', contextPath='null', encode='null', endpoint='null', namespace='nacos-env-dev', accessKey='null', secretKey='null', ramRoleName='null', autoRefresh=true, dataId='nacos-config-spring-boot-starter-example', dataIds='null', group='DEVELOP', type=YAML, maxRetry='10', configLongPollTimeout='60000', configRetryTime='3000', enableRemoteSyncConfig=true, extConfig=[], bootstrap=Bootstrap{enable=true, logEnable=true}}

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.18)

#NacosConfigApplicationContextInitializer
#nacos配置属性集构建
2023-07-23 16:30:56.264  INFO 2128 --- [           main] c.a.b.n.c.u.NacosConfigPropertiesUtils   : nacosConfigProperties : NacosConfigProperties{serverAddr='127.0.0.1:8848', contextPath='null', encode='null', endpoint='null', namespace='nacos-env-dev', accessKey='null', secretKey='null', ramRoleName='null', autoRefresh=true, dataId='nacos-config-spring-boot-starter-example', dataIds='null', group='DEVELOP', type=YAML, maxRetry='10', configLongPollTimeout='60000', configRetryTime='3000', enableRemoteSyncConfig=true, extConfig=[], bootstrap=Bootstrap{enable=true, logEnable=true}}
#应用程序启动入口(Java版本、本机IP、进程ID、应用目录、操作系统用户名称)
2023-07-23 16:30:56.374  INFO 2128 --- [           main] c.s.b.n.example.NacosConfigApplication   : Starting NacosConfigApplication using Java 11.0.19 on 192.168.0.101 with PID 2128 (/Users/feimen/Documents/lhg/workspace/spring-boot-extend/spring-boot-starters-example/nacos-config-spring-boot-starter-example/target/classes started by feimen in /Users/feimen/Documents/lhg/workspace/spring-boot-extend)
2023-07-23 16:30:56.375  INFO 2128 --- [           main] c.s.b.n.example.NacosConfigApplication   : The following 1 profile is active: "dev"
#加载客户端身份验证服务，客户端身份验证插件管理器
2023-07-23 16:30:56.726  INFO 2128 --- [           main] c.a.n.p.a.s.c.ClientAuthPluginManager    : [ClientAuthPluginManager] Load ClientAuthService com.alibaba.nacos.client.auth.impl.NacosClientAuthServiceImpl success.
2023-07-23 16:30:56.726  INFO 2128 --- [           main] c.a.n.p.a.s.c.ClientAuthPluginManager    : [ClientAuthPluginManager] Load ClientAuthService com.alibaba.nacos.client.auth.ram.RamClientAuthServiceImpl success.
#nacos配置端点身份命名包含无效字符
2023-07-23 16:30:56.923  WARN 2128 --- [           main] o.s.boot.actuate.endpoint.EndpointId     : Endpoint ID 'nacos-config' contains invalid characters, please migrate to a valid format.
#tomcat启动，web服务器、标准服务、标准引擎、嵌入的web应用上下文
2023-07-23 16:30:57.321  INFO 2128 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-07-23 16:30:57.327  INFO 2128 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-07-23 16:30:57.327  INFO 2128 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.75]
2023-07-23 16:30:57.384  INFO 2128 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
#根web应用上下文初始化完成
2023-07-23 16:30:57.384  INFO 2128 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 982 ms
#应用bean组件实例化
2023-07-23 16:30:57.563  INFO 2128 --- [           main] c.s.b.n.e.c.ExampleNacosConfigListener   : create ExampleNacosConfigListener
2023-07-23 16:30:57.567  INFO 2128 --- [           main] .e.c.ExampleNacosConfigurationProperties : create ExampleNacosConfigurationProperties
#nacos配置元数据事件日志记录，ExampleNacosConfigurationProperties，配置属性集
2023-07-23 16:30:57.622  INFO 2128 --- [           main] .LoggingNacosConfigMetadataEventListener : Nacos Config Metadata : dataId='nacos-config-spring-boot-starter-example', groupId='DEVELOP', beanName='exampleNacosConfigurationProperties', bean='ExampleNacosConfigurationProperties(dynamicConfig=yml-demo-dev-dynamic, configNamespace=nacos-config-dev)', beanType='class com.spring.boot.nacos.example.config.ExampleNacosConfigurationProperties', annotatedElement='class com.spring.boot.nacos.example.config.ExampleNacosConfigurationProperties', xmlResource='null', nacosProperties='{encode=UTF-8, maxRetry=10, configRetryTime=3000, serverAddr=127.0.0.1:8848, enableRemoteSyncConfig=true, namespace=nacos-env-dev, configLongPollTimeout=60000}', nacosPropertiesAttributes='{accessKey=${nacos.access-key:}, clusterName=${nacos.cluster-name:}, configLongPollTimeout=${nacos.configLongPollTimeout:}, configRetryTime=${nacos.configRetryTime:}, contextPath=${nacos.context-path:}, enableRemoteSyncConfig=${nacos.enableRemoteSyncConfig:}, encode=${nacos.encode:UTF-8}, endpoint=${nacos.endpoint:}, maxRetry=${nacos.maxRetry:}, namespace=${nacos.namespace:}, password=${nacos.password:}, secretKey=${nacos.secret-key:}, serverAddr=${nacos.server-addr:}, username=${nacos.username:}}', source='@com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties(ignoreUnknownFields=true, autoRefreshed=true, exceptionIfInvalid=true, ignoreInvalidFields=false, ignoreNestedProperties=false, prefix="", groupId="${nacos.config.group:}", type=YAML, properties=@com.alibaba.nacos.api.annotation.NacosProperties(encode="${nacos.encode:UTF-8}", secretKey="${nacos.secret-key:}", contextPath="${nacos.context-path:}", configLongPollTimeout="${nacos.configLongPollTimeout:}", maxRetry="${nacos.maxRetry:}", configRetryTime="${nacos.configRetryTime:}", password="${nacos.password:}", endpoint="${nacos.endpoint:}", serverAddr="${nacos.server-addr:}", accessKey="${nacos.access-key:}", enableRemoteSyncConfig="${nacos.enableRemoteSyncConfig:}", clusterName="${nacos.cluster-name:}", namespace="${nacos.namespace:}", username="${nacos.username:}"), yaml=false, dataId="${nacos.config.data-id:}")', timestamp='1690101057610'
2023-07-23 16:30:57.629  INFO 2128 --- [           main] c.s.b.n.e.config.NacosConfiguration      : create NacosConfiguration
2023-07-23 16:30:57.631  INFO 2128 --- [           main] c.s.b.n.e.service.ConfigServiceDemo      : create ConfigServiceDemo
2023-07-23 16:30:57.636  INFO 2128 --- [           main] c.s.b.n.e.c.NacosConfigController        : create NacosConfigController
#暴露基础路径'/actuator'下方的14个端点
2023-07-23 16:30:58.080  INFO 2128 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 14 endpoint(s) beneath base path '/actuator'
2023-07-23 16:30:58.121  INFO 2128 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
#nacos配置元数据事件日志记录，NacosConfiguration，配置属性源
2023-07-23 16:30:58.131  INFO 2128 --- [           main] .LoggingNacosConfigMetadataEventListener : Nacos Config Metadata : dataId='nacos-config-spring-boot-starter-example', groupId='DEVELOP', beanName='nacosConfiguration', bean='null', beanType='class com.spring.boot.nacos.example.config.NacosConfiguration', annotatedElement='null', xmlResource='null', nacosProperties='{encode=UTF-8, maxRetry=10, configRetryTime=3000, serverAddr=127.0.0.1:8848, enableRemoteSyncConfig=true, namespace=nacos-env-dev, configLongPollTimeout=60000}', nacosPropertiesAttributes='{accessKey=${nacos.access-key:}, clusterName=${nacos.cluster-name:}, configLongPollTimeout=${nacos.configLongPollTimeout:}, configRetryTime=${nacos.configRetryTime:}, contextPath=${nacos.context-path:}, enableRemoteSyncConfig=${nacos.enableRemoteSyncConfig:}, encode=${nacos.encode:UTF-8}, endpoint=${nacos.endpoint:}, maxRetry=${nacos.maxRetry:}, namespace=${nacos.namespace:}, password=${nacos.password:}, secretKey=${nacos.secret-key:}, serverAddr=${nacos.server-addr:}, username=${nacos.username:}}', source='com.spring.boot.nacos.example.config.NacosConfiguration', timestamp='1690101056856'
#nacos配置元数据事件日志记录，ExampleNacosConfigListener.onChange()，配置监视器
2023-07-23 16:30:58.142  INFO 2128 --- [           main] .LoggingNacosConfigMetadataEventListener : Nacos Config Metadata : dataId='nacos-config-spring-boot-starter-example', groupId='DEVELOP', beanName='exampleNacosConfigListener', bean='ExampleNacosConfigListener(count=3)', beanType='class com.spring.boot.nacos.example.config.ExampleNacosConfigListener', annotatedElement='public final void com.spring.boot.nacos.example.config.ExampleNacosConfigListener.onChange(java.lang.String)', xmlResource='null', nacosProperties='{encode=UTF-8, maxRetry=10, configRetryTime=3000, serverAddr=127.0.0.1:8848, enableRemoteSyncConfig=true, namespace=nacos-env-dev, configLongPollTimeout=60000}', nacosPropertiesAttributes='{accessKey=${nacos.access-key:}, clusterName=${nacos.cluster-name:}, configLongPollTimeout=${nacos.configLongPollTimeout:}, configRetryTime=${nacos.configRetryTime:}, contextPath=${nacos.context-path:}, enableRemoteSyncConfig=${nacos.enableRemoteSyncConfig:}, encode=${nacos.encode:UTF-8}, endpoint=${nacos.endpoint:}, maxRetry=${nacos.maxRetry:}, namespace=${nacos.namespace:}, password=${nacos.password:}, secretKey=${nacos.secret-key:}, serverAddr=${nacos.server-addr:}, username=${nacos.username:}}', source='@com.alibaba.nacos.api.config.annotation.NacosConfigListener(type=YAML, properties=@com.alibaba.nacos.api.annotation.NacosProperties(encode="${nacos.encode:UTF-8}", secretKey="${nacos.secret-key:}", contextPath="${nacos.context-path:}", configLongPollTimeout="${nacos.configLongPollTimeout:}", maxRetry="${nacos.maxRetry:}", configRetryTime="${nacos.configRetryTime:}", password="${nacos.password:}", endpoint="${nacos.endpoint:}", serverAddr="${nacos.server-addr:}", accessKey="${nacos.access-key:}", enableRemoteSyncConfig="${nacos.enableRemoteSyncConfig:}", clusterName="${nacos.cluster-name:}", namespace="${nacos.namespace:}", username="${nacos.username:}"), timeout=3000, converter=com.alibaba.nacos.api.config.convert.NacosConfigConverter.class, groupId="${nacos.config.group:}", dataId="${nacos.config.data-id:}")', timestamp='1690101058141'
#应用启动完成
2023-07-23 16:30:58.151  INFO 2128 --- [           main] c.s.b.n.example.NacosConfigApplication   : Started NacosConfigApplication in 3.148 seconds (JVM running for 3.779)

#调度服务器初始化
2023-07-23 16:30:58.568  INFO 2128 --- [)-192.168.0.101] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2023-07-23 16:30:58.569  INFO 2128 --- [)-192.168.0.101] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2023-07-23 16:30:58.570  INFO 2128 --- [)-192.168.0.101] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms

```


## 2.请求日志
```shell

#通过配置服务获取配置数据，ConfigService
2023-07-23 16:31:35.892  INFO 2128 --- [nio-8080-exec-3] c.s.b.n.e.service.ConfigServiceDemo      : get config, content=
dynamic:
  config:
    enable: true

dynamic-config: 'yml-demo-dev-dynamic'
config-namespace: 'nacos-config-dev'

people:
  count: 3

```


## 3.关闭日志
```shell

#开始销毁发布服务器，通知中心
2023-07-23 16:32:14.810  WARN 2128 --- [       Thread-5] c.a.nacos.common.notify.NotifyCenter     : [NotifyCenter] Start destroying Publisher
#发布服务器销毁完成，通知中心
2023-07-23 16:32:14.810  WARN 2128 --- [       Thread-5] c.a.nacos.common.notify.NotifyCenter     : [NotifyCenter] Destruction of the end
#开始销毁公共http客户端
2023-07-23 16:32:14.811  WARN 2128 --- [       Thread-0] c.a.n.common.http.HttpClientBeanHolder   : [HttpClientBeanHolder] Start destroying common HttpClient
#http客户端销毁完成
2023-07-23 16:32:14.812  WARN 2128 --- [       Thread-0] c.a.n.common.http.HttpClientBeanHolder   : [HttpClientBeanHolder] Destruction of the end
#运行时关机挂钩，SpringApplicationShutdownHook
#@NacosValue注解已销毁
2023-07-23 16:32:14.827  INFO 2128 --- [ionShutdownHook] .c.NacosValueAnnotationBeanPostProcessor : true was destroying!
2023-07-23 16:32:14.827  INFO 2128 --- [ionShutdownHook] .c.NacosValueAnnotationBeanPostProcessor : 3 was destroying!
2023-07-23 16:32:14.827  INFO 2128 --- [ionShutdownHook] .c.NacosValueAnnotationBeanPostProcessor : class com.alibaba.nacos.spring.context.annotation.config.NacosValueAnnotationBeanPostProcessor was destroying!
#@NacosInjected注解已销毁
2023-07-23 16:32:14.827  INFO 2128 --- [ionShutdownHook] AnnotationNacosInjectedBeanPostProcessor : com.alibaba.nacos.spring.context.event.config.EventPublishingConfigService@2865f590 was destroying!
2023-07-23 16:32:14.831  INFO 2128 --- [ionShutdownHook] AnnotationNacosInjectedBeanPostProcessor : class com.alibaba.nacos.spring.beans.factory.annotation.AnnotationNacosInjectedBeanPostProcessor was destroying!

```

