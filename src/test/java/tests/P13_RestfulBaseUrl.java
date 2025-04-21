package tests;

import baseUrl.RESTFUL_BaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class P13_RestfulBaseUrl extends RESTFUL_BaseUrl {

    @Test
    public void test01(){
        /*
            https://restful-booker.herokuapp.com/booking endpointine
            bir GET request gonderdigimizde donen response'un
            status code'unun 200 oldugunu ve
            Response'ta 12 bookingid'sine sahip booking oldugunu test edin
        */

        specRestFull.pathParam("pp1","booking");

        Response response = given().spec(specRestFull).when().get("/{pp1}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200)
                .body("bookingid", Matchers.hasItem(12));

    }
}
