package DevilSpiderX.httpProtocolTool.handle;

import com.alibaba.fastjson.JSONObject;

/**
 * URL参数类
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */
public class Query {
    private final JSONObject attributes;

//    public Query() {
//        attributes = new JSONObject();
//    }

    public Query(String queryText) {
        attributes = new JSONObject();
        if (queryText == null) return;
//        try {
//            queryText = URLDecoder.decode(queryText, StandardCharsets.UTF_8.name());
        String[] queryArray = queryText.split("&");
        for (String query : queryArray) {
            String[] nAv = query.split("=");
            if (nAv.length != 2) continue;
            String key = nAv[0];
            String value = nAv[1];
            attributes.put(key, value);
        }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 获取URL所有的参数名
     *
     * @return 一个数组，包含URL所有参数名
     */
    public String[] getKeys() {
        return attributes.keySet().toArray(new String[0]);
    }

    /**
     * 获取URL参数
     *
     * @param key 参数名
     * @return URL参数
     */
    public String get(String key) {
        return attributes.getString(key);
    }

    /**
     * 判断是否存在该URL参数名
     *
     * @param key 要判断的参数名
     * @return 如果存在该URL参数名，则返回true；否则，返回false
     */
    public boolean contains(String key) {
        return attributes.containsKey(key);
    }
}
