package transfer;

import configuration.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

@Slf4j
public class BaseTransfer {

    private Configuration configuration = new Configuration();

    public Response post(Object body, String path) {
        return buildCreateOrUpdateRequest(body).post(path);
    }

    public Response put(Object body, String path) {
        return buildCreateOrUpdateRequest(body).put(path);
    }

    public Response postWithFormParams(String id, String formField1, String formValue1,
                                       String formField2, File formValue2, String path) {
        return given().spec(getRequestSpecificationMultipart()).log().all()
                .multiPart(formField1, formValue1)
                .multiPart(formField2, formValue2)
                .post(path, id);
    }

    public Response get(String paramName, List<String> params, String path) {
        return given().urlEncodingEnabled(true).spec(getRequestSpecification()).log().all().queryParam(paramName, params).when().get(path);
    }

    private RequestSpecification buildCreateOrUpdateRequest(Object body) {
        return given().spec(getRequestSpecification()).log().all().body(body).when();
    }

    private RequestSpecification getRequestSpecificationMultipart() {
        return new RequestSpecBuilder()
                .setContentType("multipart/form-data")
                .setBaseUri(configuration.getValue(Configuration.Options.BASE_PATH)).build();
    }

    private RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(configuration.getValue(Configuration.Options.BASE_PATH)).build();
    }

}
