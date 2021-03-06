package pp.pocket.response;

import java.util.List;

public class SuccessResponseObject extends BaseResponse{
    public Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public SuccessResponseObject(String status, Integer code, Object result) {
        super(status, code);
        this.result = result;
    }
}
