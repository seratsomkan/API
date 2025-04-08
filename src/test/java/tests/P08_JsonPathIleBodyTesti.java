package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class P08_JsonPathIleBodyTesti {

    @Test
    public void test(){
        /*
            https://restful-booker.herokuapp.com/booking url’ine asagidaki body’ye sahip bir POST request gonderdigimizde
            {
                “firstname” : “Ahmet”,
                “lastname” : “Bulut”,
                “totalprice” : 500,
                “depositpaid” : false,
                “bookingdates” : {
                    “checkin” : “2021-06-01”,
                    “checkout” : “2021-06-10”
                },
                “additionalneeds” : “wi-fi”
           }
            donen Response’un,
            status code’unun 200,
            ve content type’inin application/json,
            ve response body’sindeki
                “firstname”in,“Ahmet”,
                ve “lastname”in, “Bulut”,
                ve “totalprice”in,500,
                ve “depositpaid”in,false,
                ve “checkin” tarihinin 2021-06-01
                ve “checkout” tarihinin 2021-06-10
                ve “additionalneeds”in,“wi-fi”
            oldugunu test edin
        */

        // 1- EndPoint ve requestBody Hazırla
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject reqBody = new JSONObject();
        JSONObject innerBody = new JSONObject();

        innerBody.put("checkin","2021-06-01")
                 .put("checkout","2021-06-10");

        reqBody.put("firstname","Serat")
                .put("lastname","Somkan")
                .put("totalprice",500)
                .put("depositpaid",false)
                .put("bookingdates",innerBody)
                .put("additionalneeds","wi-fi");

        // 2- Expected Data -yok

        // 3- Response kaydı
        Response response = given().contentType(ContentType.JSON)
                .when().body(reqBody.toString()).post(url);

        // 4- Assertion
        response.then().assertThat().statusCode(200)
                .contentType("application/json")
                .body("booking.firstname", equalTo("Serat"),
                        "booking.lastname",equalTo("Somkan"),
                        "booking.totalprice",equalTo(500),
                        "booking.depositpaid",equalTo(false),
                        "booking.bookingdates.checkin",equalTo("2021-06-01"),
                        "booking.bookingdates.checkout",equalTo("2021-06-10"),
                        "booking.additionalneeds",equalTo("wi-fi")
                );

        /*
            Alınan çıktı, belli bir parantez içinde belirtilmişse eğer o parantez başlığının belirtilerek
            karşılaştırma yapılması gerekir. Yukarıdaki örnekte;
             - booking'in altındaki firstname veya booking'in altındaki additionalneeds
             - iç içe çıktı var ise booking'in altındaki bookingdates'in altındaki checkout
         */

    }
}
