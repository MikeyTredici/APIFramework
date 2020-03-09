package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
  StepDefinitions step = new StepDefinitions();

  @Before("@DeletePlace")
  public void beforeScenario() throws IOException {
    if (StepDefinitions.placeId == null) {
      step.add_Place_Payload_with("Tony", "EN", "123 address lane");
      step.user_calls_with_http_request("addPlaceAPI", "post");
      step.verifyPlace_idCreatedMapsToUsing("Tony", "getPlaceAPI");
    }
  }
}
