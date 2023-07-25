

Redis
======
> https://redis.io/

The open source, **in-memory data store** used by millions of developers as **a database, cache, streaming engine, and message broker**.
内存数据存储，用作数据库、缓存、数据流引擎和消息代理。


## Core capabilities 核心功能
### In-memory data structures 内存中的数据结构
Well-known as a "**data structure server**", with support for **strings, hashes, lists, sets, sorted sets, streams**, and more.
**数据结构服务器**，支持**字符串、哈希表、列表、集合、有序集合、数据流**等。

### Programmability 可编程性
Server-side scripting with Lua and server-side stored procedures with Redis Functions.
使用Lua的服务器端脚本和使用Redis函数的服务器端存储过程。

### Extensibility 可扩展性
A module API for building custom extensions to Redis in C, C++, and Rust.
一个模块API，用于在C、C++和Rust中构建Redis的自定义扩展。

### Persistence 持久化
Keeps the dataset in memory for fast access, but can also persist all writes to permanent storage to survive reboots and system failures.
**将数据集保留在内存中以实现快速访问**，但也可以将所有写入永久存储以避免重新启动和系统故障。

### Clustering 集群
**Horizontal scalability with hash-based sharding**, scaling to millions of nodes with **automatic re-partitioning** when growing the cluster.
具有**基于哈希分片的水平可扩展性**，在增长集群时通过自动重新分区可扩展到数百万个节点。

### High availability 高可用性
**Replication with automatic failover** for both standalone and clustered deployments.
具有**自动故障切换功能的复制**，适用于独立部署和群集部署。


## Use cases 使用场景用例
### Real-time data store 实时数据存储
Redis' versatile in-memory data structures enable building data infrastructure for real-time applications that require low latency and high-throughput.

### Caching & session storage 缓存和会话存储
Redis' speed makes it ideal for caching database queries, complex computations, API calls, and session state.

### Streaming & messaging 数据流和消息
The stream data type enables high-rate data ingestion, messaging, event sourcing, and notifications.


## 实战
- [Install Redis on macOS](https://redis.io/docs/getting-started/installation/install-redis-on-mac-os/)

