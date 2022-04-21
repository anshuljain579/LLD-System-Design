package com.home.ticketmaster.TicketMaster.controller;

import com.home.ticketmaster.TicketMaster.service.BookingService;
import com.home.ticketmaster.TicketMaster.service.PaymentsService;
import lombok.NonNull;

public class PaymentController {
    private final PaymentsService paymentsService;
    private final BookingService bookingService;

    public PaymentController(PaymentsService paymentsService, BookingService bookingService){
        this.bookingService = bookingService;
        this.paymentsService = paymentsService;
    }

    public void paymentFailed(@NonNull final String bookingId, @NonNull final String user){
        paymentsService.processPaymentFailed(bookingService.getBooking(bookingId), user);
    }

    public void paymentSuccess(@NonNull final String bookingId, @NonNull final String user){
        bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
    }
}
