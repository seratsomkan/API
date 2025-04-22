package tests;

import baseUrl.JPH_baseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import pojo.PojoJPH;

import static io.restassured.RestAssured.given;

public class P21_PUT_PojoClass extends JPH_baseUrl {

    @Test
    public void pojoClassTest(){

        /*
            https://jsonplaceholder.typicode.com/posts/70 url'ine
            asagidaki body'e sahip bir PUT request yolladigimizda
            donen response'in response body'sinin
            asagida verilen ile ayni oldugunu test ediniz

            Request Body
            {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 10,
                "id": 70
            }
            Expected Data :
            {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 10,
                "id": 70
            }
        */
        // 1- Endpoint ve Request Body hazırlandı.
        specJPH.pathParams("pp1","posts","pp2","70");

        // Request body'i, PojoJPH'de oluşturduğumuz parametreli constructor'dan oluşturduk.
        PojoJPH reqPOJO = new PojoJPH("Ahmet","Merhaba",10,70);;

        // 2- Expected Body hazırlandı
        PojoJPH expPOJO = new PojoJPH("Ahmet","Merhaba",10,70);;

        // 3- Response hazırlandı.
        Response response = given().spec(specJPH).contentType(ContentType.JSON).when().body(reqPOJO).put("/{pp1}/{pp2}");

        PojoJPH resPOJO = response.as(PojoJPH.class);

        // 4- Assert
        Assertions.assertEquals(expPOJO.getTitle(),resPOJO.getTitle());
        Assertions.assertEquals(expPOJO.getBody(),resPOJO.getBody());
        Assertions.assertEquals(expPOJO.getUserId(),resPOJO.getUserId());
        Assertions.assertEquals(expPOJO.getId(),resPOJO.getId());

    }

}
