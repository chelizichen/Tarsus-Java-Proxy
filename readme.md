# @Tarsus/Java Http服务模块

## 整合的仓库
- [@Tarsus/Node](https://github.com/chelizichen/Tarsus) 包含 Http服务  微服务模块 依赖注入 ORM 命令行 等多个开发包的库
- [@Tarsus/Java-Proxy](https://github.com/chelizichen/Tarsus-Java-Proxy) SpringBoot，可以提供Http 服务，也可以调用微服务
- [@Tarsus/Java](https://github.com/chelizichen/Tarsus-Java) Java 微服务模块 示例代码


## 说明

````TXT
使用 Java 作为 中间层 可以使用 Http 服务 也可以调用 Ado—Node 的微服务
````

## 微服务架构模型

```mermaid
graph TD

  A1[Web App] -->|请求| B1[TarsusNodeHttpServer]
  A1[Web App] -->|请求| B2[TarsusJavaHttpServer]

  B1 -->|响应| A1
  B2 -->|响应| A1
  
  B1 ---|转发请求-响应| C(TarsusProxyServer)
  B2 ---|转发请求-响应| C(TarsusProxyServer)
  


  C ---|请求-响应| D[TarsusNodeMicroServer]
  C ---|请求-响应| E[TarsusJavaMicroServer]

```

