package recap;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class P01_GET_apiSorgusu {

    @Test
    public void test01(){
        /*
            https://reqres.in/api/users/2 URL'sine bir GET isteği gönderin.
            Gelen yanıtın durum kodunun 200 olduğunu doğrulayın.
            Yanıtın JSON body'sinde yer alan data.id değerinin 2 olduğunu doğrulayın.
            Yanıt süresinin 2 saniyeden kısa olduğunu kontrol edin.
        */

        // 1- Endpoint hazırlamak
        String url = "https://reqres.in/api/users/2";

        // 2- Expected body -yok

        // 3- Response kaydetme
        Response response = given().when().get(url);

        // 4- Sorgulama
        response.then().assertThat().statusCode(200);

        Assertions.assertEquals(2,response.jsonPath().getInt("data.id"));

        Assertions.assertTrue(response.getTime()<2000,"Yanıt süresi 2 saniyeden fazladır.");


    }
}
