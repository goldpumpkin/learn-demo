### 根据 .proto 文件，生成 Java Model

- Mac install Protobuf  ` brew install protobuf`
- 在与 java 同等级目录下，创建 proto 文件夹，并将约定的 .proto 文件放于此文件夹下
- 执行 `protoc --java_out=src/main/java src/main/proto/quote.proto  `  即可生成 java 文件