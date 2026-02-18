package controller.ticket_booth_controller;

import model.entities.Ticket;
import model.entities.TicketVoucher;
import model.services.TicketBooth;
import model.valueobjects.LottoNumber;
import model.valueobjects.TicketVoucherBundle;
import view.TicketBoothView;

import java.util.ArrayList;
import java.util.List;

public class TicketBoothController {
    private final TicketBooth ticketBooth;
    private final TicketBoothView ticketBoothView = new TicketBoothView();
    private final TicketBoothControllerDelegate delegate;


    public TicketBoothController(
            TicketBoothControllerDelegate delegate,
            TicketBooth ticketBooth
    ) {
        this.delegate = delegate;
        this.ticketBooth = ticketBooth;
    }

    public void render() {
        while (true) {
            try {
                delegate.ticketIssueSucceed(purchaseTickets());
                return;
            } catch (IllegalArgumentException e) {
                ticketBoothView.showErrorMessage(e);
            }
        }
    }

    private List<Ticket> purchaseTickets() {
        List<TicketVoucher> ticketVouchers = issueTicketVouchersByPriceInput();
        int manualTicketCount = inputManualTicketCount();
        TicketVoucherBundle voucherBundle = ticketBooth.splitTicketVouchers(ticketVouchers, manualTicketCount);

        List<Ticket> manualTickets = issueManualTickets(voucherBundle.getManualVouchers());
        List<Ticket> autoTickets = ticketBooth.issueAutoTickets(voucherBundle.getAutoVouchers());
        ticketBoothView.showTicketPurchaseResult(manualTickets, autoTickets);

        return mergeTickets(manualTickets, autoTickets);
    }

    private List<TicketVoucher> issueTicketVouchersByPriceInput() {
        ticketBoothView.showInputPriceMessage();
        int price = ticketBoothView.inputTicketPrice();
        return ticketBooth.issueTicketVouchers(price);
    }

    private int inputManualTicketCount() {
        ticketBoothView.showInputManualTicketCount();
        return ticketBoothView.inputManualTicketCount();
    }

    private List<Ticket> issueManualTickets(List<TicketVoucher> manualVouchers) {
        ticketBoothView.showInputManualTicketNumbers();
        List<List<LottoNumber>> manualTicketNumbers =
                ticketBoothView.inputManualTicketNumbers(manualVouchers.size(), Ticket.TICKET_NUMBER_COUNT);
        return ticketBooth.issueManualTickets(manualVouchers, manualTicketNumbers);
    }

    private List<Ticket> mergeTickets(List<Ticket> manualTickets, List<Ticket> autoTickets) {
        List<Ticket> result = new ArrayList<>(manualTickets);
        result.addAll(autoTickets);
        return result;
    }
}
