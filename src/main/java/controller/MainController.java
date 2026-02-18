package controller;

import java.util.*;

import model.entities.TicketVoucher;
import model.valueobjects.LotteryWinningNumbers;
import model.valueobjects.LottoNumber;
import model.services.StatBoard;
import model.entities.Ticket;
import model.services.TicketBooth;
import model.valueobjects.WinLevel;
import view.LotteryWinningNumbersView;
import view.StatBoardView;
import view.TicketBoothView;

public class MainController {
  public static final int BALL_COUNT = 6;
  private final int ticketValidateCode = 990;
  private final TicketBooth ticketBooth = new TicketBooth(ticketValidateCode);
  private final TicketBoothView ticketBoothView = new TicketBoothView();
  private final LotteryWinningNumbersView lotteryWinningNumbersView = new LotteryWinningNumbersView();
  private final StatBoardView statBoardView = new StatBoardView();

  Scanner scanner = new Scanner(System.in);
  public void render() {
    ticketBoothRender();
  }

  private void ticketBoothRender() {
    try {
      ticketBoothView.showInputPriceMessage();
      Integer price = ticketBoothView.inputTicketPrice();
      // 1. 구매 금액 입력 후 `issueTicketVouchers` 기반으로 발급 흐름 전환
      List<TicketVoucher> ticketVouchers = ticketBooth.issueTicketVouchers(price);
      // 2. 수동 구매 수량 입력 및 총 구매 수량 초과 검증
      ticketBoothView.showInputManualTicketCount();
      int totalVoucherCount = ticketVouchers.size();
      int manualTicketCount = ticketBoothView.inputManualTicketCount();

      if(totalVoucherCount < manualTicketCount) {
        throw new IllegalArgumentException("유저 티켓의 갯수가 더 넘칠 수 없습니다!!");
      }

      int startIndex = Math.max(0, totalVoucherCount - manualTicketCount);
      List<TicketVoucher> manualVouchers = new ArrayList<>(ticketVouchers.subList(startIndex, totalVoucherCount));
      List<TicketVoucher> autoVouchers = new ArrayList<>(ticketVouchers.subList(0, startIndex));
      // 3. 수동 수량만큼 번호 입력 루프 구성 후 `issueManualTickets` 호출
      ticketBoothView.showInputManualTicketNumbers();
      List<List<LottoNumber>> manualTicketNumbers = ticketBoothView.inputManualTicketNumbers(manualTicketCount, BALL_COUNT);

      // 4. 자동 수량(`총 수량 - 수동 수량`) 계산 후 `issueAutoTickets` 호출
      // 모델 핵심 API 통신
      List<Ticket> manualTickets = ticketBooth.issueManualTickets(manualVouchers, manualTicketNumbers);
      List<Ticket> autoTickets = ticketBooth.issueAutoTickets(autoVouchers);
      ticketBoothView.showTicketPurchaseResult(manualTickets, autoTickets);

      // 5. 수동/자동 티켓 병합 후 기존 통계 흐름 (gameScoreRenderer) 연결
      List<Ticket> result = new ArrayList<>(manualTickets);
      result.addAll(autoTickets);
      gameScoreRender(result);

    } catch (IllegalArgumentException e) {
      ticketBoothView.showErrorMessage(e);
      ticketBoothRender();
    }
  }

  private void gameScoreRender(List<Ticket> tickets) {
    try {
      lotteryWinningNumbersView.showInputWinNumberMessage();
      List<LottoNumber> winNumbers = lotteryWinningNumbersView.inputWinNumber(BALL_COUNT);
      lotteryWinningNumbersView.showInputBonusBall();
      LottoNumber bonusBall = lotteryWinningNumbersView.inputBonusBall();
      statBoardRender(new LotteryWinningNumbers(bonusBall, winNumbers), tickets);
    } catch (IllegalArgumentException e) {
      lotteryWinningNumbersView.showErrorMessage(e);
      gameScoreRender(tickets);
    }
  }

  private void statBoardRender(LotteryWinningNumbers score, List<Ticket> tickets) {
    StatBoard statBoard = new StatBoard(score, tickets);
    statBoardView.showStatResult();
    List<WinLevel> winLevels =
        List.of(WinLevel.FIFTH, WinLevel.FOURTH, WinLevel.THIRD, WinLevel.SECOND, WinLevel.FIRST);
    for (WinLevel winLevel : winLevels) {
      statBoardView.showWinCountMessage(winLevel, statBoard.getLevelCount(winLevel));
    }
    statBoardView.showProfitMessage(statBoard.getProfitRatio());
  }
}
