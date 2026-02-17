package model.valueobjects;

import model.entities.Ticket;

import java.util.List;
import java.util.Objects;

class TicketIssueResult {
    List<Ticket> manualTickets;
    List<Ticket> autoTickets;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketIssueResult that = (TicketIssueResult) o;
        return Objects.equals(manualTickets, that.manualTickets) && Objects.equals(autoTickets, that.autoTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manualTickets, autoTickets);
    }
}