package recap;

import baseUrl.ReqResBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P06_DELETE extends ReqResBaseUrl {

    @Test
    public void test01(){
        /*
            https://reqres.in/api/users/57 URL'sine bir DELETE isteği gönderin.
            Gelen yanıtın durum kodunun 204 olduğunu doğrulayın.
        */

        specReqRes.pathParams("pp1","api","pp2","users","pp3","57");

        Response response = given().spec(specReqRes).when().delete("/{pp1}/{pp2}/{pp3}");

        Assertions.assertEquals(204,response.getStatusCode());
    }
}
