package com.redhat.developers.embedding;

import com.redhat.developers.embedding.Customer;

import java.time.LocalDate;

public record Booking(String bookingNumber, LocalDate bookingFrom, LocalDate bookingTo, Customer customer) {
}