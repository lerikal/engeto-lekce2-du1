import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
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

        /*
        // test 1: Zkus přidat dvě různé rezervace pro jednoho hosta na různé pokoje
        guests.add(guest1);
        Booking booking3 = new Booking(room1, guests, LocalDate.of(2021, 7, 1), LocalDate.of(2021, 7, 19), Booking.VacationType.BUSINESS);
        Booking booking4 = new Booking(room2, guests, LocalDate.of(2021, 7, 1), LocalDate.of(2021, 7, 19), Booking.VacationType.BUSINESS);
        guests.clear();

        bookings.add(booking3);
        bookings.add(booking4);

        System.out.println("----- Testovací seznam rezervací - test 1 ------");
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

        // test 2: Zkus přidat dvě různé rezervace na jeden pokoj v různá data.
        guests.add(guest2);
        Booking booking5 = new Booking(room2, guests, LocalDate.of(2021, 2, 1), LocalDate.of(2021, 2, 15), Booking.VacationType.RECREATIONAL);
        Booking booking6 = new Booking(room2, guests, LocalDate.of(2021, 10, 16), LocalDate.of(2021, 10, 22), Booking.VacationType.RECREATIONAL);
        guests.clear();

        bookings.add(booking5);
        bookings.add(booking6);

        System.out.println("----- Testovací seznam rezervací - test 2 ------");
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

        // test 3: Je zajištěno, aby u každé rezervace byl registrovaný minimálně jeden host? Neměla by jít vytvořit rezervace bez hosta.
        /*Booking booking7 = new Booking(room2, null, LocalDate.of(2021, 10, 16), LocalDate.of(2021, 10, 22), false);
        padá na chybu:
        Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.util.Collection.toArray()" because "c" is null
            at java.base/java.util.ArrayList.addAll(ArrayList.java:752)
            at Booking.<init>(Booking.java:14)
            at Main.main(Main.java:100)
         */

        /*
        // test 4: vložení rekreacniho pobytu na 6 noci
        guests.add(guest1);
        guests.add(guest2);
        Booking booking7 = new Booking(room3, guests);
        guests.clear();

        bookings.add(booking7);

        System.out.println("----- Testovací seznam rezervací - test 2 ------");
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
         */
    }
}