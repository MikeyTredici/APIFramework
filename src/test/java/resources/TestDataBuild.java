package resources;

import addPlace.AddPlace;
import addPlace.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
  public AddPlace addPlacePayload(String name, String language, String address) {
    AddPlace addPlace = new AddPlace();

    addPlace.setAccuracy(50);
    addPlace.setAddress(address);
    addPlace.setLanguage(language);
    addPlace.setPhone_number("(+91) 983 893 3937");
    addPlace.setWebsite("http://google.com");
    addPlace.setName(name);

    List<String> types = new ArrayList<>();

    types.add("antique");
    types.add("shop");

    addPlace.setTypes(types);

    Location location = new Location();
    location.setLat(-38.383494);
    location.setLng(33.427362);

    addPlace.setLocation(location);

    return addPlace;
  }

  public String deletePlacePayload(String placeId) {
    return "{\n" + "    \"place_id\" : \"" + placeId + "\"\n" + "}";
  }
}
