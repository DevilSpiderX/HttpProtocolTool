package DevilSpiderX.httpProtocolTool.response;

import DevilSpiderX.httpProtocolTool.lang.Bytes;

/**
 * 响应体类
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */
public class Body {
    private final Bytes value;

    public Body() {
        value = new Bytes();
    }

    public Body(byte[] bytes) {
        value = new Bytes(bytes);
    }

    /**
     * 将指定的字节数组添加到响应体里去
     *
     * @param bytes 被添加的字节数组
     */
    public void add(byte[] bytes) {
        value.append(bytes);
    }

    /**
     * 将指定的字节数组按给定的范围添加到响应体里去
     *
     * @param bytes 被添加的字节数组
     * @param off   偏移量
     * @param len   长度
     */
    public void add(byte[] bytes, int off, int len) {
        value.append(bytes, off, len);
    }

    /**
     * 清空响应体的数据
     */
    public void clean() {
        value.delete(0, value.length());
    }

    /**
     * 获取响应体的值
     * 返回一个字节数组
     *
     * @return 以字节数组为形式的值
     */
    public byte[] get() {
        return value.toByteArray();
    }

    /**
     * 获取响应体的值
     * 返回一个字节串
     *
     * @return 以字节串为形式的值
     */
    public Bytes getValue() {
        return value;
    }

    /**
     * 获取响应体大小
     *
     * @return 所包含的字节串的长度
     */
    public int size() {
        return value.length();
    }

}
