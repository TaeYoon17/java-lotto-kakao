package model.entities;

import model.valueobjects.LotteryWinningNumbers;
import model.valueobjects.LottoNumber;
import model.valueobjects.WinLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ticket {

  public static final int TICKET_NUMBER_COUNT = 6;

  private final List<LottoNumber> numbers;

  private final TicketVoucher ticketVoucher;

  public Ticket(TicketVoucher ticketVoucher, LottoNumber... numbers) {
    this(ticketVoucher, new ArrayList<>(Arrays.asList(numbers)));
  }

  public Ticket(TicketVoucher ticketVoucher,List<LottoNumber> numbers) {
    this.ticketVoucher = ticketVoucher;
    this.numbers = new ArrayList<>(numbers).stream().sorted().toList();
  }

  public WinLevel getWinLevel(LotteryWinningNumbers lotteryWinningNumbers) {
    long key = 0L;
    for (LottoNumber number : numbers) {
      key |= (1L << number.getNumber());
    }
    int winMatchcount = 0;
    for (var winNumber : lotteryWinningNumbers.getWinNumbers()) {
      winMatchcount += (key & (1L << winNumber.getNumber())) == 0 ? 0 : 1;
    }
    boolean bonusMatched = (key & (1L << lotteryWinningNumbers.getBonusNumber().getNumber())) > 0;
    return WinLevel.match(winMatchcount, bonusMatched);
  }

  public List<LottoNumber> getNumbers() {
    return numbers;
  }
  public TicketVoucher getTicketVoucher() {
    return ticketVoucher;
  }
}
