package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class StatBoardTest {
  LotteryWinningNumbers lotteryWinningNumbers =
      new LotteryWinningNumbers(n(7), createLottoNumbers(1, 2, 3, 4, 5, 6));

  @Test
  void profitRatioMaker() {
    StatBoard statBoard =
        new StatBoard(
            lotteryWinningNumbers,
            Arrays.asList(
                new Ticket(createLottoNumbers(1, 2, 3, 4, 5, 6)),
                new Ticket(createLottoNumbers(1, 2, 3, 4, 5, 7)),
                new Ticket(createLottoNumbers(1, 2, 3, 4, 5, 8)),
                new Ticket(createLottoNumbers(1, 2, 3, 4, 9, 8)),
                new Ticket(createLottoNumbers(1, 2, 3, 10, 9, 8)),
                new Ticket(createLottoNumbers(7, 8, 9, 10, 11, 12))));
    Double profitRatio = statBoard.getProfitRatio();
    assertThat(profitRatio).isEqualTo(338592.5);
  }

  private static List<LottoNumber> createLottoNumbers(int... numbers) {
    List<LottoNumber> lottoNumbers = new ArrayList<>();
    for (int number : numbers) {
      lottoNumbers.add(n(number));
    }
    return lottoNumbers;
  }

  private static LottoNumber n(int number) {
    return new LottoNumber(number);
  }
}
