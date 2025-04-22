package tests;

import baseUrl.CollectAPIBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class P24_CollectAPI_NobetciEczane extends CollectAPIBaseUrl {

    @Test
    public void test01(){
        /*
            https://api.collectapi.com/health/dutyPharmacy?il=istanbul&ilce=Üsküdar
            adresine aşağıdaki bilgilerler bir GET request yapınca
            dönen cevabı yazdırınız.

            header
            Content-Type : application/json
            Authorization: apiKeyiniz
         */

        specCollectApi.pathParams("pp1","health","pp2","dutyPharmacy")
                      .queryParams("il","istanbul","ilce","Üsküdar");

        Response response = given().spec(specCollectApi).when()
                                    .header("Content-Type","application/json")
                                    .header("Authorization","apikey 4wPOcueYv3UK3erYcHt3EG:45w4PXiWwFx0JbHV1axyy3")
                                    .get("/{pp1}/{pp2}");

        response.prettyPrint();

    }
}
