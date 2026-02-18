package controller.game_score_controller;

import java.util.List;
import model.entities.Ticket;
import model.valueobjects.LotteryWinningNumbers;
import model.valueobjects.LottoNumber;
import view.LotteryWinningNumbersView;

public class GameScoreController {
  private final LotteryWinningNumbersView lotteryWinningNumbersView = new LotteryWinningNumbersView();
  private final GameScoreControllerDelegate delegate;

  public GameScoreController(GameScoreControllerDelegate delegate) {
    this.delegate = delegate;
  }

  public void render(List<Ticket> tickets) {
    while (true) {
      try {
        delegate.winningNumbersInputSucceed(inputLotteryWinningNumbers());
        return;
      } catch (IllegalArgumentException e) {
        lotteryWinningNumbersView.showErrorMessage(e);
      }
    }
  }

  private LotteryWinningNumbers inputLotteryWinningNumbers() {
    lotteryWinningNumbersView.showInputWinNumberMessage();
    List<LottoNumber> winNumbers = lotteryWinningNumbersView.inputWinNumber(Ticket.TICKET_NUMBER_COUNT);
    lotteryWinningNumbersView.showInputBonusBall();
    LottoNumber bonusBall = lotteryWinningNumbersView.inputBonusBall();
    return new LotteryWinningNumbers(bonusBall, winNumbers);
  }
}
