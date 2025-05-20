package recap;

import baseUrl.OpenWeatherBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P07_GET_openWeather extends OpenWeatherBaseUrl {

    public static String myAPIKey = "3050a71f1560f5c9895f191d4ac90c76";

    @Test
    public void test(){
        /*
            https://api.openweathermap.org/data/2.5/weather?q=Istanbul&appid=YOUR_API_KEY
            HTTP Metodu: GET
            Senaryo:
            Bu endpoint'e bir GET request gönderdiğimizde dönen Response’un:
             - Status code’unun 200,
             - Content-Type’ının application/json; charset=utf-8,
             - Response Body’sinde "name" alanının "Istanbul" olduğunu,
             - Response Body’sinde "main.temp" değerinin -50 ile 50 arasında olmadığını
            test ediniz.
         */



        specOpenWeather.pathParams("pp1","data","pp2","2.5","pp3","weather")
                        .queryParams("q","Istanbul","appid",myAPIKey);

        Response response = given().spec(specOpenWeather).when().get("/{pp1}/{pp2}/{pp3}");

        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("name", Matchers.equalTo("Istanbul"));

        Assertions.assertFalse(response.jsonPath().getDouble("main.temp")>-50 && response.jsonPath().getDouble("main.temp")<50);

    }
}
