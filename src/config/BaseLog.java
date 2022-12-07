package config;

public class BaseLog {
    //private final Boolean isSuccess;
    private final String message;
    private final int code;

    public BaseLog(BaseLogStatus status) {
        //this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }
}
