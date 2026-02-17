package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.entities.Ticket;
import model.entities.TicketVoucher;
import model.services.TicketBooth;
import model.valueobjects.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketBoothTest {
  private int ticketValidateCode;
  private TicketBooth ticketBooth;
  private List<TicketVoucher> ticketVouchers;

  @BeforeEach
  void setUp() {
    ticketValidateCode = 990;
    ticketBooth = new TicketBooth(ticketValidateCode);
    ticketVouchers = ticketBooth.issueTicketVouchers(3000);
  }

  @Test
  void ticketBoothValidatorThrow() {
    TicketBooth ticketBooth = new TicketBooth(990);
    Integer[] prices = new Integer[] {1050, -123142341, 0};
    for (Integer price : prices) {
      assertThatThrownBy(() -> ticketBooth.issueTickets(price))
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Test
  void ticketBoothIssue() {
    TicketBooth ticketBooth = new TicketBooth(990);
    List<Ticket> tickets = ticketBooth.issueTickets(12000);
    assertThat(tickets.size()).isEqualTo(12);
  }

  @Test
  void ticketVoucher() {
    TicketBooth ticketBooth = new TicketBooth(990);
    List<TicketVoucher> ticketVouchers = ticketBooth.issueTicketVouchers(12000);
    assertThat(ticketVouchers.size()).isEqualTo(12);
  }

  @Test
  void manualTicket_VoucherCount_MatchFailed() {
    // Given
    List<List<LottoNumber>> userInputNumbers = List.of(
          List.of(1, 2, 3, 4, 5, 6).stream().map(LottoNumber::new).toList(),
          List.of(2, 3, 4, 5, 6, 7).stream().map(LottoNumber::new).toList()
    );
    TicketBooth sut = new TicketBooth(ticketValidateCode);
    // 여기서 개수 안 맞음을 오류로 검출한다!!
    assertThatThrownBy(() -> sut.issueManualTickets(ticketVouchers, userInputNumbers))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void manualTicketIssue() {
    List<List<LottoNumber>> userInputNumbers = List.of(
            List.of(1, 2, 3, 4, 5, 6).stream().map(LottoNumber::new).toList(),
            List.of(2, 3, 4, 5, 6, 7).stream().map(LottoNumber::new).toList(),
            List.of(3, 4, 5, 6, 7, 8).stream().map(LottoNumber::new).toList()
    );


    List<Ticket> sut = ticketBooth.issueManualTickets(ticketVouchers, userInputNumbers);

    // 티켓의 갯수가 모두 같아야한다.
    assertThat(sut.size()).isEqualTo(3);
    // 티켓이 모두 바우처를 가져야한다.
    for(Ticket ticket: sut) {
      assertThat(ticketVouchers.contains(ticket.getTicketVoucher())).isEqualTo(true);
    }
  }

  @Test
  void autoTicketIssue() {

    List<Ticket> sut = ticketBooth.issueAutoTickets(ticketVouchers);

    // 티켓의 갯수가 모두 같아야한다.
    assertThat(sut.size()).isEqualTo(ticketVouchers.size());
    // 티켓이 모든 바우처를 가져가야한다.
    for(Ticket ticket: sut) {
      assertThat(ticketVouchers.contains(ticket.getTicketVoucher())).isEqualTo(true);
    }
  }
}