

```shell
/Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home/bin/java com.spring.boot.observability.example.ObservabilityApplication
# Spring应用事件侦听器 创建
11:33:19.284 [main] INFO com.spring.boot.observability.event.PrometheusClientApplicationEventListener - create PrometheusClientApplicationEventListener
11:33:19.289 [main] INFO com.spring.boot.log.event.LogSpringApplicationEventListener - create LogSpringApplicationEventListener
# Spring应用运行侦听器 创建
11:33:19.337 [main] INFO com.spring.boot.log.LogSpringApplicationRunListener - create LogSpringApplicationRunListener
# 在准备好环境后调用
2023-09-18 11:33:19.777  INFO 49225 --- [           main] .b.l.e.LogSpringApplicationEventListener : receive event org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent[source=org.springframework.boot.SpringApplication@10163d6]
2023-09-18 11:33:19.780  INFO 49225 --- [           main] c.s.b.l.LogSpringApplicationRunListener  : Called once the environment has been prepared, but before the ApplicationContext has been created. bootstrapContext=org.springframework.boot.DefaultBootstrapContext@247d8ae, environment=ApplicationServletEnvironment {activeProfiles=[], defaultProfiles=[default], propertySources=[ConfigurationPropertySourcesPropertySource {name='configurationProperties'}, StubPropertySource {name='servletConfigInitParams'}, StubPropertySource {name='servletContextInitParams'}, PropertiesPropertySource {name='systemProperties'}, OriginAwareSystemEnvironmentPropertySource {name='systemEnvironment'}, RandomValuePropertySource {name='random'}, OriginTrackedMapPropertySource {name='Config resource 'class path resource [application.yml]' via location 'optional:classpath:/''}]}

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.16)

# 创建并准备好ApplicationContext后调用(应用上下文初始化完成事件)
2023-09-18 11:33:19.847  INFO 49225 --- [           main] .b.l.e.LogSpringApplicationEventListener : receive event org.springframework.boot.context.event.ApplicationContextInitializedEvent[source=org.springframework.boot.SpringApplication@10163d6]
2023-09-18 11:33:19.847  INFO 49225 --- [           main] c.s.b.l.LogSpringApplicationRunListener  : Called once the ApplicationContext has been created and prepared, but before sources have been loaded. context=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@4a7f959b, started on Thu Jan 01 08:00:00 CST 1970
# 启动应用程序
2023-09-18 11:33:19.854  INFO 49225 --- [           main] c.s.b.o.e.ObservabilityApplication       : Starting ObservabilityApplication using Java 1.8.0_333 on lihuagangdebijibendiannao.local with PID 49225 (/Users/lihuagang/Documents/workspace/spring-boot-extend/spring-boot-starters-example/observability-spring-boot-starter-example/target/classes started by lihuagang in /Users/lihuagang/Documents/workspace/spring-boot-extend)
# 活动配置文件集
2023-09-18 11:33:19.856  INFO 49225 --- [           main] c.s.b.o.e.ObservabilityApplication       : No active profile set, falling back to 1 default profile: "default"
# 在加载应用上下文之后但在刷新之前调用
2023-09-18 11:33:19.908  INFO 49225 --- [           main] .b.l.e.LogSpringApplicationEventListener : receive event org.springframework.boot.context.event.ApplicationPreparedEvent[source=org.springframework.boot.SpringApplication@10163d6]
2023-09-18 11:33:19.908  INFO 49225 --- [           main] c.s.b.l.LogSpringApplicationRunListener  : Called once the application context has been loaded but before it has been refreshed. context=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@4a7f959b, started on Thu Jan 01 08:00:00 CST 1970
# Tomcat初始化
2023-09-18 11:33:21.202  INFO 49225 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-09-18 11:33:21.208  INFO 49225 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-09-18 11:33:21.209  INFO 49225 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.78]
2023-09-18 11:33:21.334  INFO 49225 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-09-18 11:33:21.334  INFO 49225 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1425 ms
# 自动配置
2023-09-18 11:33:21.785  INFO 49225 --- [           main] c.s.b.l.a.LogAutoConfiguration           : create LogAutoConfiguration
2023-09-18 11:33:21.786  INFO 49225 --- [           main] c.spring.boot.log.LogApplicationRunner   : create LogApplicationRunner
2023-09-18 11:33:21.786  INFO 49225 --- [           main] c.spring.boot.log.LogCommandLineRunner   : create LogCommandLineRunner
2023-09-18 11:33:21.786  INFO 49225 --- [           main] c.s.b.l.e.ReadinessStateEventListener    : create ReadinessStateEventListener
2023-09-18 11:33:21.797  INFO 49225 --- [           main] c.s.b.l.e.LivenessStateEventListener     : create LivenessStateEventListener
2023-09-18 11:33:21.798  INFO 49225 --- [           main] com.spring.boot.log.LocalCacheVerifier   : create LocalCacheVerifier
2023-09-18 11:33:21.800  INFO 49225 --- [           main] com.spring.boot.log.LocalCacheVerifier   : check LocalCache
2023-09-18 11:33:21.804  INFO 49225 --- [           main] .b.o.a.PrometheusClientAutoConfiguration : create PrometheusClientAutoConfiguration
# Tomcat启动完成
2023-09-18 11:33:22.083  INFO 49225 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
# Tomcat-1初始化
2023-09-18 11:33:22.180  INFO 49225 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 38081 (http)
2023-09-18 11:33:22.181  INFO 49225 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-09-18 11:33:22.181  INFO 49225 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.78]
2023-09-18 11:33:22.191  INFO 49225 --- [           main] o.a.c.c.C.[Tomcat-1].[localhost].[/]     : Initializing Spring embedded WebApplicationContext
2023-09-18 11:33:22.191  INFO 49225 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 103 ms
# 暴露端点
2023-09-18 11:33:22.205  INFO 49225 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 13 endpoint(s) beneath base path ''
# Tomcat-1启动完成
2023-09-18 11:33:22.245  INFO 49225 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 38081 (http) with context path ''
# 应用启动完成
2023-09-18 11:33:22.262  INFO 49225 --- [           main] c.s.b.o.e.ObservabilityApplication       : Started ObservabilityApplication in 2.932 seconds (JVM running for 3.664)
# 刷新应用上下文后，但在调用任何应用程序和命令行运行程序之前发布的事件
2023-09-18 11:33:22.262  INFO 49225 --- [           main] PrometheusClientApplicationEventListener : init and register the default Hotspot collectors.
2023-09-18 11:33:22.354  INFO 49225 --- [           main] .b.l.e.LogSpringApplicationEventListener : receive event org.springframework.boot.context.event.ApplicationStartedEvent[source=org.springframework.boot.SpringApplication@10163d6]
2023-09-18 11:33:22.385  INFO 49225 --- [           main] c.s.b.l.e.LivenessStateEventListener     : receive event CORRECT
2023-09-18 11:33:22.386  INFO 49225 --- [           main] c.s.b.l.LogSpringApplicationRunListener  : The context has been refreshed and the application has started but CommandLineRunners and ApplicationRunners have not been called. context=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@4a7f959b, started on Mon Sep 18 11:33:19 CST 2023, timeTaken=PT2.932330708S
# 运行应用运行程序
2023-09-18 11:33:22.389  INFO 49225 --- [           main] c.spring.boot.log.LogApplicationRunner   : run ApplicationArguments, args=org.springframework.boot.DefaultApplicationArguments@5292ceca
# 运行命令行运行程序
2023-09-18 11:33:22.389  INFO 49225 --- [           main] c.spring.boot.log.LogCommandLineRunner   : run String..., args=[]
# 在刷新应用上下文并且调用了所有CommandLineRunner和ApplicationRunner之后，在运行方法完成之前立即调用
2023-09-18 11:33:22.389  INFO 49225 --- [           main] .b.l.e.LogSpringApplicationEventListener : receive event org.springframework.boot.context.event.ApplicationReadyEvent[source=org.springframework.boot.SpringApplication@10163d6]
2023-09-18 11:33:22.393  INFO 49225 --- [           main] c.s.b.l.e.ReadinessStateEventListener    : receive event ACCEPTING_TRAFFIC
2023-09-18 11:33:22.395  INFO 49225 --- [           main] c.s.b.l.LogSpringApplicationRunListener  : Called immediately before the run method finishes, when the application context has been refreshed and all CommandLineRunners and ApplicationRunners have been called. context=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@4a7f959b, started on Mon Sep 18 11:33:19 CST 2023, timeTaken=PT3.0602585S
# 初始化请求分发程序
2023-09-18 11:33:22.478  INFO 49225 --- [2)-172.16.22.50] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2023-09-18 11:33:22.478  INFO 49225 --- [2)-172.16.22.50] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2023-09-18 11:33:22.479  INFO 49225 --- [2)-172.16.22.50] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms


2023-09-18 11:34:46.709  INFO 49225 --- [io-38081-exec-1] o.a.c.c.C.[Tomcat-1].[localhost].[/]     : Initializing Spring DispatcherServlet 'dispatcherServlet'
2023-09-18 11:34:46.710  INFO 49225 --- [io-38081-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2023-09-18 11:34:46.715  INFO 49225 --- [io-38081-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 4 ms


2023-09-18 11:35:14.019  INFO 49225 --- [ionShutdownHook] c.s.b.l.e.ReadinessStateEventListener    : receive event REFUSING_TRAFFIC
2023-09-18 11:35:14.022  INFO 49225 --- [ionShutdownHook] c.s.b.l.e.ReadinessStateEventListener    : receive event REFUSING_TRAFFIC

Process finished with exit code 130 (interrupted by signal 2: SIGINT)

```

