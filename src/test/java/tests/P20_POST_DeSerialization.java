package tests;

import baseUrl.RESTFUL_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import testDatas.RestfullDatas;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P20_POST_DeSerialization extends RESTFUL_BaseUrl {

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

        Map<String,Object> reqMapBody = RestfullDatas.mapDataOlustur();
        Map<String,Object> expMapBody = RestfullDatas.expMapDataOlustur();

        Response response = given().contentType(ContentType.JSON).spec(specRestFull).when().body(reqMapBody).post("/{pp1}");

        Map<String,Object> resMap = response.as(HashMap.class);

        Assert.assertEquals( ( (Map) resMap.get("booking") ).get("firstname"),( (Map) expMapBody.get("booking") ).get("firstname"));
        Assert.assertEquals( ( (Map) resMap.get("booking") ).get("lastname"),( (Map) expMapBody.get("booking") ).get("lastname"));
        Assert.assertEquals( ( (Map) resMap.get("booking") ).get("totalprice"),( (Map) expMapBody.get("booking") ).get("totalprice"));
        Assert.assertEquals( ( (Map) resMap.get("booking") ).get("depositpaid"),( (Map) expMapBody.get("booking") ).get("depositpaid"));
        /*
            JSONObject olmadığı için "booking.firstname" ile değere ulaşamadık.
            Map nesnesi oluşturduğumuz için, "booking" bize OBJECT türünde dönecekti.
            Bu yüzden booking içindeki değerlere ulaşmak için;
            ÖNCE "booking"i getirdik ve MAP'e cast ettik.
            Cast ettikten sonra tekrar '.get' kullanmamıza olanak sağlandı ve booking içerisindeki değerlere ulaştık.
         */
        /*
            İnnerData'ya ulaşmak için 2 kez cast yaptık.
         */
        Assert.assertEquals(((Map)((Map) resMap.get("booking")).get("bookingdates")).get("checkin"),
                            ((Map)((Map) expMapBody.get("booking")).get("bookingdates")).get("checkin"));

        Assert.assertEquals(((Map)((Map) resMap.get("booking")).get("bookingdates")).get("checkout"),
                            ((Map)((Map) expMapBody.get("booking")).get("bookingdates")).get("checkout"));

        Assert.assertEquals( ( (Map) resMap.get("booking") ).get("additionalneeds"),( (Map) expMapBody.get("booking") ).get("additionalneeds"));
    }
}
