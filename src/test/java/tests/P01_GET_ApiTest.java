package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P01_GET_ApiTest {

    /*
        https://restful-booker.herokuapp.com/booking/10 url’ine
        bir GET request gonderdigimizde donen Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.
    */

    /*
        Tüm API sorguları 4 aşamada yapılır;

            1- EndPoint hazırlanır. (URL ve varsa(PUT-POST-PATCH) request(talep) Body hazırlanır.

            2- Soruda verilmiş ise Expected Data hazırlanır. Verilmemişse hazırlanmaz.

            3- Dönen cevap kaydedilir.

            4- Assertion işlemleri yapılır.
     */

    @Test
    public void test(){

        //-----------  1- EndPoint hazırlanır.
        String url = "https://restful-booker.herokuapp.com/booking/10";

        //-----------  2- Soruda verilmiş ise Expected Data hazırlanır. Verilmemişse hazırlanmaz.
            // soruda verilmemiş

        //-----------  3- Dönen cevap kaydedilir.

            // Dönen cevap objenin içine kaydedilmelidir. Objeyi de hazır bir class'tan oluşturmalıyım;
        Response response = given().when().get(url); // given() yazdıktan sonra, Restassure'den import ettik.
                                                     // RestAssured.get(url) şeklinde de yazılabilir.
            //açıklaması; url'e sorgu gönderdiğim zaman, dönen cevabı response objesine kaydet.

            //objenin içerisine gelen bilgileri görelim;
        //response.prettyPrint(); // tüm cevabı yazdıracağı için sistemi yorar.
        //response.prettyPeek(); // daha kapsamlı bilgiler yazdırır.

        //-----------  4- Assertion işlemleri yapılır.
            // istenilen değerleri, oluşturulan objeden çekeriz.
        System.out.println("Status Değeri: " +response.getStatusCode()); // 200
        System.out.println("Content Type Değeri: " +response.getContentType()); // application/json; charset=utf-8
        System.out.println("Server İsimli Header Değeri: " +response.getHeader("Server")); // Cowboy
            /* HTTP'nin header değerlerini alır.(Server, Connection, X-Powered-By gibi)
               Header değerleri, postman üzerinden kontrol edebiliriz. "Send" ile talebi gönderdikten sonra
               cevabın header bölümünden kontrol edilir.
            */
        System.out.println("Status Line Değeri: " +response.getStatusLine()); // HTTP/1.1 200 OK
        System.out.println("Response(Cevap) Süresinin Değeri: " +response.getTime()); // 5 saniyeden kısa
    }

}
