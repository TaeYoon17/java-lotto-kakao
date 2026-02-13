package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketBooth {

	public static final int TICKET_PRICE = 1000;

	private Ticket issueTicket() {
		List<LottoNumber> numbers = new ArrayList<>();
		for(int i = LottoNumber.MIN_LOTTO_NUMBER; i <= LottoNumber.MAX_LOTTO_NUMBER; i++) {
			numbers.add(new LottoNumber(i));
		}
		Collections.shuffle(numbers);
		List<LottoNumber> result = new ArrayList<>();
		for(int i = 0; i < Ticket.TICKET_NUMBER_COUNT; i++) {
			result.add(numbers.get(i));
		}
		return new Ticket(result);
	}

	public List<Ticket> issueTickets(int price) {
		issueTicketsValidation(price);
		int ticketCount = price / TICKET_PRICE;
		List<Ticket> result = new ArrayList<>();
		while(result.size() < ticketCount) {
			Ticket ticket = issueTicket();
			result.add(ticket);
		}
		return result;
	}

	private void issueTicketsValidation(int price) {
		if(price < 0) {
			throw new IllegalArgumentException("빚내서 도박은 안돼!");
		}
		if(price == 0) {
			throw new IllegalArgumentException("공짜 좋아하면 대머리!");
		}
		if(price % TICKET_PRICE != 0) {
			throw new IllegalArgumentException(TICKET_PRICE + "원 단위의 입력이 아니다!");
		}
	}
}
