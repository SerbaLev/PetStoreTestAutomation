package transfer.endpoints.store;

import lombok.Getter;
import transfer.BaseTransfer;

public class Store {

    private static final String ROUT = "/store";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;
    @Getter
    private final Order order;
    @Getter
    private final Inventory inventory;

    public Store(String parentRout) {
        this.path = parentRout + ROUT;
        this.order = new Order(path);
        this.inventory = new Inventory(path);
    }

}
