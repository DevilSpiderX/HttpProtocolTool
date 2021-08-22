package DevilSpiderX.httpProtocolTool.response;


import DevilSpiderX.httpProtocolTool.exception.NullStatus_LineException;
import DevilSpiderX.httpProtocolTool.exception.StatusNoExistException;
import DevilSpiderX.httpProtocolTool.lang.Bytes;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 响应类
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */
public class Response {
    private String status_line;
    private final Header header;
    private final Body body;
    private final Charset charset;

    public Response() {
        header = new Header();
        body = new Body();
        charset = StandardCharsets.UTF_8;
    }

    public Response(int status) {
        try {
            status_line = StatusLine.getStatus_Line(status);
        } catch (StatusNoExistException e) {
            e.printStackTrace();
        }
        header = new Header();
        body = new Body();
        charset = StandardCharsets.UTF_8;
    }

    public Response(Charset charset) {
        header = new Header();
        body = new Body();
        this.charset = charset;
    }

    public Response(String charsetName) {
        header = new Header();
        body = new Body();
        this.charset = Charset.forName(charsetName);
    }

    public Response(int status, Charset charset) {
        try {
            status_line = StatusLine.getStatus_Line(status);
        } catch (StatusNoExistException e) {
            e.printStackTrace();
        }
        header = new Header();
        body = new Body();
        this.charset = charset;
    }

    public Response(int status, String charsetName) {
        try {
            status_line = StatusLine.getStatus_Line(status);
        } catch (StatusNoExistException e) {
            e.printStackTrace();
        }
        header = new Header();
        body = new Body();
        charset = Charset.forName(charsetName);
    }

    /**
     * 设置响应状态
     *
     * @param status 要设定为的状态码
     */
    public void setStatus(int status) {
        try {
            status_line = StatusLine.getStatus_Line(status);
        } catch (StatusNoExistException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加响应头参数
     *
     * @param name  参数名
     * @param value 参数值
     */
    public void addHeader(String name, String value) {
        header.add(name, value);
    }

    /**
     * 添加整型响应头参数
     *
     * @param name  参数名
     * @param value 参数值
     */
    public void addIntHeader(String name, int value) {
        header.addInt(name, value);
    }

    /**
     * 添加长整型响应头参数
     *
     * @param name  参数名
     * @param value 参数值
     */
    public void addDateHeader(String name, long value) {
        header.addDate(name, value);
    }

    /**
     * 设置响应体
     *
     * @param response_body 响应体的内容
     */
    public void addBody(String response_body) {
        body.add(response_body.getBytes(charset));
    }

    /**
     * 从输入流中获取响应体内容
     * 然后添加为响应体的值
     *
     * @param inputStream 可以获取到响应体内容的输入流
     */
    public void addBodyFromStream(InputStream inputStream) {
        int oneSize = 8 * 1024;
        BufferedInputStream bufferedIn = null;
        try {
            bufferedIn = new BufferedInputStream(inputStream);
            byte[] data = new byte[oneSize];
            int len;
            do {
                len = bufferedIn.read(data);
                if (len == -1) break;
                body.add(data, 0, len);
            } while (len == oneSize);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedIn != null) {
                try {
                    bufferedIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从输入流中获取响应体内容
     * 然后按照范围添加为响应体的值
     *
     * @param inputStream 可以获取到响应体内容的输入流
     * @param offset      偏移量
     * @param length      长度
     */
    public void addBodyFromStream(InputStream inputStream, int offset, int length) {
        int oneSize = 8 * 1024;
        int count = 0;
        BufferedInputStream bufferedIn = null;
        try {
            bufferedIn = new BufferedInputStream(inputStream);
            if (bufferedIn.skip(offset) == offset) {
                byte[] data = new byte[oneSize];
                int len;
                do {
                    len = bufferedIn.read(data);
                    if (len == -1) break;
                    int margin = length - count;
                    if (margin < oneSize) {
                        len = Math.min(margin, len);
                        body.add(data, 0, len);
                        break;
                    }
                    count += len;
                    body.add(data, 0, len);
                } while (len == oneSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedIn != null) {
                try {
                    bufferedIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取响应状态行
     *
     * @return 响应状态行的值
     */
    public String getStatus_line() {
        return status_line;
    }

    /**
     * 获取响应头
     *
     * @return 响应头
     */
    public Header getHeader() {
        return header;
    }

    /**
     * 获取响应体
     *
     * @return 响应体
     */
    public Body getBody() {
        return body;
    }

    /**
     * 返回一个由所有内容组合而成的字符串
     *
     * @return 结果字符串
     */
    public String toRspString() throws NullStatus_LineException {
        if (status_line == null) {
            throw new NullStatus_LineException();
        }
        return status_line + header + "\r\n" + new String(body.get(), charset);
    }

    /**
     * 返回一个由所有内容组合而成的字节串
     *
     * @return 结果字节串
     */
    public byte[] toRspBytes() throws NullStatus_LineException {
        if (status_line == null) {
            throw new NullStatus_LineException();
        }

        Bytes bytes = new Bytes();
        bytes.append(status_line.getBytes(charset))
                .append(header.toString().getBytes(charset))
                .append("\r\n".getBytes(charset))
                .append(body.get());
        return bytes.toByteArray();
    }
}
