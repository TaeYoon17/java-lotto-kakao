package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TicketTest {

	private LotteryWinningNumbers lotteryWinningNumbers;

	@BeforeEach
	void setUp() {
		lotteryWinningNumbers = new LotteryWinningNumbers(
			new LottoNumber(7),
			List.of(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(6)
			)
		);
	}

	@Test
	void ticketNumberCompared() {
		Ticket leftTicket = createTicket(1,2,3,4,5,6);
		Ticket rightTicket = createTicket(6,5,4,3,2,1);
		assertThat(leftTicket.getNumbers()).isEqualTo(rightTicket.getNumbers());
	}

	@DisplayName("5개 일치 + 보너스 일치면 2등")
	@Test
	void secondLevelByFiveMatchesAndBonus() {
		Ticket ticket = createTicket(1,2,3,4,5,7);
		assertThat(ticket.getWinLevel(lotteryWinningNumbers)).isEqualTo(WinLevel.SECOND);
	}

	@DisplayName("5개 일치 + 보너스 불일치면 3등")
	@Test
	void thirdLevelByFiveMatchesWithoutBonus() {
		Ticket ticket = createTicket(1,2,3,4,5,8);
		assertThat(ticket.getWinLevel(lotteryWinningNumbers)).isEqualTo(WinLevel.THIRD);
	}

	@DisplayName("각 매칭 개수에 따라 올바른 등수를 반환")
	@ParameterizedTest(name = "{index}: {0} -> {1}")
	@MethodSource("ticketsAndExpectedLevels")
	void ticketWinLevel(Ticket ticket, WinLevel expectedLevel) {
		assertThat(ticket.getWinLevel(lotteryWinningNumbers)).isEqualTo(expectedLevel);
	}

	private static Stream<Arguments> ticketsAndExpectedLevels() {
		return Stream.of(
			Arguments.of(createTicket(1,2,3,4,5,6), WinLevel.FIRST),
			Arguments.of(createTicket(1,2,3,4,9,8), WinLevel.FOURTH),
			Arguments.of(createTicket(1,2,3,10,9,8), WinLevel.FIFTH),
			Arguments.of(createTicket(8,9,10,11,12,13), WinLevel.LOSER)
		);
	}
	private static Ticket createTicket(int... numbers) {
		List<LottoNumber> lottoNumberList = new ArrayList<>();
		for(int number: numbers) {
			lottoNumberList.add(new LottoNumber(number));
		}
		return new Ticket(lottoNumberList);
	}
}
