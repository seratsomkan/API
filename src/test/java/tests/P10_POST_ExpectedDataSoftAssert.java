package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class P10_POST_ExpectedDataSoftAssert {

    @Test
    public void test(){
        /*
            https://restful-booker.herokuapp.com/booking url’ine
            asagidaki body’ye sahip bir POST request gonderdigimizde
            Request body
                  {
                    "firstname": "Ahmet",
                    "lastname": "Bulut",
                    "totalprice": 500,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2021-06-01",
                        "checkout": "2021-06-10"
                    },
                    "additionalneeds": "wi-fi"
                }
            donen response’un id haric asagidaki gibi oldugunu test edin.
            Response Body - Expected Data
             {
                “bookingid”: 24,
                “booking”: {
                    "firstname": "Ahmet",
                    "lastname": "Bulut",
                    "totalprice": 500,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2021-06-01",
                        "checkout": "2021-06-10"
                    },
                    additionalneeds": "wi-fi"
                }
            }
 */

        // 1- EndPoint ve request Body hazırla
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2021-06-01")
                    .put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject();
        reqBody.put("firstname","Maho")
                .put("lastname","Maho")
                .put("totalprice",500)
                .put("depositpaid",false)
                .put("bookingdates", bookingdates)
                .put("additionalneeds","wi-fi");

        // 2- ExpectedData
        JSONObject expBody = new JSONObject();
        expBody.put("bookingid",455)
                .put("booking",reqBody);

        // 3- Response kaydı
        Response response = given().contentType(ContentType.JSON).when().body(reqBody.toString()).post(url);
        JsonPath respJS = response.jsonPath();

        // 4- SoftAssert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(respJS.get("booking.firstname"),expBody.getJSONObject("booking").get("firstname"));
        softAssert.assertEquals(respJS.get("booking.lastname"),expBody.getJSONObject("booking").get("lastname"));
        softAssert.assertEquals(respJS.get("booking.totalprice"),expBody.getJSONObject("booking").get("totalprice"));
        softAssert.assertEquals(respJS.get("booking.depositpaid"),expBody.getJSONObject("booking").get("depositpaid"));
        softAssert.assertEquals(respJS.get("booking.bookingdates.checkin"),expBody.getJSONObject("booking")
                                                                                   .getJSONObject("bookingdates").get("checkin"));
        softAssert.assertEquals(respJS.get("booking.bookingdates.checkout"),expBody.getJSONObject("booking")
                                                                                   .getJSONObject("bookingdates").get("checkout"));
        softAssert.assertEquals(respJS.get("booking.additionalneeds"),expBody.getJSONObject("booking").get("additionalneeds"));

        softAssert.assertAll();

    }
}
