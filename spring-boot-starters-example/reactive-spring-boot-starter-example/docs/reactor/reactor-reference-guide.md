

Reactor 3 Reference Guide 参考指南
======
> https://projectreactor.io/docs/core/release/reference/index.html
> 
> Version 3.5.8


## 1. About the Documentation
This section provides a brief overview of Reactor reference documentation. 
You do not need to read this guide in a linear fashion. 
Each piece stands on its own, though they often refer to other pieces.

本节简要概述了反应堆参考文档。您不需要以线性方式阅读本指南。每一件作品都是独立的，尽管它们通常指的是其他作品。

### 1.3. Getting Help

### 1.4. Where to Go from Here
- Head to [Getting Started](https://projectreactor.io/docs/core/release/reference/index.html#getting-started) 
  if you feel like jumping straight into the code.
  快速入门

- If you are new to reactive programming, though, you should probably start with the 
  [Introduction to Reactive Programming](https://projectreactor.io/docs/core/release/reference/index.html#intro-reactive).
  反应式编程的新手

- If you are familiar with Reactor concepts and are just looking for the right tool for the job but cannot think of a relevant operator, 
  try the [Which operator do I need?](https://projectreactor.io/docs/core/release/reference/index.html#which-operator) Appendix.
  熟悉反应式的概念

- In order to dig deeper into the core features of Reactor, head to 
  [Reactor Core Features](https://projectreactor.io/docs/core/release/reference/index.html#core-features) to learn:
  更深入地了解反应堆的核心功能

  - More about Reactor’s reactive types in the [Flux, an Asynchronous Sequence of 0-N Items](https://projectreactor.io/docs/core/release/reference/index.html#flux) and 
    [Mono, an Asynchronous 0-1 Result](https://projectreactor.io/docs/core/release/reference/index.html#mono) sections.
    反应式类型，Flux是0-N项的异步序列，Mono是异步0-1个结果

  - How to switch threading contexts using [a scheduler](https://projectreactor.io/docs/core/release/reference/index.html#schedulers).
    如何使用调度器切换线程上下文

  - How to handle errors in the [Handling Errors](https://projectreactor.io/docs/core/release/reference/index.html#error.handling) section.
    如何处理错误

- Unit testing? Yes it is possible with the reactor-test project! See [Testing](https://projectreactor.io/docs/core/release/reference/index.html#testing).
  单元测试

- [Programmatically creating a sequence](https://projectreactor.io/docs/core/release/reference/index.html#producing) offers a more advanced way of creating reactive sources. 
  创建反应式数据源

- Other advanced topics are covered in [Advanced Features and Concepts](https://projectreactor.io/docs/core/release/reference/index.html#advanced).
  其他高级主题


## 2. Getting Started
This section contains information that should help you get going with Reactor. It includes the following sections:

- Introducing Reactor

- Prerequisites

- Understanding the BOM and versioning scheme

- Getting Reactor

### 2.1. Introducing Reactor
Reactor is **a fully non-blocking reactive programming foundation** for the JVM, with efficient demand management (in the form of managing “**backpressure**”). 
It integrates directly with the Java 8 functional APIs, notably CompletableFuture, Stream, and Duration. 
It offers **composable asynchronous sequence APIs—Flux (for [N] elements) and Mono (for [0|1] elements)**—and extensively implements **the [Reactive Streams](https://www.reactive-streams.org/) specification**.

Reactor是JVM的一个**完全无阻塞的反应式编程基础设施/库**，具有高效的需求管理（以管理“**背压**”的形式）。
它直接与Java 8函数式API集成，特别是CompletableFuture、Stream和Duration。
它提供了**可组合的异步序列API—Flux（用于[N]个元素）和Mono（用于[0|1]个元素）**—并且广泛地实现了**Reactive Streams规范**。

Reactor also supports **non-blocking inter-process communication** with the reactor-netty project. 
Suited for **Microservices Architecture**, Reactor Netty offers **backpressure-ready network engines** 
for HTTP (including Websockets), TCP, and UDP. Reactive encoding and decoding are fully supported.

Reactor还支持与reactor-netty项目的**无阻塞进程间通信**。
Reactor Netty适用于**微服务架构/体系结构**，为HTTP（包括Websockets）、TCP和UDP提供**背压就绪的网络引擎**。完全支持反应式编码和解码。

### 2.4. Getting Reactor

#### 2.4.1. Maven Installation


## 3. Introduction to Reactive Programming
Reactor is an implementation of the **Reactive Programming paradigm**, which can be summed up as follows:

Reactor是**反应式编程范式**的一种实现，可以概括如下：

> **Reactive programming is an asynchronous programming paradigm concerned with data streams and the propagation of change.**
> This means that it becomes possible to express static (e.g. arrays) or dynamic (e.g. event emitters) data streams with ease via the employed programming language(s).
> 
> **反应式编程是一种异步编程范式，涉及数据流和变化的传播**。
> 这意味着可以通过所采用的编程语言轻松地表达静态（数组）或动态（事件发射器）数据流。
> 
> — https://en.wikipedia.org/wiki/Reactive_programming

As a first step in the direction of reactive programming, Microsoft created the Reactive Extensions (Rx) library in the .NET ecosystem. 
Then RxJava implemented reactive programming on the JVM. As time went on, 
a standardization for Java emerged through the **Reactive Streams** effort, 
a specification that defines **a set of interfaces and interaction rules** for reactive libraries on the JVM. 
Its interfaces have been integrated into Java 9 under the Flow class.

作为反应式编程方向的第一步，微软在.NET生态系统中创建了反应式扩展（Rx）库。
然后，RxJava在JVM上实现了反应式编程。随着时间的推移，
Java的标准化通过Reactive Streams工作而出现，该规范为JVM上的反应式库定义了**一组接口和交互规则**。
它的接口已经集成到Java 9的Flow类中。

The **reactive programming paradigm** is often presented in object-oriented languages as **an extension of the Observer design pattern**. 
You can also compare the main **reactive streams pattern** with the familiar **Iterator design pattern**, 
as there is a duality to the Iterable-Iterator pair in all of these libraries. 
**One major difference is that, while an Iterator is pull-based, reactive streams are push-based**.

**反应式编程范式**通常在面向对象的语言中呈现，作为**观察者设计模式的扩展**。
您还可以将主要的**反应式数据流模式**与熟悉的**迭代器设计模式**进行比较，因为所有这些库中的**可迭代的迭代器对**都具有二元性。
**一个主要区别是，迭代器是基于拉取的，反应式数据流是基于推送的**。

Using **an iterator is an imperative programming pattern**, even though the method of accessing values is solely the responsibility of the Iterable. 
Indeed, it is up to the developer to choose when to access the next() item in the sequence. 
In **reactive streams**, the equivalent of the above pair is **Publisher-Subscriber**. 
But it is **the Publisher that notifies the Subscriber of newly available values as they come, and this push aspect is the key to being reactive.** 
Also, **operations applied to pushed values** are expressed **declaratively** rather than imperatively: 
**The programmer expresses the logic of the computation rather than describing its exact control flow.**

使用**迭代器是一种命令式编程模式**，即使访问值的方法完全是可迭代对象的责任。实际上，由开发人员选择何时访问序列中的next()项。
在**反应式数据流**中，上述对的等效项是**发布者-订阅者**。但是，**发布者会在新可用值到来时通知订阅者，而这种推送方式是反应式的关键**。
此外，**应用于推送值的操作**以**声明方式**而不是命令式表示：**程序员表达计算的逻辑，而不是描述其确切的控制流**。

In addition to pushing values, the error-handling and completion aspects are also covered in a well defined manner. 
A Publisher can push new values to its Subscriber (by calling onNext) but can also signal an error (by calling onError) or completion (by calling onComplete). 
Both errors and completion terminate the sequence. This can be summed up as follows:

```shell
onNext x 0..N [onError | onComplete]
```

This approach is very flexible. The pattern supports use cases where there is no value, one value, or n values (including an infinite sequence of values, such as the continuing ticks of a clock).

But why do we need such an asynchronous reactive library in the first place?

### 3.1. Blocking Can Be Wasteful
Modern applications can reach huge numbers of concurrent users, and, even though the capabilities of modern hardware have continued to improve, performance of modern software is still a key concern.

There are, broadly, two ways one can improve a program’s performance:

parallelize to use more threads and more hardware resources.

seek more efficiency in how current resources are used.

Usually, Java developers write programs by using blocking code. This practice is fine until there is a performance bottleneck. Then it is time to introduce additional threads, running similar blocking code. But this scaling in resource utilization can quickly introduce contention and concurrency problems.

Worse still, blocking wastes resources. If you look closely, as soon as a program involves some latency (notably I/O, such as a database request or a network call), resources are wasted because threads (possibly many threads) now sit idle, waiting for data.

So the parallelization approach is not a silver bullet. It is necessary to access the full power of the hardware, but it is also complex to reason about and susceptible to resource wasting.

### 3.2. Asynchronicity to the Rescue?
The second approach mentioned earlier, seeking more efficiency, can be a solution to the resource wasting problem. By writing asynchronous, non-blocking code, you let the execution switch to another active task that uses the same underlying resources and later comes back to the current process when the asynchronous processing has finished.

But how can you produce asynchronous code on the JVM? Java offers two models of asynchronous programming:

Callbacks: Asynchronous methods do not have a return value but take an extra callback parameter (a lambda or anonymous class) that gets called when the result is available. A well known example is Swing’s EventListener hierarchy.

Futures: Asynchronous methods immediately return a Future<T>. The asynchronous process computes a T value, but the Future object wraps access to it. The value is not immediately available, and the object can be polled until the value is available. For instance, an ExecutorService running Callable<T> tasks use Future objects.

Are these techniques good enough? Not for every use case, and both approaches have limitations.

Callbacks are hard to compose together, quickly leading to code that is difficult to read and maintain (known as “Callback Hell”).

### 3.3. From Imperative to Reactive Programming
Reactive libraries, such as Reactor, aim to address these drawbacks of “classic” asynchronous approaches on the JVM while also focusing on a few additional aspects:

- **Composability** and **readability**

- Data as a **flow** manipulated with a rich vocabulary of **operators**

- Nothing happens until you **subscribe**

- **Backpressure** or the ability for the consumer to signal the producer that the rate of emission is too high

- **High level** but **high value** abstraction that is concurrency-agnostic

#### 3.3.1. Composability and Readability
By “composability”, we mean the ability to orchestrate multiple asynchronous tasks, in which we use results from previous tasks to feed input to subsequent ones. Alternatively, we can run several tasks in a fork-join style. In addition, we can reuse asynchronous tasks as discrete components in a higher-level system.

The ability to orchestrate tasks is tightly coupled to the readability and maintainability of code. As the layers of asynchronous processes increase in both number and complexity, being able to compose and read code becomes increasingly difficult. As we saw, the callback model is simple, but one of its main drawbacks is that, for complex processes, you need to have a callback executed from a callback, itself nested inside another callback, and so on. That mess is known as “Callback Hell”. As you can guess (or know from experience), such code is pretty hard to go back to and reason about.

Reactor offers rich composition options, wherein code mirrors the organization of the abstract process, and everything is generally kept at the same level (nesting is minimized).

#### 3.3.2. The Assembly Line Analogy
You can think of data processed by a reactive application as moving through an assembly line. Reactor is both the conveyor belt and the workstations. The raw material pours from a source (the original Publisher) and ends up as a finished product ready to be pushed to the consumer (or Subscriber).

The raw material can go through various transformations and other intermediary steps or be part of a larger assembly line that aggregates intermediate pieces together. If there is a glitch or clogging at one point (perhaps boxing the products takes a disproportionately long time), the afflicted workstation can signal upstream to limit the flow of raw material.

#### 3.3.3. Operators
In Reactor, operators are the workstations in our assembly analogy. Each operator adds behavior to a Publisher and wraps the previous step’s Publisher into a new instance. The whole chain is thus linked, such that data originates from the first Publisher and moves down the chain, transformed by each link. Eventually, a Subscriber finishes the process. Remember that nothing happens until a Subscriber subscribes to a Publisher, as we will see shortly.

> Understanding that operators create new instances can help you avoid a common mistake that would lead you to believe that an operator you used in your chain is not being applied. See this item in the FAQ.

While the Reactive Streams specification does not specify operators at all, one of the best added values of reactive libraries, such as Reactor, is the rich vocabulary of operators that they provide. These cover a lot of ground, from simple transformation and filtering to complex orchestration and error handling.

#### 3.3.4. Nothing Happens Until You subscribe()
In Reactor, when you write a Publisher chain, data does not start pumping into it by default. Instead, you create an abstract description of your asynchronous process (which can help with reusability and composition).

By the act of subscribing, you tie the Publisher to a Subscriber, which triggers the flow of data in the whole chain. This is achieved internally by a single request signal from the Subscriber that is propagated upstream, all the way back to the source Publisher.

#### 3.3.5. Backpressure
Propagating signals upstream is also used to implement **backpressure**, which we described in the assembly line analogy as a feedback signal sent up the line when a workstation processes more slowly than an upstream workstation.

The real mechanism defined by the Reactive Streams specification is pretty close to the analogy: A subscriber can work in unbounded mode and let the source push all the data at its fastest achievable rate or it can use the request mechanism to signal the source that it is ready to process at most n elements.

Intermediate operators can also change the request in-transit. Imagine a buffer operator that groups elements in batches of ten. If the subscriber requests one buffer, it is acceptable for the source to produce ten elements. Some operators also implement **prefetching** strategies, which avoid request(1) round-trips and is beneficial if producing the elements before they are requested is not too costly.

This transforms the push model into a **push-pull hybrid**, where the downstream can pull n elements from upstream if they are readily available. But if the elements are not ready, they get pushed by the upstream whenever they are produced.

#### 3.3.6. Hot vs Cold
The Rx family of reactive libraries distinguishes two broad categories of reactive sequences: **hot** and **cold**. This distinction mainly has to do with how the reactive stream reacts to subscribers:

- A **Cold** sequence starts anew for each Subscriber, including at the source of data. For example, if the source wraps an HTTP call, a new HTTP request is made for each subscription.

- A **Hot** sequence does not start from scratch for each Subscriber. Rather, late subscribers receive signals emitted after they subscribed. Note, however, that some hot reactive streams can cache or replay the history of emissions totally or partially. From a general perspective, a hot sequence can even emit when no subscriber is listening (an exception to the “nothing happens before you subscribe” rule).

For more information on hot vs cold in the context of Reactor, see this reactor-specific section.


## 4. Reactor Core Features
The Reactor project main artifact is reactor-core, a reactive library that focuses on the Reactive Streams specification and targets Java 8.

Reactor introduces composable reactive types that implement Publisher but also provide a rich vocabulary of operators: Flux and Mono. A Flux object represents a reactive sequence of 0..N items, while a Mono object represents a single-value-or-empty (0..1) result.

This distinction carries a bit of semantic information into the type, indicating the rough cardinality of the asynchronous processing. For instance, an HTTP request produces only one response, so there is not much sense in doing a count operation. Expressing the result of such an HTTP call as a Mono<HttpResponse> thus makes more sense than expressing it as a Flux<HttpResponse>, as it offers only operators that are relevant to a context of zero items or one item.

Operators that change the maximum cardinality of the processing also switch to the relevant type. For instance, the count operator exists in Flux, but it returns a Mono<Long>.

### 4.1. Flux, an Asynchronous Sequence of 0-N Items
The following image shows how a Flux transforms items:

![](../../images/1.flux.svg)

A Flux<T> is a standard Publisher<T> that represents an asynchronous sequence of 0 to N emitted items, optionally terminated by either a completion signal or an error. As in the Reactive Streams spec, these three types of signal translate to calls to a downstream Subscriber’s onNext, onComplete, and onError methods.

With this large scope of possible signals, Flux is the general-purpose reactive type. Note that all events, even terminating ones, are optional: no onNext event but an onComplete event represents an empty finite sequence, but remove the onComplete and you have an infinite empty sequence (not particularly useful, except for tests around cancellation). Similarly, infinite sequences are not necessarily empty. For example, Flux.interval(Duration) produces a Flux<Long> that is infinite and emits regular ticks from a clock.

### 4.2. Mono, an Asynchronous 0-1 Result
The following image shows how a Mono transforms an item:

![](../../images/2.mono.svg)

A Mono<T> is a specialized Publisher<T> that emits at most one item via the onNext signal then terminates with an onComplete signal (successful Mono, with or without value), or only emits a single onError signal (failed Mono).

Most Mono implementations are expected to immediately call onComplete on their Subscriber after having called onNext. Mono.never() is an outlier: it doesn’t emit any signal, which is not technically forbidden although not terribly useful outside of tests. On the other hand, a combination of onNext and onError is explicitly forbidden.

Mono offers only a subset of the operators that are available for a Flux, and some operators (notably those that combine the Mono with another Publisher) switch to a Flux. For example, Mono#concatWith(Publisher) returns a Flux while Mono#then(Mono) returns another Mono.

Note that you can use a Mono to represent no-value asynchronous processes that only have the concept of completion (similar to a Runnable). To create one, you can use an empty Mono<Void>.

### 4.3. Simple Ways to Create a Flux or Mono and Subscribe to It
The easiest way to get started with Flux and Mono is to use one of the numerous factory methods found in their respective classes.

