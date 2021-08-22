package DevilSpiderX.httpProtocolTool.exception;

/**
 * 没有响应状态行的异常
 *
 * @author <a target="_blank" href="https://github.com/DevilSpiderX">DevilSpiderX</a>
 */
public class NullStatusException extends Exception {
    public NullStatusException() {
        super("StatusLine is Null");
    }
}
