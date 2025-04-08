package testDatas;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JPHDatas {

    public static int basariliStatusCode = 200;
    public static String contentType = "application/json; charset=utf-8";
    public static String connectionHeader = "keep-alive";

    public static JSONObject expDataOlustur(){

        JSONObject expBody = new JSONObject();

        expBody.put("userId",3);
        expBody.put("id",22);
        expBody.put("title","dolor sint quo a velit explicabo quia nam");
        expBody.put("body","eos qui et ipsum ipsam suscipit aut\n" + "sed omnis non odio\n" + "expedita earum mollitia molestiae aut atque rem suscipit\n" + "nam impedit esse");


        return expBody;
    }

    public static JSONObject expDataParametreli(int userId, int id, String title, String body){

        JSONObject expBody = new JSONObject();

        expBody.put("userId",userId);
        expBody.put("id",id);
        expBody.put("title",title);
        expBody.put("body",body);

        return expBody;
    }

    public static JSONObject expDataOlusturScanner(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Gerekli bilgileri giriniz:");

        JSONObject expBody = new JSONObject();
        System.out.println("UserId giriniz");
        int userId = scanner.nextInt();
        expBody.put("userId",userId);

        System.out.println("Id giriniz");
        int id = scanner.nextInt();
        expBody.put("id",id);

        System.out.println("Title giriniz");
        String title = scanner.nextLine();
        expBody.put("title",title);

        System.out.println("Body giriniz");
        String body = scanner.nextLine();
        expBody.put("body",body);

        return expBody;
    }

    public static JSONObject jSONDataOlustur(){

        JSONObject jSONData = new JSONObject();

        jSONData.put("userId",3);
        jSONData.put("id",70);
        jSONData.put("title","Ahmet");
        jSONData.put("body","Merhaba");

        return jSONData;
    }
    public static JSONObject parametreliJSONDataOlustur(int userId, int id, String title, String body){

        JSONObject jSONData = new JSONObject();

        jSONData.put("userId",userId);
        jSONData.put("id",id);
        jSONData.put("title",title);
        jSONData.put("body",body);

        return jSONData;
    }

    public static Map<String, Object> MapDataOlustur(){

        Map<String,Object> reqMap = new HashMap<>();
        // Boş bir Map nesnesi oluşturuldu.

        reqMap.put("userId",10.0);
        reqMap.put("body","Merhaba");
        reqMap.put("title","Ahmet");
        reqMap.put("id",70.0);
        // Map içerisine veriler eklendi.

        return reqMap;
    }

    public static Map<String,Object> parametreliMapDataOlustur(double userId, String body, String title, double id){

        Map<String,Object> reqMap = new HashMap<>();

        reqMap.put("userId",userId);
        reqMap.put("body",body);
        reqMap.put("title",title);
        reqMap.put("id",id);

        return reqMap;
    }


}
