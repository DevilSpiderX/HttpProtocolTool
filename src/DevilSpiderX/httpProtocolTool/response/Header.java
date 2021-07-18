package DevilSpiderX.httpProtocolTool.response;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 响应头类
 * 默认添加"Data"参数
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */
public class Header {
    private final Map<String, String> attributeMap;

    public Header() {
        attributeMap = new HashMap<>();
        LocalDateTime date = LocalDateTime.now(ZoneId.of("GMT"));
        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("E, dd LLL yyyy HH:mm:ss", Locale.ENGLISH);
        attributeMap.put("Date", date.format(dateFormatter) + " GMT");
    }

    /**
     * 添加响应头参数
     *
     * @param name  参数名
     * @param value 参数值
     */
    public void add(String name, String value) {
        attributeMap.put(name, value);
    }

    /**
     * 添加整型响应头参数
     *
     * @param name  参数名
     * @param value 参数值
     */
    public void addInt(String name, int value) {
        attributeMap.put(name, String.valueOf(value));
    }

    /**
     * 添加长整型响应头参数
     *
     * @param name  参数名
     * @param value 参数值
     */
    public void addDate(String name, long value) {
        attributeMap.put(name, String.valueOf(value));
    }

    /**
     * 删除响应头参数
     *
     * @param name 参数名
     * @return boolean
     */
    public boolean remove(String name) {
        try {
            attributeMap.remove(name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 清空响应头
     *
     * @return boolean
     */
    public boolean clean() {
        try {
            attributeMap.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder headerStringBuilder = new StringBuilder();
        for (String attribute : attributeMap.keySet()) {
            headerStringBuilder.append(attribute).append(":").append(attributeMap.get(attribute)).append("\r\n");
        }
        return headerStringBuilder.toString();
    }
}
