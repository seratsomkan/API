package recap;

import baseUrl.ReqResBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P02_GET_listKullanma extends ReqResBaseUrl {

    @Test
    public void test01(){
        /*
            https://reqres.in/api/users?page=2 URL'sine bir GET isteği gönderin.
            Gelen yanıtın durum kodunun 200 olduğunu doğrulayın.
            Liste içerisindeki kullanıcı sayısının en az 1 olduğunu doğrulayın.
            İlk kullanıcının email adresinin boş olmadığını kontrol edin.
        */
        specReqRes.pathParams("pp1","api","pp2","users").queryParam("page",2);

        Response response = given().spec(specReqRes).when().get("/{pp1}/{pp2}");
        JsonPath jsPath = response.jsonPath();

        //response.prettyPrint();

        Assertions.assertEquals(200,response.getStatusCode());

        Assertions.assertFalse(jsPath.getList("data").isEmpty());

        //System.out.println((String) jsPath.get("data[0].email"));
        Assertions.assertNotNull(jsPath.get("data[0].email"));

        /*
            ****** 2.YOL ******
            String email = jsPath.getList("data.email").get(0).toString();
            Assertions.assertFalse(email.isEmpty());
        */
    }
}
