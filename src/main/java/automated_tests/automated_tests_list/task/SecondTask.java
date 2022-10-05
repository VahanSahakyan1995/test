package automated_tests.automated_tests_list.task;

import automated_tests.automated_tests_list.base.Base;
import automated_tests.configuration.AppConfig;
import automated_tests.utils.RetryAnalyzer;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


public class SecondTask extends Base {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void validateJSONSchema() {
        RestAssured.baseURI = AppConfig.getInstance().getRestAssuredUrl();
        //obtain response
        given()
                .when().get()
                //verify JSON Schema
                .then().assertThat().statusCode(200)
                .body(JsonSchemaValidator.
                        matchesJsonSchema(new File("C:\\Users\\Admin\\Picsart Task\\test\\src\\main\\resources\\files\\schema.json")));
    }

}
