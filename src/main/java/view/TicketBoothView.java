package view;

import java.util.List;
import java.util.Scanner;
import model.entities.Ticket;

public class TicketBoothView {
  private final Scanner scanner = new Scanner(System.in);

  public void showInputPriceMessage() {
    System.out.println("구입금액을 입력해 주세요.");
  }

  public void showTicketInfo(List<Ticket> tickets) {
    System.out.println(tickets.size() + "개를 구매했습니다.");
    for (Ticket ticket : tickets) {
      TicketView ticketView = new TicketView(ticket);
      ticketView.showTicketNumberInfo();
    }
  }

  public Integer inputTicketPrice() {
    String input = scanner.nextLine();
    try {
      return Integer.parseInt(input);
    } catch (Exception e) {
      throw new IllegalArgumentException("잘 못 된 입력");
    }
  }

  public void showErrorMessage(IllegalArgumentException e) {
    System.out.println("[Error] " + e.getMessage());
  }
}
