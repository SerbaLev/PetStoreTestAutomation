package transfer.endpoints.user;

import transfer.BaseTransfer;

public class LogIn {

    private static final String ROUT = "/createWithArray";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;

    public LogIn(String parentRout) {
        this.path = parentRout + ROUT;
    }

}
