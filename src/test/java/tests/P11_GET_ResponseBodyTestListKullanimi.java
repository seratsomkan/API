package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P11_GET_ResponseBodyTestListKullanimi {

    @Test
    public void test(){
        /*
            https://api.collectapi.com/health/dutyPharmacy?il=Kırıkkale url'ine
            bir GET request yolladigimizda
            donen Response'in
            status code'unun 200,
            ve content type'inin application/json; charset=utf-8,
            ve response body'sindeki
                eczane sayısının sayisinin 6
                ve eczane'lerden birinin "ENES ECZANESİ"
                ve eczanele ilçelerinde icinde keskin ,karakeçili ve yahşihan degerinin oldugunu test edin.
         */

        // 1- Endpoint
        String url = "https://api.collectapi.com/health/dutyPharmacy?il=Kırıkkale";
            // Get olduğu için reqBody yok ve soruda verilmediği için expBody yok

        String apikey = "apikey 4wPOcueYv3UK3erYcHt3EG:45w4PXiWwFx0JbHV1axyy3";

        Response response = given().when().header("authorization",apikey).get(url);

        /*
            response.prettyPrint()'i getirmek istedik fakat güvenliğe takıldıkç
            Güvenlik sekmesi varsa, when'den sonra header diyerek, apikey'i
            "authorization"i referans alarak gireriz.
         */

        response.then().assertThat().statusCode(200)
                                    .contentType("application/json; charset=utf-8")
                                    .body("result.dist", hasSize(6),
                                            "result.name",hasItem("ENES ECZANESİ"),
                                            "result.dist",hasItems("keskin","karakeçili","yahşihan")
                                    );
        /*
            hasSize --> JSON içerisinden herhangi değerin(mesela dist) sayısına baktırıp, kaç adet eczane olduğunu çıkardık.
            hasItem/hasItems --> JSON içinde bir veya birden fazla değerin olup olmadığını kontrol eder.
         */
    }
}
