package transfer.endpoints.pet;

import dto.response.pet.PetResponseDTO;
import io.restassured.response.Response;
import lombok.Getter;
import transfer.BaseTransfer;
import transfer.Context;

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

    public Context<PetResponseDTO> post(String id, String formField1, String formValue1, String formField2, String formValue2) {
        Response response = baseTransfer.postWithFormStringParams(id, formField1, formValue1, formField2, formValue2, path);
        return new Context<>(response, PetResponseDTO.class);
    }

    public Context<PetResponseDTO> get(String id) {
        Response response = baseTransfer.get(id, path);
        return new Context<>(response, PetResponseDTO.class);
    }

    public Context<PetResponseDTO> delete(String id) {
        Response response = baseTransfer.delete(id, path);
        return new Context<>(response, PetResponseDTO.class);
    }
}
