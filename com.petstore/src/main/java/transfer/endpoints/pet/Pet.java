package transfer.endpoints.pet;

import dto.response.pet.PetResponseDTO;
import io.restassured.response.Response;
import lombok.Getter;
import transfer.BaseTransfer;
import transfer.Context;

public class Pet {

    private static final String ROUT = "/pet";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;
    @Getter
    private final ByPetId byPetId;
    @Getter
    private final FindByStatus findByStatus;

    public Pet(String parentRout) {
        this.path = parentRout + ROUT;
        this.byPetId = new ByPetId(path);
        this.findByStatus = new FindByStatus(path);
    }

    public <T> Context<PetResponseDTO> post(T request) {
        Response response = baseTransfer.post(request, path);
        return new Context<>(response, PetResponseDTO.class);
    }

    public <T> Context<PetResponseDTO> put(T request) {
        Response response = baseTransfer.put(request, path);
        return new Context<>(response, PetResponseDTO.class);
    }

}
