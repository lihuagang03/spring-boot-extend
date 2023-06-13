

基于配置属性条件自动装配不同实现的服务组件
======
> IoC使用发布订阅模型，组件是主题，主动创建组件是生产者，依赖注入组件是消费者。
> 
> IoC，Inversion of Control，控制反转容器，是一种设计思想。将你设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。
> 理解好IoC的关键是要明确“谁控制谁，控制什么，为何是反转，哪些方面反转了”。
> DI，Dependency Injection，依赖注入，是组件之间依赖关系由容器在运行期决定，由容器动态的将某个依赖关系注入到组件之中。
> 理解DI的关键是：“谁依赖谁，为什么需要依赖，谁注入谁，注入了什么”。


![语音订会期望的架构实现方案](docs/语音订会期望的架构实现方案.png)

这个想法可行，通过 @ConditionalOnProperty + @Service 实现。


spring-boot：@ConditionalOnProperty根据不同配置注入不同实现的bean
https://www.cnblogs.com/teach/p/15315046.html

SpringBoot 中@ConditionalOnProperty的妙用，指定接口实现
https://blog.csdn.net/qq_42651904/article/details/115012377


### reference
- [Condition Annotations](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.developing-auto-configuration.condition-annotations)
  - Class Conditions
  - Bean Conditions
  - Property Conditions
  - SpEL Expression Conditions
- [2分钟带你理解IoC](https://zhuanlan.zhihu.com/p/36840573)

