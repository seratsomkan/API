package pojo;

public class RestFullExpBodyPOJO {

    private int bookingid;
    private RestFullReqBodyPOJO booking;

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public RestFullReqBodyPOJO getBooking() {
        return booking;
    }

    public void setBooking(RestFullReqBodyPOJO booking) {
        this.booking = booking;
    }

    public RestFullExpBodyPOJO(int bookingid, RestFullReqBodyPOJO booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public RestFullExpBodyPOJO() {
    }

    @Override
    public String toString() {
        return "RestFullExpBodyPOJO" +
                "bookingid: " + bookingid +
                ", booking: " + booking;
    }
}
