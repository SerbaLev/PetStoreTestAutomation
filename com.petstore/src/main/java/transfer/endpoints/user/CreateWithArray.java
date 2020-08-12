package transfer.endpoints.user;

import transfer.BaseTransfer;

public class CreateWithArray {

    private static final String ROUT = "/createWithArray";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;

    public CreateWithArray(String parentRout) {
        this.path = parentRout + ROUT;
    }

}
