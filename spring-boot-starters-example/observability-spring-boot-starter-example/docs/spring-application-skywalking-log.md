

```shell
/Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home/bin/java -javaagent:/Users/lihuagang/Documents/SkyWalking/skywalking-agent/skywalking-agent.jar -Dskywalking.agent.service_name=observability-spring-boot-starter-example -Dskywalking.collector.backend_service=127.0.0.1:11800 com.spring.boot.observability.example.ObservabilityApplication
# skywalking-agent代理包路径
DEBUG 2023-12-11 16:14:25.380 main AgentPackagePath : The beacon class location is jar:file:/Users/lihuagang/Documents/SkyWalking/skywalking-agent/skywalking-agent.jar!/org/apache/skywalking/apm/agent/core/boot/AgentPackagePath.class. 
# 代理配置初始化
INFO 2023-12-11 16:14:25.381 main SnifferConfigInitializer : Config file found in /Users/lihuagang/Documents/SkyWalking/skywalking-agent/config/agent.config. 
INFO 2023-12-11 16:14:25.445 main SnifferConfigInitializer : SnifferConfigInitializer url:jar:file:/Users/lihuagang/.m2/repository/org/springframework/boot/spring-boot-starter-actuator/2.7.16/spring-boot-starter-actuator-2.7.16.jar!/META-INF/MANIFEST.MF 
......
INFO 2023-12-11 16:14:25.493 main SnifferConfigInitializer : SnifferConfigInitializer url:jar:file:/Users/lihuagang/.m2/repository/org/apache/skywalking/apm-toolkit-logback-1.x/9.1.0/apm-toolkit-logback-1.x-9.1.0.jar!/META-INF/MANIFEST.MF 
# 代理插件加载
INFO 2023-12-11 16:14:25.503 main AgentClassLoader : /Users/lihuagang/Documents/SkyWalking/skywalking-agent/plugins/apm-spring-kafka-1.x-plugin-9.1.0.jar loaded. 
......
INFO 2023-12-11 16:14:25.536 main AgentClassLoader : /Users/lihuagang/Documents/SkyWalking/skywalking-agent/plugins/apm-dubbo-3.x-plugin-9.1.0.jar loaded. 
# 代理激活插件加载
INFO 2023-12-11 16:14:25.579 main AgentClassLoader : /Users/lihuagang/Documents/SkyWalking/skywalking-agent/activations/apm-toolkit-webflux-activation-9.1.0.jar loaded. 
......
INFO 2023-12-11 16:14:25.589 main AgentClassLoader : /Users/lihuagang/Documents/SkyWalking/skywalking-agent/activations/apm-toolkit-logback-1.x-activation-9.1.0.jar loaded. 
# 插件资源解析
INFO 2023-12-11 16:14:25.605 main PluginResourcesResolver : find skywalking plugin define in jar:file:/Users/lihuagang/Documents/SkyWalking/skywalking-agent/plugins/apm-spring-kafka-1.x-plugin-9.1.0.jar!/skywalking-plugin.def 
......
INFO 2023-12-11 16:14:25.609 main PluginResourcesResolver : find skywalking plugin define in jar:file:/Users/lihuagang/Documents/SkyWalking/skywalking-agent/plugins/apm-dubbo-3.x-plugin-9.1.0.jar!/skywalking-plugin.def 
# 激活插件资源解析
INFO 2023-12-11 16:14:25.635 main PluginResourcesResolver : find skywalking plugin define in jar:file:/Users/lihuagang/Documents/SkyWalking/skywalking-agent/activations/apm-toolkit-webflux-activation-9.1.0.jar!/skywalking-plugin.def 
......
INFO 2023-12-11 16:14:25.635 main PluginResourcesResolver : find skywalking plugin define in jar:file:/Users/lihuagang/Documents/SkyWalking/skywalking-agent/activations/apm-toolkit-logback-1.x-activation-9.1.0.jar!/skywalking-plugin.def 
# SkyWalking代理开始安装类字节码转换器
INFO 2023-12-11 16:14:25.942 main SkyWalkingAgent : Skywalking agent begin to install transformer ... 

# 应用开始运行
# Spring应用事件侦听器 创建
2023-12-11 16:14:28.885 [TID:N/A] [main] INFO  c.s.b.o.e.PrometheusClientApplicationEventListener create PrometheusClientApplicationEventListener
2023-12-11 16:14:28.899 [TID:N/A] [main] INFO  c.s.b.l.e.LogSpringApplicationEventListener create LogSpringApplicationEventListener
# Spring应用运行侦听器 创建
2023-12-11 16:14:29.095 [TID:N/A] [main] INFO  c.s.b.l.LogSpringApplicationRunListener create LogSpringApplicationRunListener
# 在准备好环境后调用
2023-12-11 16:14:29.662 [TID:N/A] [main] INFO  c.s.b.l.e.LogSpringApplicationEventListener receive event org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent[source=org.springframework.boot.SpringApplication@d70e9a]
2023-12-11 16:14:29.668 [TID:N/A] [main] INFO  c.s.b.l.LogSpringApplicationRunListener Called once the environment has been prepared, but before the ApplicationContext has been created. bootstrapContext=org.springframework.boot.DefaultBootstrapContext@61808ecd, environment=ApplicationServletEnvironment {activeProfiles=[dev], defaultProfiles=[default], propertySources=[ConfigurationPropertySourcesPropertySource {name='configurationProperties'}, StubPropertySource {name='servletConfigInitParams'}, StubPropertySource {name='servletContextInitParams'}, PropertiesPropertySource {name='systemProperties'}, OriginAwareSystemEnvironmentPropertySource {name='systemEnvironment'}, RandomValuePropertySource {name='random'}, OriginTrackedMapPropertySource {name='Config resource 'class path resource [application.yml]' via location 'optional:classpath:/''}]}

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.16)

# 创建并准备好ApplicationContext后调用(应用上下文初始化完成事件)
2023-12-11 16:14:29.970 [TID:N/A] [main] INFO  c.s.b.l.e.LogSpringApplicationEventListener receive event org.springframework.boot.context.event.ApplicationContextInitializedEvent[source=org.springframework.boot.SpringApplication@d70e9a]
2023-12-11 16:14:29.971 [TID:N/A] [main] INFO  c.s.b.l.LogSpringApplicationRunListener Called once the ApplicationContext has been created and prepared, but before sources have been loaded. context=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@56ec6960, started on Thu Jan 01 08:00:00 CST 1970
# 启动应用程序
2023-12-11 16:14:29.976 [TID:N/A] [main] INFO  c.s.b.o.e.ObservabilityApplication Starting ObservabilityApplication using Java 11.0.2 on lihuagangdebijibendiannao.local with PID 39602 (/Users/lihuagang/Documents/workspace/spring-boot-extend/spring-boot-starters-example/observability-spring-boot-starter-example/target/classes started by lihuagang in /Users/lihuagang/Documents/workspace/spring-boot-extend)
# 活动配置文件集
2023-12-11 16:14:29.976 [TID:N/A] [main] INFO  c.s.b.o.e.ObservabilityApplication The following 1 profile is active: "dev"
# 在加载应用上下文之后但在刷新之前调用
2023-12-11 16:14:30.038 [TID:N/A] [main] INFO  c.s.b.l.e.LogSpringApplicationEventListener receive event org.springframework.boot.context.event.ApplicationPreparedEvent[source=org.springframework.boot.SpringApplication@d70e9a]
2023-12-11 16:14:30.039 [TID:N/A] [main] INFO  c.s.b.l.LogSpringApplicationRunListener Called once the application context has been loaded but before it has been refreshed. context=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@56ec6960, started on Thu Jan 01 08:00:00 CST 1970
# Tomcat初始化
2023-12-11 16:14:31.927 [TID:N/A] [main] INFO  o.s.b.w.e.tomcat.TomcatWebServer Tomcat initialized with port(s): 8383 (http)
2023-12-11 16:14:31.934 [TID:N/A] [main] INFO  o.a.coyote.http11.Http11NioProtocol Initializing ProtocolHandler ["http-nio-8383"]
2023-12-11 16:14:31.939 [TID:N/A] [main] INFO  o.a.catalina.core.StandardService Starting service [Tomcat]
2023-12-11 16:14:31.941 [TID:N/A] [main] INFO  o.a.catalina.core.StandardEngine Starting Servlet engine: [Apache Tomcat/9.0.80]
2023-12-11 16:14:32.085 [TID:N/A] [main] INFO  o.a.c.c.C.[Tomcat].[localhost].[/] Initializing Spring embedded WebApplicationContext
2023-12-11 16:14:32.085 [TID:N/A] [main] INFO  o.s.b.w.s.c.ServletWebServerApplicationContext Root WebApplicationContext: initialization completed in 2045 ms
# 自动配置
2023-12-11 16:14:32.657 [TID:N/A] [main] INFO  c.s.b.l.a.LogAutoConfiguration create LogAutoConfiguration
2023-12-11 16:14:32.661 [TID:N/A] [main] INFO  c.s.boot.log.LogApplicationRunner create LogApplicationRunner
2023-12-11 16:14:32.662 [TID:N/A] [main] INFO  c.s.boot.log.LogCommandLineRunner create LogCommandLineRunner
2023-12-11 16:14:32.662 [TID:N/A] [main] INFO  c.s.b.l.e.ReadinessStateEventListener create ReadinessStateEventListener
2023-12-11 16:14:32.667 [TID:N/A] [main] INFO  c.s.b.l.e.LivenessStateEventListener create LivenessStateEventListener
2023-12-11 16:14:32.668 [TID:N/A] [main] INFO  c.spring.boot.log.LocalCacheVerifier create LocalCacheVerifier
2023-12-11 16:14:32.670 [TID:N/A] [main] INFO  c.spring.boot.log.LocalCacheVerifier check LocalCache
2023-12-11 16:14:32.674 [TID:N/A] [main] INFO  c.s.b.o.a.PrometheusClientAutoConfiguration create PrometheusClientAutoConfiguration
# Tomcat启动完成
2023-12-11 16:14:33.038 [TID:N/A] [main] INFO  o.a.coyote.http11.Http11NioProtocol Starting ProtocolHandler ["http-nio-8383"]
2023-12-11 16:14:33.142 [TID:N/A] [main] INFO  o.s.b.w.e.tomcat.TomcatWebServer Tomcat started on port(s): 8383 (http) with context path ''
# Tomcat-1初始化
2023-12-11 16:14:33.304 [TID:N/A] [main] INFO  o.s.b.w.e.tomcat.TomcatWebServer Tomcat initialized with port(s): 38081 (http)
2023-12-11 16:14:33.305 [TID:N/A] [main] INFO  o.a.coyote.http11.Http11NioProtocol Initializing ProtocolHandler ["http-nio-38081"]
2023-12-11 16:14:33.306 [TID:N/A] [main] INFO  o.a.catalina.core.StandardService Starting service [Tomcat]
2023-12-11 16:14:33.306 [TID:N/A] [main] INFO  o.a.catalina.core.StandardEngine Starting Servlet engine: [Apache Tomcat/9.0.80]
2023-12-11 16:14:33.314 [TID:N/A] [main] INFO  o.a.c.c.C.[Tomcat-1].[localhost].[/] Initializing Spring embedded WebApplicationContext
2023-12-11 16:14:33.314 [TID:N/A] [main] INFO  o.s.b.w.s.c.ServletWebServerApplicationContext Root WebApplicationContext: initialization completed in 156 ms
# 暴露端点
2023-12-11 16:14:33.327 [TID:N/A] [main] INFO  o.s.b.a.e.web.EndpointLinksResolver Exposing 11 endpoint(s) beneath base path ''
# Tomcat-1启动完成
2023-12-11 16:14:33.450 [TID:N/A] [main] INFO  o.a.coyote.http11.Http11NioProtocol Starting ProtocolHandler ["http-nio-38081"]
2023-12-11 16:14:33.452 [TID:N/A] [main] INFO  o.s.b.w.e.tomcat.TomcatWebServer Tomcat started on port(s): 38081 (http) with context path ''
# 应用启动完成
2023-12-11 16:14:33.476 [TID:N/A] [main] INFO  c.s.b.o.e.ObservabilityApplication Started ObservabilityApplication in 4.395 seconds (JVM running for 8.416)
# 刷新应用上下文后，但在调用任何应用程序和命令行运行程序之前发布的事件
2023-12-11 16:14:33.478 [TID:N/A] [main] INFO  c.s.b.o.e.PrometheusClientApplicationEventListener init and register the default Hotspot collectors.
2023-12-11 16:14:33.496 [TID:N/A] [main] INFO  c.s.b.l.e.LogSpringApplicationEventListener receive event org.springframework.boot.context.event.ApplicationStartedEvent[source=org.springframework.boot.SpringApplication@d70e9a]
2023-12-11 16:14:33.592 [TID:N/A] [main] INFO  c.s.b.l.e.LivenessStateEventListener receive event CORRECT
2023-12-11 16:14:33.594 [TID:N/A] [main] INFO  c.s.b.l.LogSpringApplicationRunListener The context has been refreshed and the application has started but CommandLineRunners and ApplicationRunners have not been called. context=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@56ec6960, started on Mon Dec 11 16:14:30 CST 2023, timeTaken=PT4.395662916S
# 运行应用运行程序
2023-12-11 16:14:33.596 [TID:N/A] [main] INFO  c.s.boot.log.LogApplicationRunner run ApplicationArguments, args=org.springframework.boot.DefaultApplicationArguments@76afb9d
# 运行命令行运行程序
2023-12-11 16:14:33.596 [TID:N/A] [main] INFO  c.s.boot.log.LogCommandLineRunner run String..., args=[]
# 在刷新应用上下文并且调用了所有CommandLineRunner和ApplicationRunner之后，在运行方法完成之前立即调用
2023-12-11 16:14:33.597 [TID:N/A] [main] INFO  c.s.b.l.e.LogSpringApplicationEventListener receive event org.springframework.boot.context.event.ApplicationReadyEvent[source=org.springframework.boot.SpringApplication@d70e9a]
2023-12-11 16:14:33.599 [TID:N/A] [main] INFO  c.s.b.l.e.ReadinessStateEventListener receive event ACCEPTING_TRAFFIC
2023-12-11 16:14:33.599 [TID:N/A] [main] INFO  c.s.b.l.LogSpringApplicationRunListener Called immediately before the run method finishes, when the application context has been refreshed and all CommandLineRunners and ApplicationRunners have been called. context=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@56ec6960, started on Mon Dec 11 16:14:30 CST 2023, timeTaken=PT4.517919583S
# 初始化请求分发程序
2023-12-11 16:14:34.155 [TID:N/A] [RMI TCP Connection(1)-127.0.0.1] INFO  o.a.c.c.C.[Tomcat].[localhost].[/] Initializing Spring DispatcherServlet 'dispatcherServlet'
2023-12-11 16:14:34.159 [TID:N/A] [RMI TCP Connection(1)-127.0.0.1] INFO  o.s.web.servlet.DispatcherServlet Initializing Servlet 'dispatcherServlet'
2023-12-11 16:14:34.160 [TID:N/A] [RMI TCP Connection(1)-127.0.0.1] INFO  o.s.web.servlet.DispatcherServlet Completed initialization in 1 ms


2023-12-11 16:16:00.334 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.73.17022825602990001] [http-nio-38081-exec-1] INFO  o.a.c.c.C.[Tomcat-1].[localhost].[/] Initializing Spring DispatcherServlet 'dispatcherServlet'
2023-12-11 16:16:00.336 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.73.17022825602990001] [http-nio-38081-exec-1] INFO  o.s.web.servlet.DispatcherServlet Initializing Servlet 'dispatcherServlet'
2023-12-11 16:16:00.346 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.73.17022825602990001] [http-nio-38081-exec-1] INFO  o.s.web.servlet.DispatcherServlet Completed initialization in 10 ms

2023-12-11 16:21:05.352 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.57.17022828652930001] [http-nio-8383-exec-1] INFO  c.s.b.o.e.controller.AdminController checkHealth
2023-12-11 16:21:55.994 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.61.17022829159770001] [http-nio-8383-exec-5] INFO  c.s.b.o.e.controller.AdminController checkHealth
2023-12-11 16:21:57.955 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.62.17022829179520001] [http-nio-8383-exec-6] INFO  c.s.b.o.e.controller.AdminController checkHealth
2023-12-11 16:21:59.372 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.63.17022829193680001] [http-nio-8383-exec-7] INFO  c.s.b.o.e.controller.AdminController checkHealth
2023-12-11 16:22:00.545 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.66.17022829205430001] [http-nio-8383-exec-10] INFO  c.s.b.o.e.controller.AdminController checkHealth
2023-12-11 16:22:02.053 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.57.17022829220500003] [http-nio-8383-exec-1] INFO  c.s.b.o.e.controller.AdminController checkHealth
2023-12-11 16:22:03.207 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.58.17022829232040001] [http-nio-8383-exec-2] INFO  c.s.b.o.e.controller.AdminController checkHealth
2023-12-11 16:22:04.433 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.60.17022829244290001] [http-nio-8383-exec-4] INFO  c.s.b.o.e.controller.AdminController checkHealth
2023-12-11 16:22:05.674 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.62.17022829256650003] [http-nio-8383-exec-6] INFO  c.s.b.o.e.controller.AdminController checkHealth
2023-12-11 16:22:06.937 [TID:3fc1dd2eb6be4e0d83911c62a8602b8c.63.17022829269340003] [http-nio-8383-exec-7] INFO  c.s.b.o.e.controller.AdminController checkHealth


2023-12-11 16:25:27.767 [TID:N/A] [SpringApplicationShutdownHook] INFO  c.s.b.l.e.ReadinessStateEventListener receive event REFUSING_TRAFFIC
2023-12-11 16:25:27.783 [TID:N/A] [SpringApplicationShutdownHook] INFO  c.s.b.l.e.ReadinessStateEventListener receive event REFUSING_TRAFFIC

Process finished with exit code 130 (interrupted by signal 2: SIGINT)

```

