

RocketMQ消息实战
======
> https://rocketmq.apache.org/zh/
>
> https://github.com/apache/rocketmq

RocketMQ 5.0：云原生“消息、事件、流”实时数据处理平台，覆盖云边端一体化数据处理场景


# 启动NameServer
```shell
# 首先启动NameServer
nohup sh bin/mqnamesrv > logs/mqnamesrv.log 2>&1 &

# 查看日志，确认是否成功
tail -f logs/mqnamesrv.log
The Name Server boot success. serializeType=JSON, address 0.0.0.0:9876
```


# 启动Broker+Proxy
```shell
nohup sh bin/mqbroker -n localhost:9876 --enable-proxy > logs/broker-a.log 2>&1 &

# 查看日志，确认是否成功
tail -f logs/broker-a.log
Wed Dec 27 13:50:45 CST 2023 rocketmq-proxy startup successfully
```


# 启动Dashboard
> [部署 & 运维 > RocketMQ Dashboard](https://rocketmq.apache.org/zh/docs/deploymentOperations/04Dashboard/)

```properties
server.port=8083

rocketmq.config.namesrvAddr=localhost:9876
rocketmq.config.isVIPChannel=true
rocketmq.config.timeoutMillis=5000
```

http://localhost:8083/


# 关闭
```shell
sh bin/mqshutdown broker
sh bin/mqshutdown proxy

sh bin/mqshutdown namesrv
```


# 参考引用
* [快速开始](https://rocketmq.apache.org/zh/docs/quickStart/01quickstart)
* [部署方式](https://rocketmq.apache.org/zh/docs/deploymentOperations/01deploy/)
* [最佳实践](https://rocketmq-1.gitbook.io/rocketmq-connector/kai-fa-zhe-zhong-xin/zui-jia-shi-jian)

