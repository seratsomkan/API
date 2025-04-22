package recap;

import baseUrl.ReqResBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P03_POST_apiSorgusu extends ReqResBaseUrl {

    @Test
    public void test01(){
        /*
            https://reqres.in/api/users URL'sine
            header derğeri "Content-Type", "application/json" olan bir POST isteği gönderin.
            İstek için JSON formatında şu bilgileri içeren bir body kullanın: { "name": "morpheus", "job": "leader" }
            Gelen yanıtın durum kodunun 201 olduğunu doğrulayın.
            Yanıtın JSON body'sinde name alanının morpheus olduğunu kontrol edin.
        */

        specReqRes.pathParams("pp1","api","pp2","users");

        JSONObject reqBody=new JSONObject();
        reqBody.put("name", "morpheus");
        reqBody.put("job","leader");

        Response response=given().spec(specReqRes).contentType(ContentType.JSON)
                .header("Content-Type","application/json").when().body(reqBody.toString())
                .post("/{pp1}/{pp2}");
        JsonPath resJP = response.jsonPath();

        //response.prettyPrint();

        Assertions.assertEquals(201,response.getStatusCode());
        Assertions.assertEquals("morpheus",resJP.getString("name"));
    }
}
