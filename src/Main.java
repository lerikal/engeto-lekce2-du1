import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // naplneni dat
        BookingManager data = new BookingManager();
        data = fillBookings();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. M. yyyy"); // format pro datum

        // vypisy
        System.out.println("Počet pracovních pobytů: " + data.getNumberOfWorkingBookings() + "\n");

        System.out.println("Průměrný počet hostů na rezervaci: " + data.getAverageGuests() + "\n");

        System.out.println("Prvních osm rekreačních rezervací:");
        for (Booking booking : data.getTopNHolidayBookings()) {
            for (Guest guest : booking.getListOfGuests()) {
                System.out.println("Rezervace pro: " + guest.getFirstName() + ", " + guest.getLastName() + " (" + guest.getDateOfBirth().format(formatter) + ") na: 2. termín: "
                    + booking.getFromDate().format(formatter) + " - " + booking.getToDate().format(formatter) + " pracovní pobyt: "
                    + (booking.getTypeOfVacation().equals(Booking.VacationType.BUSINESS)? "ano": "ne"));
            }

        }

        System.out.println("\n" + "Statistiky hostů:");
        data.printGuestStatistics();

        System.out.println("\n" + "Počet pracovních pobytů: " + data.getNumberOfWorkingBookings());

        System.out.println("\n" + "Formátovaný výpis všech rezervací v systému:");
        for (Booking booking : data.getBookings()) {
            booking.getFormattedSummary();
        }

        // dalsi testy - pro uplnost
        System.out.println("\n" + "Vypis prvni rezervace: ");
        data.getBooking(1).getFormattedSummary();
    }

    // Domací úkol - lekce 3
    public static BookingManager fillBookings() {
        List<Guest> guests = new ArrayList<>();
        BookingManager bookings = new BookingManager();

        // pokoje
        Room room1 = new Room(1, 1, true, true, BigDecimal.valueOf(1000.0));
        Room room2 = new Room(2, 1, true, true, BigDecimal.valueOf(1000.0));
        Room room3 = new Room(3, 3, false, true, BigDecimal.valueOf(2400.0));

        //hosty
        Guest guest101 = new Guest("Karel", "Dvořák", LocalDate.of(1990, 5, 15));
        Guest guest102 = new Guest("Karel", "Dvořák", LocalDate.of(1979, 1, 3));
        Guest guest103 = new Guest("Karolína", "Tmavá", LocalDate.of(1993, 9, 5));

        // rezervace
        guests.add(guest101);
        Booking booking1 = new Booking(room3, guests, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 7), Booking.VacationType.BUSINESS);
        guests.clear();
        bookings.addBooking(booking1);

        guests.add(guest102);
        Booking booking2 = new Booking(room2, guests, LocalDate.of(2023, 7, 18), LocalDate.of(2023, 7, 21), Booking.VacationType.RECREATIONAL);
        guests.clear();
        bookings.addBooking(booking2);

        guests.add(guest103);
        guests.add(guest101);
        Booking booking3 = new Booking(room3, guests, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 31), Booking.VacationType.BUSINESS);
        guests.clear();
        bookings.addBooking(booking3);

        LocalDate startDate = LocalDate.of(2023, 8, 1);
        LocalDate endDate = startDate.plusDays(1);
        guests.add(guest103);
        for (int i = 0; i < 10; i++) {
            bookings.addBooking(new Booking(room2, guests, startDate, endDate, Booking.VacationType.RECREATIONAL));
            startDate = endDate.plusDays(1);
            endDate = startDate.plusDays(1);
        }
        guests.clear();

        return bookings;
    }




    // Domací úkol - lekce 2
    public static void fillBookingsOld() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. M. yyyy"); // format pro datum
        List<Guest> guests = new ArrayList<>(); // list pro hosty
        List<Booking> bookings = new ArrayList<>(); // list pro rezervace

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
        guests.add(guest1);
        Booking booking1 = new Booking(room1, guests, LocalDate.of(2021, 7, 1), LocalDate.of(2021, 7, 19), Booking.VacationType.BUSINESS);
        guests.clear();

        // Pro oba (společná rezervace) na pokoj č. 3 od 1. do 14. 9. 2021.
        guests.add(guest1);
        guests.add(guest2);
        Booking booking2 = new Booking(room3, guests, LocalDate.of(2021, 9, 1), LocalDate.of(2021, 9, 14), Booking.VacationType.RECREATIONAL);
        guests.clear();

        // pridat rezervace do seznamu rezervaci
        bookings.add(booking1);
        bookings.add(booking2);

        System.out.println("----- Základní seznam rezervací ------");
        for (Booking booking: bookings) {
            for (Guest guest: booking.getListOfGuests()) {
                System.out.println("Číslo pokoje: " + booking.getRoom().getRoomNumber()
                        + ", host: " + guest.getFirstName() + " " + guest.getLastName() + " (" +  guest.getDateOfBirth().format(formatter) + ")"
                        + ", od: " + booking.getFromDate().format(formatter)
                        + " do: " + booking.getToDate().format(formatter)
                        + ", typ pobytu: " + booking.getTypeOfVacation()
                        + "."
                );
            }
        }
    }
}