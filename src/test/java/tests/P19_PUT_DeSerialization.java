package tests;

import baseUrl.JPH_baseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import testDatas.JPHDatas;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P19_PUT_DeSerialization extends JPH_baseUrl {

    @Test
    public void test01(){
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

        specJPH.pathParams("pp1","posts","pp2","70");

        Map<String, Object> reqMapBody = JPHDatas.MapDataOlustur();

        Map<String,Object> expMapBody = JPHDatas.MapDataOlustur();

        Response response = given().contentType(ContentType.JSON).spec(specJPH).when().body(reqMapBody).put("/{pp1}/{pp2}");

        //response.prettyPrint();

        Map<String,Object> resMap = response.as(HashMap.class);

        Assert.assertEquals(resMap.get("title"),expMapBody.get("title"));
        Assert.assertEquals(resMap.get("body"),expMapBody.get("body"));
        Assert.assertEquals(resMap.get("userId"),expMapBody.get("userId"));
        Assert.assertEquals(resMap.get("id"),expMapBody.get("id"));

    }

    @Test
    public void test02(){
        specJPH.pathParams("pp1","posts","pp2","70");

        Map<String,Object> reqMapBody = JPHDatas.parametreliMapDataOlustur(10.0,"Merhaba","Ahmet",70.0);

        Map<String,Object> expMapBody = JPHDatas.parametreliMapDataOlustur(10.0,"Merhaba","Ahmet",70.0);
        // JPHDatas class'ında oluşturulan metot çağırılarak, expMapBody nesnesi oluşturuldu.

        Response response = given().contentType(ContentType.JSON).spec(specJPH).when().body(reqMapBody).put("/{pp1}/{pp2}");
        // İstek gönderildi.

        Map<String,Object> resMap = response.as(HashMap.class);
        // Gelen JSON cevabı HashMap nesnesine dönüştürüldü.

        Assert.assertEquals(resMap.get("title"),expMapBody.get("title"));
        Assert.assertEquals(resMap.get("body"),expMapBody.get("body"));
        Assert.assertEquals(resMap.get("userId"),expMapBody.get("userId"));
        Assert.assertEquals(resMap.get("id"),expMapBody.get("id"));
    }

}
