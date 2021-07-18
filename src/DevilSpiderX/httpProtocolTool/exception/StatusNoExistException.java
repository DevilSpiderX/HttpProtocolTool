package DevilSpiderX.httpProtocolTool.exception;

/**
 * 响应状态不存在的异常
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */
public class StatusNoExistException extends Exception {
    public StatusNoExistException(int status) {
        super("Status(" + status + ") do not exist.");
    }
}
