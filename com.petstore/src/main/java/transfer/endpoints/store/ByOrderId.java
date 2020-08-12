package transfer.endpoints.store;

import transfer.BaseTransfer;

public class ByOrderId {

    private static final String ROUT = "/{petId}";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;

    public ByOrderId(String parentRout) {
        this.path = parentRout + ROUT;
    }

}
