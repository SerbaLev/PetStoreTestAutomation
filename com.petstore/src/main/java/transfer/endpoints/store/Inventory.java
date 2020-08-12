package transfer.endpoints.store;

import transfer.BaseTransfer;

public class Inventory {

    private static final String ROUT = "/store";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;

    public Inventory(String parentRout) {
        this.path = parentRout + ROUT;
    }

}
