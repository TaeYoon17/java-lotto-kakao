package controller.game_score_controller;

import java.util.List;
import model.entities.Ticket;
import model.valueobjects.LotteryWinningNumbers;

public interface GameScoreControllerDelegate {
  void winningNumbersInputSucceed(LotteryWinningNumbers lotteryWinningNumbers);
}
