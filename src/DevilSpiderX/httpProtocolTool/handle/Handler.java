package DevilSpiderX.httpProtocolTool.handle;

import DevilSpiderX.httpProtocolTool.exception.RequestMethodUnsupportedException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 请求处理类
 * 所有请求处理类的父类
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */
public abstract class Handler {
    Method method;

    /**
     * 解析请求报文并返回相应类型的请求处理类
     *
     * @param msg 请求报文
     * @return 相应类型的请求处理类
     * @throws RequestMethodUnsupportedException 不支持请求报文<code>msg</code>的类型时会抛出此异常
     */
    public static Handler parseHandler(String msg) throws RequestMethodUnsupportedException {
        try {
            msg = URLDecoder.decode(msg, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (msg.startsWith("GET")) {
            return new GETHandler(msg);
        } else if (msg.startsWith("POST")) {
            return new POSTHandler(msg);
        } else {
            throw new RequestMethodUnsupportedException();
        }
    }

    /**
     * 获取请求的类型
     *
     * @return method 请求的类型
     */
    public Method getMethod() {
        return method;
    }

    /**
     * 获取请求路径
     *
     * @return 请求路径
     */
    public abstract String getPath();

    /**
     * 获取HTTP版本
     *
     * @return HTTP版本
     */
    public abstract String getHTTPVersion();

    /**
     * 获取请求头
     *
     * @return 请求头实例
     */
    public abstract Header getHeader();

    /**
     * 获取请求头所有参数名
     *
     * @return 一个数组，包含请求头所有参数名
     */
    public abstract String[] getHeaderKeys();

    /**
     * 获取请求头参数
     *
     * @param key 参数名
     * @return 请求头参数
     */
    public abstract String getHeaderAttribute(String key);

    /**
     * 判断是否存在该请求头参数名
     *
     * @param key 要判断的参数名
     * @return 如果存在该请求头参数名，则返回true；否则，返回false
     */
    public abstract boolean headerKeyContains(String key);
}
