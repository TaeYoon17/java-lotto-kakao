package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class TicketBoothTest {
  @Test
  void ticketBoothValidatorThrow() {
    TicketBooth ticketBooth = new TicketBooth();
    Integer[] prices = new Integer[] {1050, -123142341, 0};
    for (Integer price : prices) {
      assertThatThrownBy(() -> ticketBooth.issueTickets(price))
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Test
  void ticketBoothIssue() {
    TicketBooth ticketBooth = new TicketBooth();
    List<Ticket> tickets = ticketBooth.issueTickets(12000);
    assertThat(tickets.size()).isEqualTo(12);
  }
}
