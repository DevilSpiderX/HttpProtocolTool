package DevilSpiderX.httpProtocolTool.handle;


import com.alibaba.fastjson.JSONObject;

/**
 * 请求头类
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */
public class Header {
    private final JSONObject attributes;

    public Header(String headerMsg) {
        attributes = new JSONObject();
        String[] headers = headerMsg.split("\r\n|\n");
        for (String header : headers) {
            if (header.contains(":") || header.contains(": ")) {
                String[] nAv = header.split(": |:", 2);
                String key = nAv[0].toLowerCase();
                String value = nAv[1];
                attributes.put(key, value);
                continue;
            }
            if (header.equals("")) break;
        }

    }
//
//    public Header(List<String> headers) {
//        attributes = new JSONObject();
//        for (String header : headers) {
//            String[] nAv = header.split(": |:", 2);
//            String key = nAv[0].toLowerCase();
//            String value = nAv[1];
//            attributes.put(key, value);
//        }
//    }

    /**
     * 获取请求头所有参数名
     *
     * @return 一个数组，包含请求头所有参数名
     */
    public String[] getKeys() {
        return attributes.keySet().toArray(new String[0]);
    }

    /**
     * 获取请求头参数
     *
     * @param key 参数名
     * @return 请求头参数
     */
    public String getAttribute(String key) {
        return attributes.getString(key.toLowerCase());
    }

    /**
     * 判断是否存在该请求头参数名
     *
     * @param key 要判断的参数名
     * @return 如果存在该请求头参数名，则返回true；否则，返回false
     */
    public boolean contains(String key) {
        return attributes.containsKey(key.toLowerCase());
    }
}

