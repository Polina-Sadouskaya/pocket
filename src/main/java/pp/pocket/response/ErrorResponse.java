package pp.pocket.response;

public class ErrorResponse extends BaseResponse {

    public String message;

    public ErrorResponse(String status, Integer code, String message) {
        super(status, code);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
