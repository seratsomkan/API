package tests;

import baseUrl.RESTFUL_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.RestFullBookingdatesPOJO;
import pojo.RestFullExpBodyPOJO;
import pojo.RestFullReqBodyPOJO;

import static io.restassured.RestAssured.given;

public class P22_POST_POJOClass extends RESTFUL_BaseUrl {

    @Test
    public void test01(){
        /*
            https://restful-booker.herokuapp.com/booking url'ine
            asagidaki body'ye sahip bir POST request gonderdigimizde
            donen response'un id haric asagidaki gibi oldugunu test edin.
            Request body
                   {
                       "firstname" : "Ahmet",
                       "lastname" : "Bulut",
                       "totalprice" : 500,
                       "depositpaid" : false,
                       "bookingdates" : {
                           "checkin" : "2021-06-01",
                           "checkout" : "2021-06-10"
                       },
                       "additionalneeds" : "wi-fi"
                   }
            Response Body // expected data
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

        specRestFull.pathParam("pp1","booking");

        RestFullBookingdatesPOJO bookingdates = new RestFullBookingdatesPOJO("2025-05-01","2025-05-05");

        RestFullReqBodyPOJO reqBody = new RestFullReqBodyPOJO("Serat","Somkan",1500,true,bookingdates,"wi-fi,breakfast");

        RestFullExpBodyPOJO expBody = new RestFullExpBodyPOJO(24,reqBody);

        Response response = given().spec(specRestFull).contentType(ContentType.JSON).when().body(reqBody).post("/{pp1}");

        RestFullExpBodyPOJO resPOJO = response.as(RestFullExpBodyPOJO.class);

        Assert.assertEquals(resPOJO.getBooking().getFirstname(),expBody.getBooking().getFirstname());
        Assert.assertEquals(resPOJO.getBooking().getLastname(),expBody.getBooking().getLastname());
        Assert.assertEquals(resPOJO.getBooking().getTotalprice(),expBody.getBooking().getTotalprice());
        Assert.assertEquals(resPOJO.getBooking().isDepositpaid(),expBody.getBooking().isDepositpaid());
        Assert.assertEquals(resPOJO.getBooking().getBookingdates().getCheckin(),expBody.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(resPOJO.getBooking().getBookingdates().getCheckout(),expBody.getBooking().getBookingdates().getCheckout());
        Assert.assertEquals(resPOJO.getBooking().getAdditionalneeds(),expBody.getBooking().getAdditionalneeds());

    }
}
