package automated_tests.automated_tests_list.task;

import automated_tests.automated_tests_list.base.Base;
import automated_tests.configuration.AppConfig;
import automated_tests.utils.RetryAnalyzer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class SecondTask extends Base {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void validateJSONSchema() {
        RestAssured
                .given()
                .baseUri(AppConfig.getInstance().getRestAssuredUrl())
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(200)
                .body("token", Matchers.notNullValue())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
    }

}
