package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P06_POST_ResponseBodyTest {

    @Test
    public void test(){
        /*
        https://jsonplaceholder.typicode.com/posts url'ine
        asagidaki body ile bir POST request gonderdigimizde
            {
                title":"API",
                "body":"API ogrenmek ne guzel",
                "userId":10,
            }
        donen Response'un,
        status code'unun 201,
        ve content type'inin application/json
        ve Response Body'sindeki,
          "title"'in "API" oldugunu
          "userId" degerinin 100'den kucuk oldugunu
          "body" nin "API" kelimesi icerdigini
       test edin.

        */

        // 1- EndPoint ve POST Request olduğundan requestBody Hazırlama
        String url = "https://jsonplaceholder.typicode.com/posts";

        JSONObject reqBody = new JSONObject();
        reqBody.put("title","API")
               .put("body","API ogrenmek ne guzel")
               .put("userId",10);

        // 2- Expected Data - YOK

        // 3- Response kaydı
        Response response = given().contentType(ContentType.JSON)
                            .when().body(reqBody.toString()).post(url);
        /*
            POST,PUT,PATCH yapılıyorsa, contentType'ı belirtilmesi gerekir.
            Ayrıca yapılan işleme göre sona post(url) veya put(url) yazılır.
         */

        // 4- Assertion İşlemleri
        response.then().assertThat()
                        .statusCode(201)
                        .contentType("application/json")
                        .body("title", equalTo("API"))
                        .body("body", containsString("API"))
                        .body("userId",lessThan(100));
        /*
            NOT1:
            Matchers'ı silip, daha sonra equalTo'nun üzerine gelip import ettik.
            Eklenen importtaki equalTo kısmını silip * koyduk. böylelikle diğer metotları da kullanabiliriz.

            NOT2:
            Body'de herhangi bir kelimeyi içerdiğini kontrol etmek için containsString kullanılır.

            Not3:
            Herhangi bir sorgu yaparken belli sayıdan küçük olduğunu sorgulamak için "lessThan",
            belli bir sayıdan büyük olduğunu sorgulamak için "greaterThen" kullanılır.
         */

    }
}
