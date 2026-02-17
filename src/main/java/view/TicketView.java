package view;

import model.valueobjects.LottoNumber;
import model.entities.Ticket;

public class TicketView {
  private final Ticket ticket;

  public TicketView(Ticket ticket) {
    this.ticket = ticket;
  }

  void showTicketNumberInfo() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    for (LottoNumber number : ticket.getNumbers()) {
      stringBuilder.append(number.getNumber()).append(", ");
    }
    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    stringBuilder.append("]");
    System.out.println(stringBuilder.toString());
  }
}
