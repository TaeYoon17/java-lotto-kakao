package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LotteryWinningNumbers {
  private final LottoNumber bonusNumber;
  private final List<LottoNumber> winNumbers;

  public LotteryWinningNumbers(LottoNumber bonusNumber, List<LottoNumber> winNumbers) {
    List<LottoNumber> sortedNumbers = new ArrayList<>(winNumbers);
    sortedNumbers.sort(null);
    this.bonusNumber = bonusNumber;
    this.winNumbers = sortedNumbers;
  }

  public LottoNumber getBonusNumber() {
    return bonusNumber;
  }

  public List<LottoNumber> getWinNumbers() {
    return winNumbers;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    LotteryWinningNumbers lotteryWinningNumbers = (LotteryWinningNumbers) o;
    return Objects.equals(bonusNumber, lotteryWinningNumbers.bonusNumber)
        && Objects.equals(winNumbers, lotteryWinningNumbers.winNumbers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bonusNumber, winNumbers);
  }
}
