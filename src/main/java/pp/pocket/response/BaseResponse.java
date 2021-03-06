package pp.pocket.response;

import java.util.List;

public class BaseResponse {
    private String status;
    private Integer code;
    private String message;
    private List list;
    private Object object;

    public BaseResponse(String status, Integer code) {
        this.status = status;
        this.code = code;
    }

    public BaseResponse(String status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public BaseResponse(String status, Integer code, List list) {
        this.status = status;
        this.code = code;
        this.list = list;
    }

    public BaseResponse(String status, Integer code, Object object) {
        this.status = status;
        this.code = code;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
