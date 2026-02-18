package model.services;

import model.entities.Ticket;
import model.entities.TicketVoucher;
import model.valueobjects.LottoNumber;

import java.util.*;

public class TicketBooth {
  public static final int TICKET_PRICE = 1000;
  private final int TICKET_VALIDATE_CODE;

  private HashSet<TicketVoucher> issuedTicketVouchers;

  public TicketBooth(int ticketValidateCode) {
    this.issuedTicketVouchers = new HashSet<>();
    this.TICKET_VALIDATE_CODE = ticketValidateCode;
  }

  public List<TicketVoucher> issueTicketVouchers(int price) {
    issueTicketVouchersValidation(price);
    int ticketCount = price / TICKET_PRICE;
    List<TicketVoucher> result = new ArrayList<>();

    for(int i = 0; i < ticketCount; i++) {
      TicketVoucher ticketVoucher = new TicketVoucher(UUID.randomUUID(), TICKET_VALIDATE_CODE);
      result.add(ticketVoucher);
      issuedTicketVouchers.add(ticketVoucher);
    }
    return result;
  }


  public List<Ticket> issueManualTickets(List<TicketVoucher> ticketVouchers, List<List<LottoNumber>> userLottoNumbers) {
    if(ticketVouchers.size() != userLottoNumbers.size()) {
      throw new IllegalArgumentException("같은 개수의 인자가 아닙니다!");
    }
    List<Ticket> result = new ArrayList<>();

    for(int i = 0; i<ticketVouchers.size();i++) {
      TicketVoucher ticketVoucher = ticketVouchers.get(i);
      List<LottoNumber> userLottoNumber = userLottoNumbers.get(i);
      if(!issuedTicketVouchers.contains(ticketVoucher)) {
        throw new IllegalArgumentException("티켓 바우처에 오류가 있습니다!");
      }
      result.add(new Ticket(ticketVoucher,userLottoNumber));
      issuedTicketVouchers.remove(ticketVoucher);
    }
    return result;
  }

  public List<Ticket> issueAutoTickets(List<TicketVoucher> ticketVouchers) {
    List<Ticket> result = new ArrayList<>();
    for (TicketVoucher ticketVoucher : ticketVouchers) {
        if (!issuedTicketVouchers.contains(ticketVoucher)) {
            throw new IllegalArgumentException("티켓 바우처에 오류가 있습니다!");
        }
        result.add(issueTicket(ticketVoucher));
        issuedTicketVouchers.remove(ticketVoucher);
    }
    return result;
  }

  private Ticket issueTicket(TicketVoucher ticketVoucher) {
    List<LottoNumber> numbers = new ArrayList<>();
    for (int i = LottoNumber.MIN_LOTTO_NUMBER; i <= LottoNumber.MAX_LOTTO_NUMBER; i++) {
      numbers.add(new LottoNumber(i));
    }
    Collections.shuffle(numbers);
    List<LottoNumber> result = new ArrayList<>();
    for (int i = 0; i < Ticket.TICKET_NUMBER_COUNT; i++) {
      result.add(numbers.get(i));
    }
    return new Ticket(ticketVoucher, result);
  }

  private Ticket issueTicket() {
    List<LottoNumber> numbers = new ArrayList<>();
    for (int i = LottoNumber.MIN_LOTTO_NUMBER; i <= LottoNumber.MAX_LOTTO_NUMBER; i++) {
      numbers.add(new LottoNumber(i));
    }
    Collections.shuffle(numbers);
    List<LottoNumber> result = new ArrayList<>();
    for (int i = 0; i < Ticket.TICKET_NUMBER_COUNT; i++) {
      result.add(numbers.get(i));
    }
    throw new IllegalArgumentException("리팩토링 대상, 랜덤 티켓 발행과 수동 티켓 발행 메서드 구분하기");
  }


  private void issueTicketVouchersValidation(int price) {
    if (price < 0) {
      throw new IllegalArgumentException("빚내서 도박은 안돼!");
    }
    if (price == 0) {
      throw new IllegalArgumentException("공짜 좋아하면 대머리!");
    }
    if (price % TICKET_PRICE != 0) {
      throw new IllegalArgumentException(TICKET_PRICE + "원 단위의 입력이 아니다!");
    }
  }
}
