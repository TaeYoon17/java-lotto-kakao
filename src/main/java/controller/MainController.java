package controller;

import java.util.List;

import model.LotteryWinningNumbers;
import model.LottoNumber;
import model.StatBoard;
import model.Ticket;
import model.TicketBooth;
import model.WinLevel;
import view.StatBoardView;
import view.TicketBoothView;

public class MainController {
	public static final int BALL_COUNT = 6;
	private final TicketBooth ticketBooth = new TicketBooth();
	private final TicketBoothView ticketBoothView = new TicketBoothView();
	private final view.LotteryWinningNumbers lotteryWinningNumbers = new view.LotteryWinningNumbers();
	private final StatBoardView statBoardView = new StatBoardView();

	public void render() {
			ticketBoothRender();
	}

	private void ticketBoothRender() {
		try {
			ticketBoothView.showInputPriceMessage();
			Integer price = ticketBoothView.inputTicketPrice();
			List<Ticket> tickets = ticketBooth.issueTickets(price);
			ticketBoothView.showTicketInfo(tickets);
			gameScoreRender(tickets);
		} catch (IllegalArgumentException e) {
			ticketBoothView.showErrorMessage(e);
			ticketBoothRender();
		}
	}

	private void gameScoreRender(List<Ticket> tickets) {
		try {
			lotteryWinningNumbers.showInputWinNumberMessage();
			List<LottoNumber> winNumbers = lotteryWinningNumbers.inputWinNumber(BALL_COUNT);
			lotteryWinningNumbers.showInputBonusBall();
			LottoNumber bonusBall = lotteryWinningNumbers.inputBonusBall();
			statBoardRender(new LotteryWinningNumbers(bonusBall, winNumbers), tickets);
		} catch (IllegalArgumentException e) {
			lotteryWinningNumbers.showErrorMessage(e);
			gameScoreRender(tickets);
		}
	}

	private void statBoardRender(LotteryWinningNumbers score, List<Ticket> tickets) {
		StatBoard statBoard = new StatBoard(score, tickets);
		statBoardView.showStatResult();
		List<WinLevel> winLevels = List.of(WinLevel.FIFTH, WinLevel.FOURTH, WinLevel.THIRD, WinLevel.SECOND, WinLevel.FIRST);
		for(WinLevel winLevel: winLevels) {
			statBoardView.showWinCountMessage(winLevel, statBoard.getLevelCount(winLevel));
		}
		statBoardView.showProfitMessage(statBoard.getProfitRatio());
	}
}
