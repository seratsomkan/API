package testDatas;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RestfullDatas {

    public static JSONObject jsonDataOlustur(){

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2021-06-01");
        bookingdates.put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject();
        reqBody.put("firstname","Serat");
        reqBody.put("lastname","Somkan");
        reqBody.put("totalprice","500");
        reqBody.put("depositpaid",false);
        reqBody.put("bookingdates",bookingdates);
        reqBody.put("additionalneeds","wifi");

        return reqBody;
    }

    public static JSONObject bookingdatesOlustur(){

        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin","2021-06-01");
        bookingdates.put("checkout","2021-06-10");

        return bookingdates;
    }

    public static JSONObject parametreliBookingdatesOlustur(String checkin,String checkout){

        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin",checkin);
        bookingdates.put("checkout",checkout);

        return bookingdates;
    }

    public static JSONObject parametreliReqBodyOlustur(String firstname, String lastname, int totalprice, boolean depositpaid, JSONObject bookingdates, String additionalneeds){

        JSONObject innerData = new JSONObject();

        innerData.put("checkin","2021-06-01");
        innerData.put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject();

        reqBody.put("firstname",firstname);
        reqBody.put("lastname",lastname);
        reqBody.put("totalprice",totalprice);
        reqBody.put("depositpaid",depositpaid);

        reqBody.put("bookingdates",bookingdates);

        reqBody.put("additionalneeds",additionalneeds);

        return reqBody;
    }

    public static Map<String,Object> mapDataOlustur(){

        Map<String,Object> innerMapBody = new HashMap<>();
        innerMapBody.put("checkin","2021-06-01");
        innerMapBody.put("checkout","2021-06-10");

        Map<String,Object> reqMapBody = new HashMap<>();
        reqMapBody.put("firstname","Serat");
        reqMapBody.put("lastname","Somkan");
        reqMapBody.put("totalprice",1500.0);
        reqMapBody.put("depositpaid",false);
        reqMapBody.put("bookingdates",innerMapBody);
        reqMapBody.put("additionalneeds","wi-fi");

        return reqMapBody;
    }

    public static Map<String,Object> expMapDataOlustur(){

        Map<String,Object> expMapBody = new HashMap<>();

        expMapBody.put("bookingid",24);
        expMapBody.put("booking",mapDataOlustur());

        return expMapBody;
    }
}
