

Nacos 领域模型
======
> https://nacos.io/zh-cn/docs/v2/architecture.html


## 领域模型
### 数据模型
**Nacos 数据模型 Key 由三元组唯一确定**, **Namespace**默认是空串，公共命名空间（public），**分组**默认是 DEFAULT_GROUP。

数据模型三元组：Namespace、Group、Service/DataId

![数据模型](images/6.数据模型.jpeg)

### 服务领域模型

服务三元组：Namespace、Group、Service

![服务领域模型](images/7.服务领域模型.jpeg)

### 配置领域模型
围绕配置，主要有两个关联的实体，一个是**配置变更历史**，一个是**服务标签**（用于**打标分类，方便索引**），由 ID 关联。

配置三元组：Namespace、Group、DataId

![配置领域模型](images/8.配置领域模型.jpeg)

Config 配置信息表：id、namespace、group、dataId、content

History 配置变更历史表：id、nid、content、modifyUser、modifyTime、modifyIp

Tag 服务标签表：id、nid、tagName、tagType

