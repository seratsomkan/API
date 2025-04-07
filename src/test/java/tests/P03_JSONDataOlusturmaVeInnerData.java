package tests;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class P03_JSONDataOlusturmaVeInnerData {

    /*
                        *************NOT*************
        MAP ile JSONObject benzer yapıya sahiptir FAKAT;
        Map oluştururken key-value için data türü belirlenmek zorundadır.
        JSON'da buna gerek yoktur. JSON daha esnektir.
    */

    @Test
    public void test01(){
        /*
            Asagidaki JSON Objesini olusturup konsolda yazdirin.
            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":1
            }
        */
        JSONObject data = new JSONObject(); // JSONObject'ten, data objesi oluşturuldu.

        data.put("bookingId","1") // data objesinin içerisine değerler girildi. (Key-Value)
            .put("body","Merhaba")
            .put("title","Ahmet");

        System.out.println(data);
    }


    @Test
    public void test02(){

        /*
            Asagidaki JSON Objesini olusturup konsolda yazdirin;
            {
                "firstname":"Jim",
                "additionalneeds":"Breakfast",
                "bookingdates": {
                        "checkin":"2018-01-01",
                        "checkout":"2019-01-01"
             },
                 "totalprice":111,
                 "depositpaid":true,
                 "lastname":"Brown"
             }
         */

        /*
            İç içe veri bulunduran datalarla çalışırken ÖNCE içteki datadan başlanır.
        */

        JSONObject innerData = new JSONObject(); // JSONObject'ten, innerData objesi oluşturuldu.
        JSONObject data = new JSONObject(); // JSONObject'ten, data objesi oluşturuldu.

        innerData.put("checkin","2018-01-01")
                 .put("checkout","2019-01-01");

        data.put("firstname","Jim")
            .put("additionalneeds","Breakfast")
            .put("bookingdates",innerData)
            .put("totalprice",111)
            .put("depositpaid",true)
            .put("lastname","Brown");

        System.out.println(data);
    }
}
