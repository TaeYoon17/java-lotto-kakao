package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.entities.Ticket;
import model.valueobjects.LottoNumber;
import view.components.LottoNumbersInputView;
import view.components.NumberInputView;

public class TicketBoothView {
  private final Scanner scanner = new Scanner(System.in);
  private final NumberInputView numberInputView = new NumberInputView();
  private final LottoNumbersInputView lottoNumbersInputView = new LottoNumbersInputView();

  public void showInputPriceMessage() {
    System.out.println("구입금액을 입력해 주세요.");
  }
  public void showInputManualTicketCount() {
    System.out.println("수동으로 구매할 로또 수를 입력해주세요.");
  }
  public void showInputManualTicketNumbers() {
    System.out.println("수동으로 구매할 번호를 입력해 주세요.");
  }

  public void showTicketPurchaseResult(List<Ticket> manualTicket, List<Ticket> autoTicket) {
    System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n",manualTicket.size(), autoTicket.size());
    showTicketInfo(manualTicket);
    showTicketInfo(autoTicket);
  }

  public Integer inputManualTicketCount() {
    return numberInputView.render();
  }

  public List<List<LottoNumber>> inputManualTicketNumbers(int ticketCount, int ballCount) {
    List<List<LottoNumber>> result = new ArrayList<>();
    for (int i = 0; i < ticketCount; i++) {
      result.add(lottoNumbersInputView.render(ballCount));
    }
    return result;
  }

  public void showTicketInfo(List<Ticket> tickets) {
    for (Ticket ticket : tickets) {
      TicketView ticketView = new TicketView(ticket);
      ticketView.showTicketNumberInfo();
    }
  }

  public Integer inputTicketPrice() {
    return numberInputView.render();
  }

  public void showErrorMessage(IllegalArgumentException e) {
    System.out.println("[Error] " + e.getMessage());
  }
}
