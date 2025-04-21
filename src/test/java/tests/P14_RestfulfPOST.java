package tests;

import baseUrl.RESTFUL_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class P14_RestfulfPOST extends RESTFUL_BaseUrl {

    @Test
    public void test(){
    /*
        https://restful-booker.herokuapp.com/booking endpointine
        asagidaki body'ye sahip bir POST request gonderdigimizde donen response'un
        status code'unun 200 oldugunu ve "firstname" degerinin "Murat" oldugunu test edin
        {
            "firstname" : "Murat",
            "lastname" : "Yiğit",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2021-06-01",
                "checkout" : "2021-06-10"
                },
            "additionalneeds" : "wi-fi"
        }
     */

     specRestFull.pathParam("pp1","booking");

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2021-06-01")
                    .put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject();
        reqBody.put("firstname","Maho")
                .put("lastname","Maho")
                .put("totalprice",500)
                .put("depositpaid",false)
                .put("bookingdates",bookingdates)
                .put("additionalneeds","wi-fi");

        Response response = given().contentType(ContentType.JSON)
                            .spec(specRestFull).when().body(reqBody.toString()).post("/{pp1}");

        /*
            silme: DELETE
            update: PUT-PATCH
            kayıt: POST
            görüntüleme: GET
         */


        response.then().assertThat().statusCode(200)
                .body("booking.firstname", Matchers.equalTo("Maho"),
                              "booking.lastname", Matchers.equalTo("Maho"),
                              "booking.totalprice", Matchers.equalTo(500),
                              "booking.depositpaid", Matchers.equalTo(false),
                              "booking.bookingdates.checkin", Matchers.equalTo("2021-06-01"),
                              "booking.bookingdates.checkout", Matchers.equalTo("2021-06-10"),
                              "booking.additionalneeds", Matchers.equalTo("wi-fi")
                );

    }

    public void test02(){
        /*
            https://restful-booker.herokuapp.com/booking endpointine
            asagidaki body'ye sahip bir POST request gonderdigimizde
            {
                "firstname" : "Murat",
                "lastname" : "Yiğit",
                "totalprice" : 500,
                "depositpaid" : false,
                "bookingdates" : {
                    "checkin": "2021-06-01",
                    "checkout" : "2021-06-10"
                    },
                "additionalneeds" : "wi-fi"
            }
            donen response'unstatus code'unun 200 oldugunu ve sonucun bookingid hariç
            aşağıdaki gibi olduğunu test edin
            {
                "bookingid": 1063,
                "booking": {
                    "firstname": "Murat",
                    "lastname": "Yiğit",
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

    }
}
