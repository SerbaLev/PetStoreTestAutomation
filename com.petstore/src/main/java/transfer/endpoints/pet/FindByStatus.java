package transfer.endpoints.pet;

import dto.response.pet.PetResponseDTO;
import io.restassured.response.Response;
import transfer.BaseTransfer;
import transfer.Context;

import java.util.List;

public class FindByStatus {

    private static final String ROUT = "/findByStatus";
    private final BaseTransfer baseTransfer = new BaseTransfer();
    private final String path;

    public FindByStatus(String parentRout) {
        this.path = parentRout + ROUT;
    }

    public Context<PetResponseDTO[]> get(String paramName, List<String> petStatuses) {
        Response response = baseTransfer.get(paramName, petStatuses, path);
        return new Context<>(response, PetResponseDTO[].class);
    }
}
