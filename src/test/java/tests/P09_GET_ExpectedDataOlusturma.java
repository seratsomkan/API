package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P09_GET_ExpectedDataOlusturma {

    @Test
    public void test(){
        /*
            https://jsonplaceholder.typicode.com/posts/22 url'ine
            bir GET request yolladigimizda donen response body'sinin
            asagida verilen ile ayni oldugunu test ediniz

            Response body :(EXPECTED DATA)
            {
                "userId": 3,
                "id": 22,
                "title": "dolor sint quo a velit explicabo quia nam",
                "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
                        um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
            }
        */

        // 1- Endpoint Hazırlama
        String url = "https://jsonplaceholder.typicode.com/posts/22";

        // 2- Expected Data Olusturma
        JSONObject expBody = new JSONObject();
        expBody.put("userId",3)
                .put("id",22)
                .put("title","dolor sint quo a velit explicabo quia nam")
                .put("body","eos qui et ipsum ipsam suscipit aut\n" +
                        "sed omnis non odio\n" +
                        "expedita earum mollitia molestiae aut atque rem suscipit\n" +
                        "nam impedit esse");

        // 3- Response
        Response response = given().when().get(url);

        // 4- Assertion
        /*
                    ************************** NOT **************************
            Expected Data hazırlanmışsa, bu verilerle dönen cevabı karşılaştırmak istersek
            Dönen cevabında JsonPath şeklinde ulaşılabilir olması lazım.

            YANİ; ExpectedData JsonObject'ten oluşturduğumuz için;
            expBody içerisindekileri, responso obje içerisindekileri karşılaştırabilmek için
            response'i jSonPath'e çevirdik.
        */
        JsonPath respJS = response.jsonPath(); // response'u, jsonPath'e çevirdik.
        assertEquals(expBody.get("userId"),respJS.get("userId"));
        assertEquals(expBody.get("title"),respJS.get("title"));
        assertEquals(expBody.get("body"),respJS.get("body"));




    }
}
