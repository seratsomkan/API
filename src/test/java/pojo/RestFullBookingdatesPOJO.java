package pojo;

public class RestFullBookingdatesPOJO {

    // 1- Tüm variable'lar private şeklinde oluşturulur.
    private String checkin;
    private String checkout;

    // 2- Daha sonra, bunların Getter&Setter'ları oluşturulur.
    public String getCheckin() {
        return checkin;
    }
    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // 3- Bu variable'lar için parametreli constructor oluşturulur.

    public RestFullBookingdatesPOJO(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // 4- Parametresiz bir constructor oluşturulur.

    public RestFullBookingdatesPOJO() {
    }

    // 5- ToString oluşturulur.

    @Override
    public String toString() {
        return "Bookingdates Bilgileri" + "\n" +
               "Checkin Date: " + checkin + "\n" +
                "Checkout Date: " + checkout;
    }


}
