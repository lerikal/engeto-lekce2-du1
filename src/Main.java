import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // format pro datum
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. M. yyyy");

        // testovani priprav
        Guest guest1 = new Guest("Adéla", "Malíková", LocalDate.of(1993, 3, 13));
        Guest guest2 = new Guest("Jan", "Dvořáček", LocalDate.of(1995, 5, 5));
        guest2.setDateOfBirth(LocalDate.of(1995, 4, 5));
        System.out.printf("%s %s (%s)%n", guest2.getFirstName(), guest2.getLastName(), guest2.getDateOfBirth().format(formatter));

        // pokoje
        Room room1 = new Room(1, 1, true, true, BigDecimal.valueOf(1000.0));
        Room room2 = new Room(2, 1, true, true, BigDecimal.valueOf(1000.0));
        Room room3 = new Room(3, 3, false, true, BigDecimal.valueOf(2400.0));

        // rezervace

        // Pro Adélu rezervuj pokoj č.1 od 19. do 26. 7. 2021.
        List<Guest> guests = new ArrayList<>();
        guests.add(guest1);
        Booking booking1 = new Booking(1, guests, LocalDate.of(2021, 7, 1), LocalDate.of(2021, 7, 19), "rekreační");

        // Pro oba (společná rezervace) na pokoj č. 3 od 1. do 14. 9. 2021.
        guests.add(guest2);
        Booking booking2 = new Booking(3, guests, LocalDate.of(2021, 9, 1), LocalDate.of(2021, 9, 14), "rekreační");

        // pridat rezervace do seznamu rezervaci
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        System.out.println("----- Seznam rezervací ------");
        for (Booking booking: bookings) {
            for (Guest guest: booking.getListOfGuests()) {
                System.out.println("Číslo pokoje: " + booking.getRoomNumber()
                                 + ", host: " + guest.getFirstName() + " " + guest.getLastName() + " (" +  guest.getDateOfBirth().format(formatter) + ")"
                                 + ", od: " + booking.getFromDate().format(formatter)
                                 + " do: " + booking.getToDate().format(formatter)
                                 + ", cíl pobytu: " + booking.getTypeOfVacation()
                                 + "."
                );
            }
        }
    }
}