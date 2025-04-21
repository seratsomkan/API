package tests;

import baseUrl.RESTFUL_BaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class P15_HerOkuAppQueryParams extends RESTFUL_BaseUrl {


    @Test
    public void test01(){
        /*
            https://restful-booker.herokuapp.com/booking endpointine
            gerekli Query parametrelerini yazarak
            “firstname” degeri “Eric” olan rezervasyon oldugunu test edecek
            bir GET request gonderdigimizde, donen response’un status code’unun
            200 oldugunu ve “Eric” ismine sahip en az bir booking oldugunu test edin
        */

        specRestFull.pathParam("pp1","booking").queryParam("firstname","Eric");
        /*
            booking → Path parametresi
            firstname=Eric → Query parametresi

            queryParam: URL'ye bir query string parametresi ekler.
            "firstname" adlı parametreye "Eric" değeri atanır.
            Bu, '?firstname=Eric' şeklinde URL’ye eklenir. Yani son hali;
            https://example.com/api/booking?firstname=Eric şeklinde olur.

        */

        Response response = given().spec(specRestFull).when().get("/{pp1}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200)
                .body("size()",Matchers.greaterThan(0));

    }

    @Test
    public void test02(){
        /*
            https://restful-booker.herokuapp.com/booking endpointine
            gerekli Query parametrelerini yazarak
            "firstname" degeri "Jim" ve "lastname" degeri "Jackson" olan rezervasyon oldugunu
            test edecek bir GET request gonderdigimizde,
            donen response'un status code'unun 200 oldugunu ve
            "Jim Jackson" ismine sahip en az bir booking oldugunu test edin
        */

        specRestFull.pathParam("pp1","booking").queryParams("firstname","Josh",
                                                            "lastname","Allen");

        Response response = given().spec(specRestFull).when().get("/{pp1}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200)
                .body("size()", Matchers.greaterThan(0)); //"size()", JSON dizisinin uzunluğunu (eleman sayısını) döndürür.


    }
}
