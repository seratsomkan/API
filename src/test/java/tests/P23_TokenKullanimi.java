package tests;

import baseUrl.RESTFUL_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import testDatas.RestfullDatas;

import static io.restassured.RestAssured.given;

public class P23_TokenKullanimi extends RESTFUL_BaseUrl {

    static String token;

    /* **************************************NOT******************************************
        1- Testi yapabilmek için önce token oluşturuyoruz.
        2- Oluşturulan token'i kullanarak, kullanıcı değerlerini güncelliyoruz.
        3- Testi 'Class Level'dan çalıştırıyoruz çünkü önce token oluşturmamız gerekiyor,
           sonra token kullanarak UPDATE işlemi yapıyoruz.
        4- test01 ile test02 arasındaki fark;
           test01 token kullanarak UPDATE edildi. FAKAT test02 Authorization kullanarak UPDATE edildi.
       ***********************************************************************************
     */

    @Test
    public void createToken(){
        /*
            https://restful-booker.herokuapp.com/booking/3170 (id güncellenmeli)
            adresindeki rezervasyon bilgilerini
                -H 'Content-Type: application/json' \
                -H 'Accept: application/json' \
                -H 'Cookie: token=abc123' \ veya -H 'Authorization:Basic YWRtaW46cGFzc3dvcmQxMjM=' \
            header değerleriyle PUT request göndererek update ediniz.

            Token Oluşturma
            Content-Type: application/json header derğeriyle aşağıdaki body ile
            {
               "username" : "admin",
               "password" : "password123"
            }
            Post Request yapınız

        */

        specRestFull.pathParam("pp1","auth");

        // 'data' isminde JSONObject oluşturuldu.
        JSONObject data = new JSONObject();
        data.put("username","admin");
        data.put("password","password123");

        // İstek gönderildi.
        Response response = given().contentType(ContentType.JSON).spec(specRestFull).when().body(data.toString()).post("/{pp1}");

        // Alınan bilgi, JsonPath formatına dönüştürüldü.
        JsonPath resJP = response.jsonPath();

        // Class Level'da oluşturduğumuz 'token' variable'ına, alınan token bilgisi atandı.
        token = resJP.getString("token");

        System.out.println("Token: "+token);

    }

    @Test
    public void test01(){

        // Yolumuzu tanımladık.
        specRestFull.pathParams("pp1","booking","pp2","17");

        // RestfullDatas Class'ından, JSONObject türünden reqBody oluşturuldu.
        JSONObject reqBody = RestfullDatas.jsonDataOlustur();

        //
        Response response = given().contentType(ContentType.JSON).spec(specRestFull)
                                    .when().body(reqBody.toString())
                                    .header("Content-Type","application/json") // JSON formatında olduğu belirtilir.
                                    .header("Accept","application/json") // API'nin JSON formatında yanıt göndermesi gerektiğini belirtir.
                                    .header("Cookie","token="+token) // API'ye, yetkili kullanıcı olarak erişim sağlar.
                                    .put("/{pp1}/{pp2}");
        // NOT: Postman'de PUT işlemi yaparken, header kısımlarına girdiğin değerleri(Content-Type, Accept, Cookie),
        // burada otomasyon ile girdik.

        response.prettyPrint();
    }

    @Test
    public void test02(){

        // Yolumuzu tanımladık.
        specRestFull.pathParams("pp1","booking","pp2","17");

        // RestfullDatas Class'ından, JSONObject türünden reqBody oluşturuldu.
        JSONObject reqBody = RestfullDatas.jsonDataOlustur();

        //
        Response response = given().contentType(ContentType.JSON).spec(specRestFull)
                                    .when().body(reqBody.toString())
                                    .header("Content-Type","application/json") // JSON formatında olduğu belirtilir.
                                    .header("Accept","application/json") // API'nin JSON formatında yanıt göndermesi gerektiğini belirtir.
                                    .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=") // API'ye, yetkili kullanıcı olarak erişim sağlar.
                                    .put("/{pp1}/{pp2}");
        // NOT: Postman'de PUT işlemi yaparken, header kısımlarına girdiğin değerleri(Content-Type, Accept, Cookie),
        // burada otomasyon ile girdik.

        response.prettyPrint();
    }


}
