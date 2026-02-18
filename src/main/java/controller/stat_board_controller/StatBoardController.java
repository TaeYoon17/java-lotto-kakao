package controller.stat_board_controller;

import java.util.List;
import model.entities.Ticket;
import model.services.StatBoard;
import model.valueobjects.LotteryWinningNumbers;
import model.valueobjects.WinLevel;
import view.StatBoardView;

public class StatBoardController {
  private static final List<WinLevel> STAT_WIN_LEVELS =
      List.of(WinLevel.FIFTH, WinLevel.FOURTH, WinLevel.THIRD, WinLevel.SECOND, WinLevel.FIRST);
  private final StatBoardView statBoardView = new StatBoardView();

  public void render(LotteryWinningNumbers score, List<Ticket> tickets) {
    StatBoard statBoard = createStatBoard(score, tickets);
    showStatBoard(statBoard);
  }

  private StatBoard createStatBoard(LotteryWinningNumbers score, List<Ticket> tickets) {
    return new StatBoard(score, tickets);
  }

  private void showStatBoard(StatBoard statBoard) {
    statBoardView.showStatResult();
    for (WinLevel winLevel : STAT_WIN_LEVELS) {
      statBoardView.showWinCountMessage(winLevel, statBoard.getLevelCount(winLevel));
    }
    statBoardView.showProfitMessage(statBoard.getProfitRatio());
  }
}
