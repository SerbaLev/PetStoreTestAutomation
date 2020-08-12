package transfer.endpoints.user;

import transfer.BaseTransfer;

public class LogOut {

    private static final String ROUT = "/createWithArray";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;

    public LogOut(String parentRout) {
        this.path = parentRout + ROUT;
    }

}
