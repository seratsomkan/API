package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P10_POST_ExpectedDataJsonPathAssertion_getJSONObject {

    @Test
    public void test(){
        /*
            https://restful-booker.herokuapp.com/booking url'ine
            asagidaki body'ye sahip bir POST request gonderdigimizde
            donen response'un id haric asagidaki gibi oldugunu test edin.

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

            Response Body - Expected Data
            {
                "bookingid": 24,
                "booking": {
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
            }
 */

        // 1- Endpoint ve reqBody Hazırla
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject reqBody = new JSONObject();
        JSONObject innerData = new JSONObject();

        innerData.put("checkin","2021-06-01")
                  .put("checkout","2021-06-10");

        reqBody.put("firstname","Mahmut")
                .put("lastname","Tuncer")
                .put("totalprice",500)
                .put("depositpaid",false)
                .put("bookingdates",innerData)
                .put("additionalneeds","wi-fi");

        // 2- ExpectedData Olusturma
        JSONObject expBody = new JSONObject();
        expBody.put("bookingid",499)
                .put("booking",reqBody); // reqData'nın aynısı olduğu için, bu şekilde yazılır.

        // 3- Response kaydı
        Response response = given().contentType(ContentType.JSON).body(reqBody.toString()).post(url);

        // 4- Assert
        JsonPath respJP = response.jsonPath(); //Dönen cevap jSON formatına çevrilir.

        assertEquals(expBody.getJSONObject("booking").get("firstname"),respJP.get("booking.firstname"));
        assertEquals(expBody.getJSONObject("booking").get("lastname"),respJP.get("booking.lastname"));
        assertEquals(expBody.getJSONObject("booking").get("totalprice"),respJP.get("booking.totalprice"));
        assertEquals(expBody.getJSONObject("booking").get("depositpaid"),respJP.get("booking.depositpaid"));
        assertEquals(expBody.getJSONObject("booking").get("additionalneeds"),respJP.get("booking.additionalneeds"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),respJP.get("booking.bookingdates.checkin"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),respJP.get("booking.bookingdates.checkout"));

        /*
            Burada incelenmesi gereken 2 şey vardır;
            1- assertEquals(expBody.getJSONObject("booking").get("firstname"),respJP.get("booking.firstname"));
               bunun anlamı, expBody içindeki "booking" JSON nesnesine gider,
               "booking" içindeki "firstname" değerini alır. Aynı zamanda respJP
               içindeki "booking.firstname" yolunu takip ederek "firstname" değerini alır
               ve assertEquals ile karşılaştırma yapar.

            2- assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),respJP.get("booking.bookingdates.checkout"));
                bunun anlamı, expBody içindeki "booking" JSON nesnesine gider,
               "booking" içindeki "bookingdates" nesnesine gider ve "bookingdates"
               içindeki "checkout" değerini alır. Aynı zamanda respJP içindeki
               "booking.bookingdates.checkout" yolunu takip ederek "checkout" değerini alır
               ve assertEquals ile karşılaştırma yapar.

         */














    }
}
