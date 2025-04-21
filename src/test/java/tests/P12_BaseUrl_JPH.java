package tests;

import baseUrl.JPH_baseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class P12_BaseUrl_JPH extends JPH_baseUrl {

    /*
        https://jsonplaceholder.typicode.com/posts endpointine
        bir GET request gonderdigimizde
        donen response'un status code'unun 200 oldugunu ve
        Response'ta 100 kayit oldugunu test edin
    */

    @Test
    public void test(){

        // 1- Endpoint hazırlama
        specJPH.pathParam("pp1","posts");
        /*
            pp1 = pathParam1

            specJPH'yi, JPH_baseUrl'den aldık ve sonuna posts eklemesi yaptık.

            eğer birden fazla parametre girilecekse pathParams,
            tek parametre girilecekse pathParam seçilir.
         */

        // 2- Expected Body -yok

        // 3- Response
        Response response = given().spec(specJPH).when().get("/{pp1}");
        /*
            spec(specJPH)'yi kullan diyoruz ve
            {pp1} yerine posts yaz ve oluşan adrese GET isteği gönderiyoruz.
         */

        response.prettyPrint();
        /*
            Kayıtlardaki "userId","body","id" ve "title" sayısından 100 kayıt olup olmadığını sorgulatabiliriz.
            Kayıtları postmanden'de kontrol edebiliriz. Linki alıp get sorgusu yapabiliriz. Ordan kayıtların
            içerisinden bir parametre("userId","body","id" ve "title") alınıp, sayı kontrolü yapılabilir.
         */

        // 4- Assert
        response.then().assertThat().statusCode(200)
                                    .body("title", Matchers.hasSize(100));
    }


    @Test
    public void test02(){
        /*
            https://jsonplaceholder.typicode.com/posts/44 endpointine
            bir GET request gonderdigimizde donen response'un
            status code'unun 200 oldugunu ve "title" degerinin
            "optio dolor molestias sit" oldugunu test edin
        */

        specJPH.pathParams("pp1","posts","pp2","44");

        Response response = given().spec(specJPH).when().get("/{pp1}/{pp2}");

        response.then().assertThat().statusCode(200)
                                    .body("title",Matchers.equalTo("optio dolor molestias sit"));



    }











}
