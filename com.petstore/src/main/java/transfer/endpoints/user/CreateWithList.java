package transfer.endpoints.user;

import transfer.BaseTransfer;

public class CreateWithList {

    private static final String ROUT = "/createWithArray";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;

    public CreateWithList(String parentRout) {
        this.path = parentRout + ROUT;
    }

}
