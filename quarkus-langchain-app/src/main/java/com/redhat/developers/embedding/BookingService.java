package com.redhat.developers.embedding;

import java.time.LocalDate;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookingService {

    public Booking getBookingDetails(String bookingNumber, String customerName, String customerSurname) {
        ensureExists(bookingNumber, customerName, customerSurname);

        // Retrieval from DB mocking
        LocalDate bookingFrom = LocalDate.now().plusDays(1);
        LocalDate bookingTo = LocalDate.now().plusDays(3);
        Customer customer = new Customer(customerName, customerSurname);
        return new Booking(bookingNumber, bookingFrom, bookingTo, customer);
    }

    public void cancelBooking(String bookingNumber, String customerName, String customerSurname) {
        ensureExists(bookingNumber, customerName, customerSurname);

        // Cancellation mocking
        throw new BookingCannotBeCancelledException(bookingNumber);
    }

    private void ensureExists(String bookingNumber, String customerName, String customerSurname) {
        // Check mocking
        if (!(bookingNumber.equals("123-456")
                && customerName.equals("John")
                && customerSurname.equals("Doe"))) {
            throw new BookingNotFoundException(bookingNumber);
        }
    }
}