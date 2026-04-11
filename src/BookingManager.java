import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private List<Booking> bookingList = new ArrayList<>();

    // Vložení nové rezervace do seznamu: addBooking(booking)
    public void addBooking(Booking booking) {
        bookingList.add(booking);
    }

    // Získání rezervace se zadaným indexem ze seznamu: getBooking(index).
    public Booking getBooking(int index) {
        return bookingList.get(index);
    }

    // Získání seznamu rezervací: getBookings()
    public List<Booking> getBookings() {
        return bookingList;
    }

    // Vymazání seznamu rezervací: clearBookings()
    public void clearBookings() {
        bookingList.clear();
    }

    // Počet pracovních pobytů
    public int getNumberOfWorkingBookings() {
        int numberOfWorkingBookings = 0;

        for (Booking booking : bookingList){
            if (booking.getTypeOfVacation().equals(Booking.VacationType.BUSINESS)) {
                numberOfWorkingBookings++;
            }
        }
        return numberOfWorkingBookings;
    }

    // počet hostů dané rezervace celkem
    public int getGuestsCount(Booking booking) {
        return booking.getListOfGuests().size();
    }

    // Průměrný počet hostů na rezervaci
    public double getAverageGuests() {
        int numberOfBookings = bookingList.size();
        int numberOfGuests = 0;

        for (Booking booking : bookingList){
            numberOfGuests += getGuestsCount(booking);
        }

        return (double) numberOfGuests/numberOfBookings;
    }

    // Vrať prvních osm rekreačních rezervací
    public List<Booking>  getTopNHolidayBookings() {
        int N = 8;
        List<Booking>  TopNHolidayBookings = new ArrayList<>();

        for (Booking booking : bookingList){
            if (booking.getTypeOfVacation().equals(Booking.VacationType.RECREATIONAL)) {
                TopNHolidayBookings.add(booking);
            }

            if (TopNHolidayBookings.size() == N) {
                break;
            }
        }
        return TopNHolidayBookings;
    }

    // Statistika podle počtu hostů
    public void printGuestStatistics() {
        int numberOfReservationsWithOneGuest = 0;
        int numberOfReservationsWithTwoGuests = 0;
        int numberOfReservationsWithMoreThanTwoGuests = 0;

        for (Booking booking : bookingList){
            if (booking.getListOfGuests().size() == 1) {
                numberOfReservationsWithOneGuest++;
            } else if (booking.getListOfGuests().size() == 2) {
                numberOfReservationsWithTwoGuests++;
            } else if (booking.getListOfGuests().size() > 2) {
                numberOfReservationsWithMoreThanTwoGuests++;
            }
        }

        System.out.println("Počet rezervací s jedním hostem : " + numberOfReservationsWithOneGuest);
        System.out.println("Počet rezervací se dvěma hosty : " + numberOfReservationsWithTwoGuests);
        System.out.println("Počet rezervací s více než dvěma hosty : " + numberOfReservationsWithMoreThanTwoGuests);
    }
}
