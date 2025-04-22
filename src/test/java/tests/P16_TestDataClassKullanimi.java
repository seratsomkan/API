package tests;

import baseUrl.JPH_baseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import testDatas.JPHDatas;

import static io.restassured.RestAssured.given;

public class P16_TestDataClassKullanimi extends JPH_baseUrl {

    /*
        https://jsonplaceholder.typicode.com/posts/22 url'ine bir
        GET request yolladigimizda donen response'in
        status kodunun 200 ve
        response body'sinin asagida verilen ile ayni oldugunu test ediniz
          Response body : (expected data)
        {
            "userId": 3,
            "id": 22,
            "title": "dolor sint quo a velit explicabo quia nam",
            "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio
            \nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
    */

    @Test
    public void testDataKullanimi(){

        specJPH.pathParams("pp1","posts","pp2","22");

        JSONObject expData = JPHDatas.expDataOlustur();

        Response response = given().spec(specJPH).when().get("/{pp1}/{pp2}");

        JsonPath resJP = response.jsonPath();

        //response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),JPHDatas.basariliStatusCode);
        Assert.assertEquals(resJP.getInt("userId"),expData.get("userId"));
        Assert.assertEquals(resJP.getInt("id"),expData.get("id"));
        Assert.assertEquals(resJP.getString("title"),expData.get("title"));
        Assert.assertEquals(resJP.getString("body"),expData.get("body"));
    }

    @Test
    public void testDataKullanimi02(){

        //parametreli metot ile
        specJPH.pathParams("pp1","posts","pp2","22");

        JSONObject expData = JPHDatas.expDataParametreli(3,22,"dolor sint quo a velit explicabo quia nam","eos qui et ipsum ipsam suscipit aut\n" + "sed omnis non odio\n" + "expedita earum mollitia molestiae aut atque rem suscipit\n" + "nam impedit esse");

        Response response = given().spec(specJPH).when().get("/{pp1}/{pp2}");

        JsonPath resJP = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(),JPHDatas.basariliStatusCode);
        Assert.assertEquals(resJP.getInt("userId"),expData.get("userId"));
        Assert.assertEquals(resJP.getInt("id"),expData.get("id"));
        Assert.assertEquals(resJP.getString("title"),expData.get("title"));
        Assert.assertEquals(resJP.getString("body"),expData.get("body"));
    }

    /*
    @Test
    public void testDataKullanimi03(){

        BURAYA SCANNER METODU İLE DOLDUR

    }

    */

}
