package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class P02_GET_ResponseAssertion {

    /*
        https://restful-booker.herokuapp.com/booking/10 url’ine
        bir GET request gonderdigimizde donen Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        oldugunu otomasyon ile test ediniz.
    */

    @Test
    public void test(){
        String url = "https://restful-booker.herokuapp.com/booking/10";

        Response response = RestAssured.get(url);

        // beklenenleri, response içerisindekiler ile karşılaştıracak;
        response.then().assertThat().statusCode(200)
                                    .contentType("application/json; charset=utf-8")
                                    .header("Server","Cowboy")
                                    .statusLine("HTTP/1.1 200 OK");

        // çıktı olarak bir şey vermiyor. Yani kod hatalı çalışmazsa, doğru olduğu anlamına gelir.
    }
}
