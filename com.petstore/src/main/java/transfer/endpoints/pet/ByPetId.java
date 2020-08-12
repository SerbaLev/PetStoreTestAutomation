package transfer.endpoints.pet;

import lombok.Getter;
import transfer.BaseTransfer;

public class ByPetId {

    private static final String ROUT = "/{petId}";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;
    @Getter
    private final UploadImage uploadImage;

    public ByPetId(String parentRout) {
        this.path = parentRout + ROUT;
        this.uploadImage = new UploadImage(path);
    }

}
