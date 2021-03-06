package pp.pocket.response;

import java.util.List;

public class SuccessResponseList extends BaseResponse{
    public List result;

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    public SuccessResponseList(String status, Integer code, List result) {
        super(status, code);
        this.result = result;
    }
}
