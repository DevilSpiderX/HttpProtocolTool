package DevilSpiderX.httpProtocolTool.handle;


import DevilSpiderX.httpProtocolTool.lang.Bytes;

import java.net.URI;

/**
 * POST请求处理类
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */
public class POSTHandler extends Handler {
    private Body body;

    public POSTHandler(String msg) {
        method = Method.POST;

        int index, offset = 2;
        if ((index = msg.indexOf("\r\n")) == -1) {
            index = msg.indexOf("\n");
            offset = 1;
        }
        String stateLine = msg.substring(0, index);
        String headerMsg = msg.substring(index + offset);

        String[] states = stateLine.split(" ");
        URI uri = URI.create(states[1]);
        path = uri.getPath();

        HTTPVersion = states[2].substring(5);
        header = new Header(headerMsg);
    }

    /**
     * 解析请求体
     *
     * @param bodyMsg 请求体的内容
     */
    public void parseBody(String bodyMsg) {
        if (header.contains("Content-Type")) {
            body = new Body(header.getAttribute("Content-Type"), bodyMsg);
        } else {
            body = new Body();
        }
    }

    /**
     * 解析请求体
     *
     * @param bodyBytes 请求体的内容，二进制形式
     */
    public void parseBody(Bytes bodyBytes) {
        if (header.contains("Content-Type")) {
            body = new Body(header.getAttribute("Content-Type"), bodyBytes);
        } else {
            body = new Body();
        }
    }

    /**
     * 获取请求体
     *
     * @return 请求体实例
     */
    public Body getBody() {
        return body;
    }

    /**
     * 获取请求体所有参数名
     *
     * @return 一个数组，包含请求体所有参数名
     */
    public String[] getBodyKeys() {
        return body.getKeys();
    }

    /**
     * 获取请求体参数
     *
     * @param key 参数名
     * @return 请求体参数
     */
    public Object getBodyAttribute(String key) {
        return body.get(key);
    }

    /**
     * 获取请求体参数
     *
     * @param index 序号
     * @return 请求体参数
     */
    public Object getBodyAttribute(int index) {
        return body.get(index);
    }

    /**
     * 获取请求体参数
     *
     * @param key 参数名
     * @return 请求体参数
     */
    public String getBodyStringAttribute(String key) {
        return body.getString(key);
    }

    /**
     * 判断是否存在该请求体参数名
     *
     * @param key 要判断的参数名
     * @return 如果存在该请求体参数名，则返回true；否则，返回false
     */
    public boolean bodyKeyContains(String key) {
        return body.contains(key);
    }
}

