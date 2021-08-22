package DevilSpiderX.httpProtocolTool.exception;

public class RequestMethodUnsupportedException extends Exception {
    public RequestMethodUnsupportedException() {
        super("The method of request is unsupported");
    }
}
