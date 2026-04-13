import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private Room room;
    private List<Guest> listOfGuests = new ArrayList<>();
    private LocalDate fromDate;
    private LocalDate toDate;
    private VacationType typeOfVacation;  // enum typ rekreační nebo pracovní

    public enum VacationType {
        RECREATIONAL,  // rekreační pobyt
        BUSINESS       // pracovní pobyt
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. M. yyyy"); // format pro datum

    public Booking(Room room, List<Guest> listOfGuests, LocalDate fromDate, LocalDate toDate, VacationType typeOfVacation) {
        this.room = room;
        this.listOfGuests.addAll(listOfGuests);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.typeOfVacation = typeOfVacation;
    }

    // Konstruktor pro rekreační pobyt na 6 noci
    public Booking(Room room, List<Guest> listOfGuests) {
        this(room, listOfGuests, LocalDate.now(), LocalDate.now().plusDays(6), VacationType.RECREATIONAL);
    }


    public Room getRoom() {
        return room;
    }

    public void setRoomNumber(Room room) {
        this.room = room;
    }

    public List<Guest> getListOfGuests() {
        return listOfGuests;
    }

    public void setListOfGuests(List<Guest> listOfGuests) {
        this.listOfGuests = listOfGuests;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public VacationType getTypeOfVacation() {
        return typeOfVacation;
    }

    public void setWorking(VacationType typeOfVacation) {
        this.typeOfVacation = typeOfVacation;
    }

    // počet hostů dané rezervace celkem
    public int getGuestsCount(Booking booking) {
        return booking.getListOfGuests().size();
    }

    // Počet nocí na pobyt
    public long getBookingLength() {
        return ChronoUnit.DAYS.between(this.fromDate, this.toDate);
    }

    // Cena rezervace
    public BigDecimal getTotalPrice() {
        long numberOfNights = getBookingLength();
        BigDecimal pricePerNight = this.getRoom().getPricePerNight();

        return pricePerNight.multiply(BigDecimal.valueOf(numberOfNights));
    }

    // Formátovaný výstup
    public void getFormattedSummary() {
        for (Guest guest : listOfGuests) {
            String seaView = "ne";

            if (this.getRoom().isHasSeaView()) {
                seaView = "ano";
            }

            System.out.println(this.getFromDate().format(formatter) + " až " + this.getToDate().format(formatter) + ": "
                    + guest.getFirstName() + " " + guest.getLastName() + " (" + guest.getDateOfBirth().format(formatter) + ")["
                    + getListOfGuests().size() + "," + seaView + "] za "
                    + this.getTotalPrice().intValue() + " Kč");
        }
    }
}
