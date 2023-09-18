

http://localhost:38081/actuator
http://localhost:38081/actuator/metrics

```yaml

management:
  # ManagementServerProperties
  server:
    port: 38081
#    base-path: ""
#    base-path: "/management"
  endpoints:
    # WebEndpointProperties
    web:
      exposure:
        include: "*"
        exclude: "heapdump,threaddump"
#      base-path: "/actuator"
      # Customizing the Management Endpoint Paths
#      path-mapping:
#        "metrics": "metrics"
  # MetricsProperties
  metrics:
    use-global-registry: true
#    enable:
#      heapdump: false
#      threaddump: false
    tags:
      application: "${spring.application.name}"
    export:
      # JmxProperties
      jmx:
        enabled: true
        domain: "metrics"
        step: 1m

```

```json
{
  "_links":{
    "self":{
      "href":"http://localhost:38081/actuator",
      "templated":false
    },
    "beans":{
      "href":"http://localhost:38081/actuator/beans",
      "templated":false
    },
    "caches":{
      "href":"http://localhost:38081/actuator/caches",
      "templated":false
    },
    "caches-cache":{
      "href":"http://localhost:38081/actuator/caches/{cache}",
      "templated":true
    },
    "health":{
      "href":"http://localhost:38081/actuator/health",
      "templated":false
    },
    "health-path":{
      "href":"http://localhost:38081/actuator/health/{*path}",
      "templated":true
    },
    "info":{
      "href":"http://localhost:38081/actuator/info",
      "templated":false
    },
    "conditions":{
      "href":"http://localhost:38081/actuator/conditions",
      "templated":false
    },
    "configprops-prefix":{
      "href":"http://localhost:38081/actuator/configprops/{prefix}",
      "templated":true
    },
    "configprops":{
      "href":"http://localhost:38081/actuator/configprops",
      "templated":false
    },
    "env-toMatch":{
      "href":"http://localhost:38081/actuator/env/{toMatch}",
      "templated":true
    },
    "env":{
      "href":"http://localhost:38081/actuator/env",
      "templated":false
    },
    "loggers-name":{
      "href":"http://localhost:38081/actuator/loggers/{name}",
      "templated":true
    },
    "loggers":{
      "href":"http://localhost:38081/actuator/loggers",
      "templated":false
    },
    "heapdump":{
      "href":"http://localhost:38081/actuator/heapdump",
      "templated":false
    },
    "threaddump":{
      "href":"http://localhost:38081/actuator/threaddump",
      "templated":false
    },
    "metrics-requiredMetricName":{
      "href":"http://localhost:38081/actuator/metrics/{requiredMetricName}",
      "templated":true
    },
    "metrics":{
      "href":"http://localhost:38081/actuator/metrics",
      "templated":false
    },
    "scheduledtasks":{
      "href":"http://localhost:38081/actuator/scheduledtasks",
      "templated":false
    },
    "mappings":{
      "href":"http://localhost:38081/actuator/mappings",
      "templated":false
    }
  }
}

```

