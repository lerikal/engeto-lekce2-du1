import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private int roomNumber;
    private List<Guest> listOfGuests = new ArrayList<>();
    private LocalDate fromDate;
    private LocalDate toDate;
    private String typeOfVacation;

    public Booking(int roomNumber, List<Guest> listOfGuests, LocalDate fromDate, LocalDate toDate, String typeOfVacation) {
        this.roomNumber = roomNumber;
        this.listOfGuests.addAll(listOfGuests);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.typeOfVacation = typeOfVacation;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    public String getTypeOfVacation() {
        return typeOfVacation;
    }

    public void setTypeOfVacation(String typeOfVacation) {
        this.typeOfVacation = typeOfVacation;
    }
}
