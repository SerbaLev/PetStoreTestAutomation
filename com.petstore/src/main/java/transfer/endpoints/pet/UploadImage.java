package transfer.endpoints.pet;

import dto.response.pet.ErrorOrNotificationDTO;
import io.restassured.response.Response;
import transfer.BaseTransfer;
import transfer.Context;

import java.io.File;

public class UploadImage {

    private static final String ROUT = "/uploadImage";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;

    public UploadImage(String parentRout) {
        this.path = parentRout + ROUT;
    }

    public Context<ErrorOrNotificationDTO> post(String id, String formField1, String formValue1, String formField2, File formValue2) {
        Response response = baseTransfer.postWithFormParams(id, formField1, formValue1, formField2, formValue2, path);
        return new Context<>(response, ErrorOrNotificationDTO.class);
    }

}
