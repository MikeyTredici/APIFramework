package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Utils {
  RequestSpecification res;
  ResponseSpecification responseSpec;
  Response response;
  TestDataBuild data = new TestDataBuild();
  public static String placeId;

  @Given("Add Place Payload with {string} {string} {string}")
  public void add_Place_Payload_with(String name, String language, String address)
      throws IOException {
    res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
  }

  @When("User calls {string} with {string} http request")
  public void user_calls_with_http_request(String resource, String method) {
    APIResources apiResources = APIResources.valueOf(resource);
    System.out.println(APIResources.valueOf(resource));

    responseSpec =
        new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    if (method.equalsIgnoreCase("post")) {
      response = res.when().post(apiResources.getResource());
    } else if (method.equalsIgnoreCase("get")) {
      response = res.when().get(apiResources.getResource());
    } else if (method.equalsIgnoreCase("delete")) {
      response = res.when().delete(apiResources.getResource());
    }
  }

  @Then("The API call succeeds with a status code {int}")
  public void the_API_call_succeeds_with_a_status_code(Integer int1) {
    assertEquals(response.getStatusCode(), 200);
  }

  @Then("{string} in response body is {string}")
  public void in_response_body_is(String keyValue, String expectedValue) {
    assertEquals(getJsonPath(response, keyValue), expectedValue);
  }

  @Then("verify place_id created maps to {string} using {string}")
  public void verifyPlace_idCreatedMapsToUsing(String expectedName, String resource)
      throws IOException {
    placeId = getJsonPath(response, "place_id");
    res = given().spec(requestSpecification()).queryParam("place_id", placeId);
    user_calls_with_http_request(resource, "get");

    String actualName = getJsonPath(response, "name");
    assertEquals(expectedName, actualName);
  }

  @Given("Delete Place Payload")
  public void deletePlacePayload() throws IOException {
    res = given().spec(requestSpecification().body(data.deletePlacePayload(placeId)));
  }
}
