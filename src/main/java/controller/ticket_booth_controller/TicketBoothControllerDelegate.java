package controller.ticket_booth_controller;

import model.entities.Ticket;

import java.util.List;

public interface TicketBoothControllerDelegate {
    void ticketIssueSucceed(List<Ticket> result);
}
