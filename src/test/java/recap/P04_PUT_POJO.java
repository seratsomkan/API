package recap;

import baseUrl.ReqResBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.ReqResPOJO;

import static io.restassured.RestAssured.given;

public class P04_PUT_POJO extends ReqResBaseUrl {

    @Test
    public void test(){
        /*
            https://reqres.in/api/users/2 URL'sine header değeri "Content-Type","application/json" olan bir PUT isteği gönderin.
            Kullanıcıyı şu şekilde güncelleyin:
                 {
                    "name": "John Doe",
                    "job": "Manager"
                  }
            Yanıtın durum kodunun 200 olduğunu doğrulayın ve name alanının John Doe olduğunu doğrulayın.
        */

        specReqRes.pathParams("pp1","api","pp2","users","pp3","2");

        ReqResPOJO reqBody = new ReqResPOJO("John Doe","Manager");

        Response response = given().contentType(ContentType.JSON).spec(specReqRes)
                                    .when().body(reqBody).put("/{pp1}/{pp2}/{pp3}");

        JsonPath resJP = response.jsonPath();

        Assertions.assertEquals(200,response.getStatusCode());
        Assertions.assertEquals("John Doe",resJP.getString("name"));
    }

}
