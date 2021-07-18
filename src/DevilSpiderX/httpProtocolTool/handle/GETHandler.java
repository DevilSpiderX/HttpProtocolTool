package DevilSpiderX.httpProtocolTool.handle;


import java.net.URI;

/**
 * GET请求处理类
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */
public class GETHandler extends Handler {
    private final String path;
    private final Query query;
    private final String HTTPVersion;
    private final Header header;


    public GETHandler(String msg) {
        method = Method.GET;

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
        query = new Query(uri.getQuery());

        HTTPVersion = states[2].substring(5);
        header = new Header(headerMsg);
    }

    /**
     * 获取请求路径
     *
     * @return 请求路径
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * 获取HTTP版本
     *
     * @return HTTP版本
     */
    @Override
    public String getHTTPVersion() {
        return HTTPVersion;
    }

    /**
     * 获取URL参数类实例
     *
     * @return URL参数实例
     */
    public Query getQuery() {
        return query;
    }

    /**
     * 获取URL所有的参数名
     *
     * @return 一个数组，包含URL所有参数名
     */
    public String[] getQueryKeys() {
        return query.getKeys();
    }

    /**
     * 获取URL参数
     *
     * @param key 参数名
     * @return URL参数
     */
    public String getQueryAttribute(String key) {
        return query.get(key);
    }

    /**
     * 判断是否存在该URL参数名
     *
     * @param key 要判断的参数名
     * @return 如果存在该URL参数名，则返回true；否则，返回false
     */
    public boolean queryKeyContains(String key) {
        return query.contains(key);
    }

    /**
     * 获取请求头
     *
     * @return 请求头实例
     */
    @Override
    public Header getHeader() {
        return header;
    }

    /**
     * 获取请求头所有参数名
     *
     * @return 一个数组，包含请求头所有参数名
     */
    @Override
    public String[] getHeaderKeys() {
        return header.getKeys();
    }

    /**
     * 获取请求头参数
     *
     * @param key 参数名
     * @return 请求头参数
     */
    @Override
    public String getHeaderAttribute(String key) {
        return header.getAttribute(key);
    }

    /**
     * 判断是否存在该请求头参数名
     *
     * @param key 要判断的参数名
     * @return 如果存在该请求头参数名，则返回true；否则，返回false
     */
    @Override
    public boolean headerKeyContains(String key) {
        return header.contains(key);
    }
}

