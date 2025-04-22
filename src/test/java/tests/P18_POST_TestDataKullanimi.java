package tests;

import baseUrl.RESTFUL_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import testDatas.RestfullDatas;

import static io.restassured.RestAssured.given;

public class P18_POST_TestDataKullanimi extends RESTFUL_BaseUrl {

    @Test
    public void test01(){
        /*
            https://restful-booker.herokuapp.com/booking url'ine asagidaki body'ye sahip
            bir POST request gonderdigimizde donen response'un
            id haric asagidaki gibi oldugunu test edin.

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

            Response Body
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

        JSONObject reqBody = RestfullDatas.jsonDataOlustur(); //Request Body'i, RestfullDatas'taki metottan oluşturduk

        JSONObject expBody = new JSONObject(); // Expected Body adında Json objesi oluşturduk.
        expBody.put("bookingid",11); // Expected body'e, booking id ekledik.
        expBody.put("booking",RestfullDatas.jsonDataOlustur());
        // Expected body'e, booking ekledik fakat değerler aynı olacağı için, RestfullDatas'taki metottan aldık.

        Response response = given().spec(specRestFull).contentType(ContentType.JSON)
                                    .when().body(reqBody.toString()).post("/{pp1}");
        /*
            Response'umuzu oluşturduk;
            given()                  --> istek yapılandırmasını başlatır.
            spec(specRestfull)       --> BaseUrl'de oluşturduğumuz adresi ekledik
            contentType()            --> Gönderilecek verinin formatını belirtir.
            when()                   --> istek aşamasına geçildiğini belirtir.
            body(reqBody.toString()) --> oluşturulan reqBody'i String'e çevirerek body'e ekledik
            post("/{pp1}")           --> post isteği yapılır. pp1 yerine oluşturulan parametre geçecek.
         */

        JsonPath resJP = response.jsonPath();

        Assertions.assertEquals(expBody.getJSONObject("booking").getString("firstname"),resJP.getString("booking.firstname"));
        Assertions.assertEquals(expBody.getJSONObject("booking").getString("lastname"),resJP.getString("booking.lastname"));
        Assertions.assertEquals(expBody.getJSONObject("booking").getInt("totalprice"),resJP.getInt("booking.totalprice"));
        Assertions.assertEquals(expBody.getJSONObject("booking").get("depositpaid"),resJP.get("booking.depositpaid"));

        Assertions.assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin"),resJP.getString("booking.bookingdates.checkin"));
        Assertions.assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout"),resJP.getString("booking.bookingdates.checkout"));

        Assertions.assertEquals(expBody.getJSONObject("booking").getString("additionalneeds"),resJP.getString("booking.additionalneeds"));
    }

    @Test
    public void test02(){

        specRestFull.pathParam("pp1","booking");

        JSONObject bookingdates = RestfullDatas.parametreliBookingdatesOlustur("2025-06-06","2025-06-10");
        JSONObject reqBody = RestfullDatas.parametreliReqBodyOlustur("Serat","Somkan",1515,false,bookingdates,"wifi");

        JSONObject expData = new JSONObject();
        expData.put("bookingid",14);
        expData.put("booking",reqBody);

        Response response = given().contentType(ContentType.JSON).spec(specRestFull).when().body(reqBody.toString()).post("/{pp1}");

        response.then().assertThat().statusCode(200);

        JsonPath resJP = response.jsonPath();

        Assert.assertEquals(resJP.getString("booking.firstname"),expData.getJSONObject("booking").getString("firstname"));
        Assert.assertEquals(resJP.getString("booking.lastname"),expData.getJSONObject("booking").getString("lastname"));
        Assert.assertEquals(resJP.getInt("booking.totalprice"),expData.getJSONObject("booking").getInt("totalprice"));
        Assert.assertEquals(resJP.getBoolean("booking.depositpaid"),expData.getJSONObject("booking").getBoolean("depositpaid"));

        Assert.assertEquals(resJP.getString("booking.bookingdates.checkin"),expData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin"));
        Assert.assertEquals(resJP.getString("booking.bookingdates.checkout"),expData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout"));

        Assert.assertEquals(resJP.getString("booking.additionalneeds"),expData.getJSONObject("booking").getString("additionalneeds"));

    }
}
