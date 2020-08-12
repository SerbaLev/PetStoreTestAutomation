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

    public Context<PetResponseDTO> get(String id) {
        Response response = baseTransfer.get(id, path);
        return new Context<>(response, PetResponseDTO.class);
    }
}
