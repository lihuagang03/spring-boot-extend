

## Nacos动态配置api

http://localhost:8680/config/nacos

配置
```yaml
# 空

```


### 1.配置属性值
http://localhost:8680/config/nacos/value

```json

```

配置变更
```yaml

dynamic:
  config:
    enable: true

```

```json
true

```


### 2.配置属性集
http://localhost:8680/config/nacos/properties

```json

{
  "dynamicConfig":null,
  "configNamespace":null
}

```

配置变更
```yaml

dynamic-config: 'yml-dynamic-config'
config-namespace: 'nacos-config'

```

```json

{
  "dynamicConfig":"yml-dynamic-config",
  "configNamespace":"nacos-config"
}

```


### 3.配置属性
> 配置监视器

http://localhost:8680/config/nacos/value/listener

```json
0

```

配置变更
```yaml

people:
  count: 1

```

```json
1

```

### 完整的配置

配置
```yaml

dynamic:
  config:
    enable: true

dynamic-config: 'yml-dynamic-config'
config-namespace: 'nacos-config'

people:
  count: 1

```

