<h1 align="center"">HTTP协议工具</h1>
<p align="center">用于解析请求报文，构造响应报文</p>
<p align="center">与SOCKET或者CHANNEL配合实现HTTP协议的服务器</p>

# 解析请求报文

- **Handler**</br>
  `调用parseHandler(String message)静态方法，解析请求报文返回相应的Handler对象`</br>
  `message为接收的请求报文。`
- GetHandler对象</br>
  `用于解析GET请求。`
- PostHandler对象</br>
  `用于解析POST请求。`

# 构造响应报文

- **Response**</br>
  `响应的主体。需设置好响应状态行，响应头和响应体。`</br>
  `默认的编码形式为UTF-8。`</br>
  `调用toString()方法，返回字符串形式的响应报文。`</br>
  `调用toBytes()方法，返回二进制形式的响应报文。`
- StatusLine</br>
  `响应状态行`</br>
  `内置大部分状态行。`
- Header</br>
  `响应头`
- Body</br>
  `响应体`

# 依赖的开源项目

- <a href="https://github.com/alibaba/fastjson">fastjson</a>
