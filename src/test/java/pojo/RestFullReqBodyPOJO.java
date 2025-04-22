package pojo;

public class RestFullReqBodyPOJO {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private RestFullBookingdatesPOJO bookingdates;
    private String additionalneeds;

    // GETTER&SETTER

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public RestFullBookingdatesPOJO getBookingdates() {
        return bookingdates;
    }

    public void setBookingdatesPOJO(RestFullBookingdatesPOJO bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public RestFullReqBodyPOJO(String firstname, String lastname, int totalprice, boolean depositpaid, RestFullBookingdatesPOJO bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public RestFullReqBodyPOJO() {
    }

    @Override
    public String toString() {
        return "Rezervasyon Bilgileriniz: " +
                "Firstname: " + firstname + "\n"+
                "Lastname: " + lastname + "\n" +
                "Totalprice: " + totalprice + "\n" +
                "Depositpaid: " + depositpaid + "\n" +
                "Bookingdates: " + bookingdates + "\n" +
                "Additionalneeds: " + additionalneeds;
    }
}

