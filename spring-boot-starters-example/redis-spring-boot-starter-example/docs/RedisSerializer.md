

Redis对象序列化/反序列化
======
> `org.springframework.data.redis.serializer.RedisSerializer<T>`


### RedisSerializer.java()
> new JdkSerializationRedisSerializer(null)
>
> 不推荐，值可读性差且又长！

```shell
localhost:6379> GET "user:user-info::123456"
"\xac\xed\x00\x05sr\x00+com.spring.boot.redis.example.model.UserDto\xb7\x8a\xefY\xa8\xccS`
\x02\x00\x03L\x00\x02idt\x00\x10Ljava/lang/Long;
L\x00\bnickNamet\x00\x12Ljava/lang/String;
L\x00\x05phoneq\x00~\x00\x02xpsr\x00\x0ejava.lang.Long;
\x8b\xe4\x90\xcc\x8f#\xdf\x02\x00\x01J\x00\x05
valuexr\x00\x10java.lang.Number\x86\xac\x95\x1d\x0b\x94\xe0\x8b\x02\x00\x00xp\x00\x00\x00\x00\x00\x01\xe2@t\x00\x06\xe6\x9d\x8e\xe5\x9b\x9bt\x00\x0b13666555888"
```


### RedisSerializer.string()
> StringRedisSerializer.UTF_8
> new StringRedisSerializer(StandardCharsets.UTF_8)


## Jackson
### RedisSerializer.json()
> new GenericJackson2JsonRedisSerializer()
>
> 推荐

类型信息
```shell
localhost:6379> GET "user:user-info::123456"
"{\"@class\":\"com.spring.boot.redis.example.model.UserDto\",\"id\":123456,\"phone\":\"13666555888\",\"nickName\":\"\xe6\x9d\x8e\xe5\x9b\x9b\"}"
```


### ~~Jackson2JsonRedisSerializer~~
> new Jackson2JsonRedisSerializer<>(Object.class))
>
> 不推荐，有坑！

类型信息丢失，读取时对象类型转换失败
```shell
localhost:6379> GET "user:user-info::123456"
"{\"id\":123456,\"phone\":\"13666555888\",\"nickName\":\"\xe6\x9d\x8e\xe5\x9b\x9b\"}"
```
```shell
java.lang.ClassCastException: class java.util.LinkedHashMap cannot be cast to class com.spring.boot.redis.example.model.UserDto
```


## FastJson
### GenericFastJsonRedisSerializer
> new GenericFastJsonRedisSerializer()
>
> 推荐

类型信息
```shell
localhost:6379> GET "user:user-info::123456"
"{\"@type\":\"com.spring.boot.redis.example.model.UserDto\",\"id\":123456L,\"nickName\":\"\xe6\x9d\x8e\xe5\x9b\x9b\",\"phone\":\"13666555888\"}"
```


### ~~FastJsonRedisSerializer~~
> new FastJsonRedisSerializer<>(Object.class))
> 
> 不推荐，有坑！

类型信息丢失，读取时对象类型转换失败
```shell
localhost:6379> GET "user:user-info::123456"
"{\"id\":123456,\"nickName\":\"\xe6\x9d\x8e\xe5\x9b\x9b\",\"phone\":\"13666555888\"}"
```
```shell
java.lang.ClassCastException: class com.alibaba.fastjson.JSONObject cannot be cast to class com.spring.boot.redis.example.model.UserDto
```

