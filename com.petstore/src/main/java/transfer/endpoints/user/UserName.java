package transfer.endpoints.user;

import transfer.BaseTransfer;

public class UserName {

    private static final String ROUT = "/createWithArray";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;

    public UserName(String parentRout) {
        this.path = parentRout + ROUT;
    }

}
