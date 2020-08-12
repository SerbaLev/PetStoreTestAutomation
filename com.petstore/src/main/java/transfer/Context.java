package transfer;

import dto.response.pet.ErrorOrNotificationDTO;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Context<T> {

    private Response response;
    private Class<T> cls;

    public int getResponseStatusCode() {
        return response.getStatusCode();
    }

    public String getResponseAsString() {
        return response.getBody().asString();
    }

    public ErrorOrNotificationDTO getErrorOrNotification() {
        return getExtractor().as(ErrorOrNotificationDTO.class);
    }

    public T getObjectFromResponse() {
        return getExtractor().as(cls);
    }

    private ExtractableResponse<Response> getExtractor() {
        return response.then().log().all().extract();
    }

}
