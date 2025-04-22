package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class CollectAPIBaseUrl {

    protected RequestSpecification specCollectApi;

    @BeforeTest
    public void setup(){

        specCollectApi = new RequestSpecBuilder().setBaseUri("https://api.collectapi.com").build();

    }
}
