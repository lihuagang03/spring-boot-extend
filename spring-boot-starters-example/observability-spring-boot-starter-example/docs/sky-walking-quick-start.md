

SkyWalking 搭建
======
> [SkyWalking 极简入门](https://skywalking.apache.org/zh/2020-04-19-skywalking-quick-start/)


# SkyWalking 启动
```shell
cd /path/to/apache-skywalking-apm-bin
#cd ~/Documents/SkyWalking/apache-skywalking-apm-bin
bin/startup.sh

```

## SkyWalking OAP 启动
```shell
bin/oapService.sh
nohup bin/oapService.sh >oap-nohup.out 2>&1 &

```

## SkyWalking UI 启动
```shell
bin/oapService.sh
nohup bin/webappService.sh >webapp-nohup.out 2>&1 &

```

# SkyWalking 控制台
http://127.0.0.1:8080/
http://127.0.0.1:8080/General-Service/Services


- [Setup java agent](https://skywalking.apache.org/docs/skywalking-java/next/en/setup/service-agent/java-agent/readme/)

