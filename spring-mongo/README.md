需要注意的问题

1. Mongo 和 应用实例的时区.
    1. 如果应用实例和Mongo实例时区不一样，那么需要再查询的时候，或者查询出来的时候，你使用的时间是和 Mongo 实例的时间是要加上时区差的。

2. 如果使用 MongoTemplate ，那么需要注意实体的 id 字段，是否加上了 @Field("id")

3. Amazon DocumentDB 不支持某些关键字，例如 “$where”

4. Spring 连接 MongoDB 集群的方式

    - 一种：
      ```
      spring.data.mongodb.uri = mongodb://[ip1]:27017,[ip2]:27017,[ip3]:27017/[database]
      ```

    - 另一种
      ```
      spring.data.mongodb.host = 127.0.0.1
      spring.data.mongodb.port = 27017
      spring.data.mongodb.database = 
      spring.data.mongodb.username = 
      spring.data.mongodb.password = 
      ```

     