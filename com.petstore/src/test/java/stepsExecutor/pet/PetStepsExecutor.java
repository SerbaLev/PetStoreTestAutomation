package stepsExecutor.pet;

import dto.request.pet.AddOrUpdatePetRequestDTO;
import dto.request.pet.UploadImageRequestDTO;
import dto.response.pet.ErrorOrNotificationDTO;
import dto.response.pet.PetResponseDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import transfer.Context;
import transfer.endpoints.PetStoreRestApi;
import transfer.endpoints.pet.FindByStatus;
import transfer.endpoints.pet.Pet;
import transfer.endpoints.pet.UploadImage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;


@Slf4j
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@EqualsAndHashCode
@ToString
public class PetStepsExecutor {

    private final Pet petEndpoint = new PetStoreRestApi().getPet();
    private final UploadImage uploadImageEndpoint = new PetStoreRestApi().getPet().getByPetId().getUploadImage();
    private final FindByStatus byPetStatusEndpoint = new PetStoreRestApi().getPet().getFindByStatus();

    private AddOrUpdatePetRequestDTO addOrUpdatePetRequest = new AddOrUpdatePetRequestDTO();
    private UploadImageRequestDTO uploadImageRequest = new UploadImageRequestDTO();


    private ErrorOrNotificationDTO expectedErrorOrNotificationResponse = new ErrorOrNotificationDTO();
    private ErrorOrNotificationDTO actualErrorOrNotificationResponse = new ErrorOrNotificationDTO();
    private PetResponseDTO actualPetResponse = new PetResponseDTO();
    private PetResponseDTO expectedPetResponse = new PetResponseDTO();
    private List<PetResponseDTO> actualPetListResponse = new ArrayList<>();
    private Integer actualStatusCode;

    private List<String> petStatuses;
    private String petId;

    @Step("Perform POST request with provided data in body section")
    public <T> void performAddNewPetRequest(T request) {
        Context<PetResponseDTO> context = petEndpoint.post(request);
        actualPetResponse = context.getObjectFromResponse();
        actualErrorOrNotificationResponse = context.getErrorOrNotification();
        actualStatusCode = context.getResponseStatusCode();
    }

    @Step("Perform PUT request with provided data in body section")
    public <T> void performUpdatePetRequest(T request) {
        Context<PetResponseDTO> context = petEndpoint.put(request);
        actualPetResponse = context.getObjectFromResponse();
        actualStatusCode = context.getResponseStatusCode();
    }

    @Step("Perform POST request with image information in form section and pet id in path")
    public void performImageUploadRequest(String petId, String formField1, String formValue1, String formField2, File formValue2) {
        Context<ErrorOrNotificationDTO> context = uploadImageEndpoint.post(petId, formField1, formValue1, formField2, formValue2);
        actualErrorOrNotificationResponse = context.getObjectFromResponse();
        actualStatusCode = context.getResponseStatusCode();
    }

    @Step("Perform GET request with statuses in path")
    public void performFindPetByStatusRequest() {
        String paramName = "status";
        Context<PetResponseDTO[]> context = byPetStatusEndpoint.get(paramName, petStatuses);
        actualPetListResponse = Arrays.asList(context.getObjectFromResponse());
        actualStatusCode = context.getResponseStatusCode();
    }

    @Step("Verify that status code is correct")
    public void verifyStatusCode(int expectedStatusCode) {
        assertThat("Status codes do not match. Expected: " + expectedStatusCode + " but was: " + actualStatusCode,
                actualStatusCode, is(equalTo(expectedStatusCode)));
    }

    @Step("Verify that response contains correct body")
    public <T> void verifyBodyOfTheResponse(T actualResponse, T expectedResponse) {
        assertThat("Actual response does not match the expected one", actualResponse, is(equalTo(expectedResponse)));
    }

    @Step("Get status for each pet and verify that status matches with provided in request")
    public void verifyResultContainsPetsOnlyForProvidedStatuses() {
        if (actualPetListResponse.isEmpty()) {
            log.info("There are no pets with statuses: " + String.join(",", petStatuses));
        } else {
            actualPetListResponse.forEach(pet -> Assert.assertTrue("Status does not math with provided in request",
                    petStatuses.contains(pet.status())));
        }
    }
}
