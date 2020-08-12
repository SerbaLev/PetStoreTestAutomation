package transfer.endpoints.user;

import lombok.Getter;
import transfer.BaseTransfer;

public class User {

    private static final String ROUT = "/user";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;
    @Getter
    private final CreateWithArray createWithArray;
    @Getter
    private final CreateWithList createWithList;
    @Getter
    private final LogIn logIn;
    @Getter
    private final LogOut logOut;
    @Getter
    private final UserName userName;

    public User(String parentRout) {
        this.path = parentRout + ROUT;
        this.createWithArray = new CreateWithArray(path);
        this.createWithList = new CreateWithList(path);
        this.logIn = new LogIn(path);
        this.logOut = new LogOut(path);
        this.userName = new UserName(path);
    }

}
