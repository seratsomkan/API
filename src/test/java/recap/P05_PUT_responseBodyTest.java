package recap;

import baseUrl.ReqResBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.ReqResPOJO;

import static io.restassured.RestAssured.given;

public class P05_PUT_responseBodyTest extends ReqResBaseUrl {

    @Test
    public void test(){
        /*
            https://reqres.in/api/users/2 URL'ine
            aşağıdaki JSON body ile PUT request gönderildiğinde
            {
                "name": "Serat",
                "job": "Junior QA"
            }
            dönen Response’un:
            Status code’unun 200 olduğunu,
            Content type’ının application/json; charset=utf-8 olduğunu,
            Response Body’sini updatedAt hariç aşağıdaki expectedBody ile birebir eşleştiğini test ediniz.
            {
                "name": "Serat",
                "job": "Junior QA",
                "updatedAt": "2025-03-05T12:34:56.789Z"
            }
        */

        specReqRes.pathParams("pp1","api","pp2","users","pp3","2");

        ReqResPOJO reqBody = new ReqResPOJO("Serat","Junior QA");
        ReqResPOJO expBody = new ReqResPOJO("Serat","Junior QA");

        Response response = given().contentType(ContentType.JSON).spec(specReqRes).when().body(reqBody).put("/{pp1}/{pp2}/{pp3}");

        Assertions.assertEquals(200,response.getStatusCode());
        Assertions.assertEquals(expBody.getName(),response.jsonPath().getString("name"));
        Assertions.assertEquals(expBody.getJob(),response.jsonPath().getString("job"));

        /*
            ******** 2.YOL (JSON OBJECT) ********

            JSONObject reqBody = new JSONObject();
            reqBody.put("name","Serat");

            JSONObject expBody = new JSONObject();
            reqBody.put("job","Junior QA");

            Assertions.assertEquals(expBody.get("name"),response.jsonPath().getString("name"));
            Assertions.assertEquals(expBody.get("job"),response.jsonPath().getString("job"));
        */
    }

}
