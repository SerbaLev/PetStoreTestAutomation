package steps.pet;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dto.data.CategoryDTO;
import dto.data.TagDTO;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Steps;
import stepsExecutor.pet.PetStepsExecutor;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetStepDefinitions {

    @Steps
    PetStepsExecutor stepsExecutor;

    @Given("User provides id, name and status information$")
    public void userProvidesIdNameAndStatusInformation(DataTable table) {
        Map<String, String> data = table.asMaps().get(0);
        stepsExecutor.addOrUpdatePetRequest().id(data.get("id"))
                .name(data.get("name"))
                .status(data.get("status"));
    }

    @Given("User provides category information$")
    public void userProvidesCategoryInformation(DataTable table) {
        Map<String, String> data = table.asMaps().get(0);
        CategoryDTO category = new CategoryDTO().id(data.get("id")).name(data.get("name"));
        stepsExecutor.addOrUpdatePetRequest().category(category);
    }

    @Given("User provides photo URLs$")
    public void userProvidesPhotoURLs(List<String> photoUrls) {
        stepsExecutor.addOrUpdatePetRequest().photoUrls(photoUrls);
    }

    @Given("User provides tags information$")
    public void userProvidesTagsInformation(DataTable table) {
        List<TagDTO> tableDataTags = table.asMaps().stream()
                .map(data -> new TagDTO().name(data.get("name")).id(data.get("id")))
                .collect(Collectors.toList());
        stepsExecutor.addOrUpdatePetRequest().tags(tableDataTags);
    }

    @Given("User provides statuses$")
    public void userProvidesStatuses(DataTable table) {
        Map<String, String> data = table.asMaps().get(0);
        stepsExecutor.petStatuses(Arrays.asList(data.get("status").split(",")));
    }

    @When("User send \"add new pet\" request")
    public void userSendAddNewPetRequest() {
        stepsExecutor.performAddNewPetRequest(stepsExecutor.addOrUpdatePetRequest());
    }

    @When("User send \"update pet information\" request")
    public void userSendUpdatePetRequest() {
        stepsExecutor.performUpdatePetRequest(stepsExecutor.addOrUpdatePetRequest());
    }

    @When("User upload image of the pet$")
    public void userUploadImageOfThePet(DataTable table) {
        Map<String, String> data = table.asMaps().get(0);
        stepsExecutor.petId(data.get("id"));
        stepsExecutor.uploadImageRequest()
                .additionalMetadata(data.get("additionalMetadata"))
                .file(new File(data.get("file")));
    }

    @When("User send \"upload image\" request")
    public void userSendUploadImageRequest() {
        stepsExecutor.performImageUploadRequest(stepsExecutor.petId(),
                "additionalMetadata",
                stepsExecutor.uploadImageRequest().additionalMetadata(),
                "file", stepsExecutor.uploadImageRequest().file());
    }

    @When("User send \"find pet by status\" request")
    public void userSendRequest() {
        stepsExecutor.performFindPetByStatusRequest();
    }

    @Then("^Request executed with correct response")
    public void petAddedOrUpdatedSuccessfully() {
        stepsExecutor.expectedPetResponse()
                .category(stepsExecutor.addOrUpdatePetRequest().category())
                .id(stepsExecutor.addOrUpdatePetRequest().id())
                .name(stepsExecutor.addOrUpdatePetRequest().name())
                .tags(stepsExecutor.addOrUpdatePetRequest().tags())
                .photoUrls(stepsExecutor.addOrUpdatePetRequest().photoUrls())
                .status(stepsExecutor.addOrUpdatePetRequest().status());
        stepsExecutor.verifyBodyOfTheResponse(stepsExecutor.actualPetResponse(), stepsExecutor.expectedPetResponse());
    }

    @Then("^Upload pet photo request executed with correct response$")
    public void petPhotoUploadedSuccessfully(DataTable table) {
        Map<String, String> data = table.asMaps().get(0);
        stepsExecutor.expectedErrorOrNotificationResponse()
                .code(data.get("code"))
                .message(data.get("message"))
                .type(data.get("type"));
        stepsExecutor.verifyBodyOfTheResponse(stepsExecutor.actualErrorOrNotificationResponse(), stepsExecutor.expectedErrorOrNotificationResponse());
    }

    @Then("Request executed with status code {int} in the response")
    public void requestFailedWithCorrectErrorAndStatusCode(int statusCode) {
        stepsExecutor.verifyStatusCode(statusCode);
    }

    @Then("Results contains pets only for provided statuses")
    public void resultContainsPetsOnlyForProvidedStatuses() {
        stepsExecutor.verifyResultContainsPetsOnlyForProvidedStatuses();
    }

}
