

http://localhost:38081
http://localhost:38081/metrics

```yaml
management:
  # ManagementServerProperties
  server:
    port: 38081
    base-path: ""
  endpoints:
    # WebEndpointProperties
    web:
      exposure:
        include: "*"
        exclude: "metrics"
      base-path: ""
      path-mapping:
        "/prometheus": "/metrics"
  # MetricsProperties
  metrics:
    use-global-registry: true
    enable:
      heapdump: false
      threaddump: false
    tags:
      application: "${spring.application.name}"
    export:
      # JmxProperties
      jmx:
        enabled: true
        domain: "metrics"
        step: 1m
      # PrometheusProperties
      prometheus:
        enabled: true
#        descriptions: true
#        pushgateway:
#          enabled: false
#          push-rate: 1m
#          shutdown-operation: push
        step: 1m

```

```json
{
  "_links":{
    "self":{
      "href":"http://localhost:38081",
      "templated":false
    },
    "prometheus":{
      "href":"http://localhost:38081/metrics",
      "templated":false
    },
    "beans":{
      "href":"http://localhost:38081/beans",
      "templated":false
    },
    "caches":{
      "href":"http://localhost:38081/caches",
      "templated":false
    },
    "caches-cache":{
      "href":"http://localhost:38081/caches/{cache}",
      "templated":true
    },
    "health-path":{
      "href":"http://localhost:38081/health/{*path}",
      "templated":true
    },
    "health":{
      "href":"http://localhost:38081/health",
      "templated":false
    },
    "info":{
      "href":"http://localhost:38081/info",
      "templated":false
    },
    "conditions":{
      "href":"http://localhost:38081/conditions",
      "templated":false
    },
    "configprops":{
      "href":"http://localhost:38081/configprops",
      "templated":false
    },
    "configprops-prefix":{
      "href":"http://localhost:38081/configprops/{prefix}",
      "templated":true
    },
    "env-toMatch":{
      "href":"http://localhost:38081/env/{toMatch}",
      "templated":true
    },
    "env":{
      "href":"http://localhost:38081/env",
      "templated":false
    },
    "loggers-name":{
      "href":"http://localhost:38081/loggers/{name}",
      "templated":true
    },
    "loggers":{
      "href":"http://localhost:38081/loggers",
      "templated":false
    },
    "heapdump":{
      "href":"http://localhost:38081/heapdump",
      "templated":false
    },
    "threaddump":{
      "href":"http://localhost:38081/threaddump",
      "templated":false
    },
    "scheduledtasks":{
      "href":"http://localhost:38081/scheduledtasks",
      "templated":false
    },
    "mappings":{
      "href":"http://localhost:38081/mappings",
      "templated":false
    }
  }
}

```

