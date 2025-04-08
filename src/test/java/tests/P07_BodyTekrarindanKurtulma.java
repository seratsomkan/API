package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class P07_BodyTekrarindanKurtulma {

    @Test
    public void test(){
        /*
            https://restful-booker.herokuapp.com/booking/10 url'ine
            bir GET request gonderdigimizde donen Response'un,
            status code'unun 200,
            ve content type'inin application-json,
            ve response body'sindeki
               "firstname"in, "Mark",
                ve "lastname"in, "Jackson",
                ve "totalprice"in, 458,
                ve "depositpaid"in, false,
                ve "additionalneeds"in, "Breakfast"
            oldugunu test edin
 */

        // 1- EndPoint Hazırlama
        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2- Expected Data -yok

        // 3- Response kaydı
        Response response = given().when().get(url);

        // 4- Assertion
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("firstname", equalTo("Mark"),
                        "lastname",equalTo("Jackson"),
                        "totalprice",equalTo(458),
                        "depositpaid",equalTo(false),
                        "additionalneeds",equalTo("Breakfast")
                );
        /*
            Tek body içerisine yazılabilir. Key-value şeklinde yazarak ve aralarına
            virgül koyarak, birden fazla body yazımını engellemiş oluruz.
         */
    }
}
