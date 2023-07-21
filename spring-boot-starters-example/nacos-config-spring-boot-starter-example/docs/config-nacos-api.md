

## 动态配置

http://localhost:8080/config/nacos

```yaml

dynamic-config: 'yml-demo'
actuator-endpoint: 'nacos-config'

```


### 1.配置属性值
http://localhost:8080/config/nacos/value

```json

```

配置变更
```yaml

dynamic:
  config:
    enable: true

```

```json
false

```

```json
true

```


### 2.配置属性集
http://localhost:8080/config/nacos/properties

```json
{
    "dynamicConfig":"yml-demo-dev",
    "configNamespace":"nacos-config-dev"
}

```

配置变更
```yaml

dynamic:
  config:
    enable: true

dynamic-config: 'yml-demo-dev-dynamic'
config-namespace: 'nacos-config-dev'

```

```json
{
    "dynamicConfig":"yml-demo-dev-dynamic",
    "configNamespace":"nacos-config-dev"
}

```


### 3.配置属性
> 配置监视器

http://localhost:8080/config/nacos/value/listener

```json
0

```

配置变更
```yaml

dynamic:
  config:
    enable: true

dynamic-config: 'yml-demo-dev-dynamic'
config-namespace: 'nacos-config-dev'

people:
  count: 1

```

```json
1

```

