package pojo;

public class PojoJPH {


    // 1- Tüm variable'lar private oluşturuldu.
    private String title;
    private String body;
    private int userId;
    private int id;

    // 2- GETTER & SETTER OLUŞTUR (Sağ click --> Generate'den)

    // ********* TITLE *********
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // ********* BODY *********
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    // ********* USERID *********
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // ********* ID *********
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // 3- Tüm variable'ları içeren Parametreli Constructor oluşturuldu.(Sağ click --> Generate'den)

    public PojoJPH(String title, String body, int userId, int id) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
    }

    // 4- Default Cons. öldüğü için, onun işlevini yapacak Parametresiz Constructor oluşturuldu.
    // (Sağ click --> Generate'den)

    public PojoJPH() {

    }

    // 5- Verileri yazdırmak istersek, toString metodu da eklendi.
    // (Sağ click --> Generate'den)

    @Override
    public String toString() {
        return "PojoJPH{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}
