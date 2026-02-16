package view;

import model.WinLevel;

public class StatBoardView {
  public void showStatResult() {
    System.out.println("당첨 통계");
    System.out.println("---------");
  }

  public void showWinCountMessage(WinLevel winLevel, Integer count) {
    System.out.printf("%s (%d원) - %d개\n", winLevel.getDescription(), winLevel.getPrice(), count);
  }

  public void showProfitMessage(Double profitRatio) {
    System.out.printf("총 수익률은 %.2f입니다.\n", profitRatio);
  }
}
