package tests;

import baseUrl.JPH_baseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import testDatas.JPHDatas;

import static io.restassured.RestAssured.given;

public class P17_PUT_TestDataKullanimi extends JPH_baseUrl {

    /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body'e sahip
        bir PUT request yolladigimizda donen response'in
        status kodunun 200, content type'inin "application/json; charset=utf-8",
        Connection header degerinin "keep-alive"
        ve response body'sinin asagida verilen ile ayni oldugunu test ediniz
        Request Body
            {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 7,
                "id": 70
            }
        Response Body
            {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 7,
                "id": 70
            }
    */

    @Test
    public void test01(){

        specJPH.pathParams("pp1","posts","pp2",70);

        JSONObject reqBody = JPHDatas.jSONDataOlustur();

        JSONObject expBody = JPHDatas.parametreliJSONDataOlustur(3,70,"Ahmet","Merhaba");

        Response response = given().contentType(ContentType.JSON).spec(specJPH).when().body(reqBody.toString()).put("/{pp1}/{pp2}");

        Assertions.assertEquals(response.getStatusCode(),JPHDatas.basariliStatusCode);
        Assertions.assertEquals(response.getContentType(),JPHDatas.contentType);
        Assertions.assertEquals(response.getHeader("Connection"),JPHDatas.connectionHeader);

        JsonPath resJP = response.jsonPath();
        response.prettyPrint();

        Assert.assertEquals(resJP.getInt("userId"),expBody.get("userId"));
        Assert.assertEquals(resJP.getInt("id"),expBody.get("id"));
        Assert.assertEquals(resJP.getString("title"),expBody.get("title"));
        Assert.assertEquals(resJP.getString("body"),expBody.get("body"));

        // Junit Assert'de önce expected, sonra actual,
        // TestNG'de önce actual, sonra expected yazılır.


    }
}
