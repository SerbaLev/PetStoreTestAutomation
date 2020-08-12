package transfer.endpoints.store;

import lombok.Getter;
import transfer.BaseTransfer;

public class Order {

    private static final String ROUT = "/order";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;
    @Getter
    private final ByOrderId byOrderId;

    public Order(String parentRout) {
        this.path = parentRout + ROUT;
        this.byOrderId = new ByOrderId(path);
    }

}
