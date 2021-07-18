package DevilSpiderX.httpProtocolTool.response;

import DevilSpiderX.httpProtocolTool.handle.GETHandler;
import DevilSpiderX.httpProtocolTool.handle.POSTHandler;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 标识可响应
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */
public interface Responsive {
    /**
     * 处理GET请求的响应
     *
     * @param out     数据输出流
     * @param handler GET请求处理类
     * @throws IOException IO异常
     */
    void responseOfGet(DataOutputStream out, GETHandler handler) throws IOException;

    /**
     * 处理POST请求的响应
     *
     * @param out     数据输出流
     * @param handler POST请求处理类
     * @throws IOException IO异常
     */
    void responseOfPOST(DataOutputStream out, POSTHandler handler) throws IOException;
}
