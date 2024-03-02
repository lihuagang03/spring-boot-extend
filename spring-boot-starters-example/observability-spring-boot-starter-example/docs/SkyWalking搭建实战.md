

SkyWalking搭建实战
======
> [SkyWalking 极简入门](https://skywalking.apache.org/zh/2020-04-19-skywalking-quick-start/)


# SkyWalking 启动
```shell
cd /path/to/apache-skywalking-apm-bin
#cd ~/Documents/SkyWalking/apache-skywalking-apm-bin
nohup bin/startup.sh &

```

## SkyWalking OAP 启动
```shell
nohup bin/oapService.sh > oapService.log 2>&1 &

tail -f logs/skywalking-oap-server.log
```

## SkyWalking UI 启动
```shell
nohup bin/webappService.sh > webappService.log 2>&1 &

tail -f logs/skywalking-webapp.log
Serving HTTP at /0:0:0:0:0:0:0:0%0:8080 - http://127.0.0.1:8080/
```


# SkyWalking 控制台
http://localhost:8080/
http://localhost:8080/General-Service/Services


- [Setup java agent](https://skywalking.apache.org/docs/skywalking-java/next/en/setup/service-agent/java-agent/readme/)

