package DevilSpiderX.httpProtocolTool.response;

import com.alibaba.fastjson.JSONObject;
import DevilSpiderX.httpProtocolTool.exception.StatusNoExistException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 响应状态行类
 * 保存有绝大多数的响应状态码对呀的状态行
 * 只用getStatus_Line静态方法就可以获取状态行
 * <h2>以下是已保存的状态行：</h2>
 * <h3><br>1**  信息，服务器收到请求，需要请求者继续执行操作</h3>
 * <p>
 * <strong>100</strong> : 继续。客户端应继续其请求
 * <br>
 * <strong>101</strong> : 切换协议。服务器根据客户端的请求切换协议。只能切换到更高级的协议，例如，切换到HTTP的新版本协议
 * </p>
 *
 * <h3><br>2**	成功，操作被成功接收并处理</h3>
 * <p>
 * <strong>200</strong> : 请求成功。一般用于GET与POST请求
 * <br>
 * <strong>201</strong> : 已创建。成功请求并创建了新的资源
 * <br>
 * <strong>202</strong> : 已接受。已经接受请求，但未处理完成
 * <br>
 * <strong>203</strong> : 非授权信息。请求成功。但返回的meta信息不在原始的服务器，而是一个副本
 * <br>
 * <strong>204</strong> : 无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档
 * <br>
 * <strong>205</strong> : 重置内容。服务器处理成功，用户终端（例如：浏览器）应重置文档视图。可通过此返回码清除浏览器的表单域
 * <br>
 * <strong>206</strong> : 部分内容。服务器成功处理了部分GET请求
 * </p>
 *
 * <h3><br>3**	重定向，需要进一步的操作以完成请求</h3>
 * <p>
 * <strong>300</strong> : 多种选择。请求的资源可包括多个位置，相应可返回一个资源特征与地址的列表用于用户终端（例如：浏览器）选择
 * <br>
 * <strong>301</strong> : 永久移动。请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI。今后任何新的请求都应使用新的URI代替
 * <br>
 * <strong>302</strong> : 临时移动。与<strong>301</strong>类似。但资源只是临时被移动。客户端应继续使用原有URI
 * <br>
 * <strong>303</strong> : 查看其它地址。与<strong>301</strong>类似。使用GET和POST请求查看
 * <br>
 * <strong>304</strong> : 未修改。所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。客户端通常会缓存访问过的资源，通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源
 * <br>
 * <strong>305</strong> : 使用代理。所请求的资源必须通过代理访问
 * <br>
 * <strong>306</strong> : 已经被废弃的HTTP状态码
 * <br>
 * <strong>307</strong> : 临时重定向。与<strong>302</strong>类似。使用GET请求重定向
 * </p>
 *
 * <h3><br>4**	客户端错误，请求包含语法错误或无法完成请求</h3>
 * <p>
 * <strong>400</strong> : 客户端请求的语法错误，服务器无法理解
 * <br>
 * <strong>401</strong> : 请求要求用户的身份认证
 * <br>
 * <strong>402</strong> : 保留，将来使用
 * <br>
 * <strong>403</strong> : 服务器理解请求客户端的请求，但是拒绝执行此请求
 * <br>
 * <strong>404</strong> : 服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置"您所请求的资源无法找到"的个性页面
 * <br>
 * <strong>405</strong> : 客户端请求中的方法被禁止
 * <br>
 * <strong>406</strong> : 服务器无法根据客户端请求的内容特性完成请求
 * <br>
 * <strong>407</strong> : 请求要求代理的身份认证，与<strong>401</strong>类似，但请求者应当使用代理进行授权
 * <br>
 * <strong>408</strong> : 服务器等待客户端发送的请求时间过长，超时
 * <br>
 * <strong>409</strong> : 服务器完成客户端的PUT请求是可能返回此代码，服务器处理请求时发生了冲突
 * <br>
 * <strong>410</strong> : 客户端请求的资源已经不存在。<strong>410</strong>不同于<strong>404</strong>，如果资源以前有现在被永久删除了可使用<strong>410</strong>代码，网站设计人员可通过<strong>301</strong>代码指定资源的新位置
 * <br>
 * <strong>411</strong> : 服务器无法处理客户端发送的不带Content-Length的请求信息
 * <br>
 * <strong>412</strong> : 客户端请求信息的先决条件错误
 * <br>
 * <strong>413</strong> : 由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息
 * <br>
 * <strong>414</strong> : 请求的URI过长（URI通常为网址），服务器无法处理
 * <br>
 * <strong>415</strong> : 服务器无法处理请求附带的媒体格式
 * <br>
 * <strong>416</strong> : 客户端请求的范围无效
 * <br>
 * <strong>417</strong> : 服务器无法满足Expect的请求头信息
 * </p>
 *
 * <h3><br>5**	服务器错误，服务器在处理请求的过程中发生了错误</h3>
 * <p>
 * <strong>500</strong> : 服务器内部错误，无法完成请求
 * <br>
 * <strong>501</strong> : 服务器不支持请求的功能，无法完成请求
 * <br>
 * <strong>502</strong> : 充当网关或代理的服务器，从远端服务器接收到了一个无效的请求
 * <br>
 * <strong>503</strong> : 由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中
 * <br>
 * <strong>504</strong> : 充当网关或代理的服务器，未及时从远端服务器获取请求
 * <br>
 * <strong>505</strong> : 服务器不支持请求的HTTP协议的版本，无法完成处理
 * </p>
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */


public class StatusLine {
    private static JSONObject statusMap;

    public static void init() {
        InputStream in = StatusLine.class.getResourceAsStream("StatusLines.json");
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        if (in != null) {
            try {
                reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            statusMap = JSONObject.parseObject(sb.toString());
        }
    }

    /**
     * 获取响应状态行
     *
     * @param status 状态码
     * @return 响应状态行
     * @throws StatusNoExistException 响应状态不存在异常
     */
    public static String getStatus_Line(int status) throws StatusNoExistException {
        String status_Line = statusMap.getString(String.valueOf(status));

        if (status_Line == null) {
            throw new StatusNoExistException(status);
        } else {
            return status_Line + "\r\n";
        }
    }
}
