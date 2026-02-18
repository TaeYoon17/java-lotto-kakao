package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.entities.Ticket;
import model.valueobjects.LottoNumber;

public class TicketBoothView {
  private final Scanner scanner = new Scanner(System.in);

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
    String input = scanner.nextLine();
    try {
      return Integer.parseInt(input);
    } catch (Exception e) {
      throw new IllegalArgumentException("숫자만 입력해주세요.");
    }
  }

  public List<List<LottoNumber>> inputManualTicketNumbers(int ticketCount, int ballCount) {
    List<List<LottoNumber>> result = new ArrayList<>();
    for (int i = 0; i < ticketCount; i++) {
      result.add(inputLottoNumber(ballCount));
    }
    return result;
  }

  public List<LottoNumber> inputLottoNumber(Integer ballCount) {
    String rawNumbers = scanner.nextLine();
    if (!rawNumbers.matches("^[0-9][0-9, ]*$")) {
      throw new IllegalArgumentException("숫자와 ','만 입력 가능합니다.");
    }
    List<LottoNumber> result = new ArrayList<>();
    for (String rawNumber : rawNumbers.split(",")) {
      int number = Integer.parseInt(rawNumber.trim());
      result.add(new LottoNumber(number));
    }
    if (result.size() != ballCount) {
      throw new IllegalArgumentException("숫자 6개를 입력해주세요.");
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
    String input = scanner.nextLine();
    try {
      return Integer.parseInt(input);
    } catch (Exception e) {
      throw new IllegalArgumentException("숫자만 입력해주세요.");
    }
  }

  public void showErrorMessage(IllegalArgumentException e) {
    System.out.println("[Error] " + e.getMessage());
  }
}
