package controller;

import controller.game_score_controller.GameScoreController;
import controller.game_score_controller.GameScoreControllerDelegate;
import controller.stat_board_controller.StatBoardController;
import controller.ticket_booth_controller.TicketBoothController;
import controller.ticket_booth_controller.TicketBoothControllerDelegate;

import java.util.ArrayList;
import java.util.List;
import model.valueobjects.LotteryWinningNumbers;
import model.entities.Ticket;
import model.services.TicketBooth;

public class MainController implements TicketBoothControllerDelegate, GameScoreControllerDelegate {
  private static final int TICKET_VALIDATE_CODE = 990;
  private final TicketBoothController ticketBoothController;
  private final GameScoreController gameScoreController;
  private final StatBoardController statBoardController;

  private List<Ticket> userIssuedTickets = new ArrayList<>();
  public MainController() {
    ticketBoothController = new TicketBoothController(this, new TicketBooth(TICKET_VALIDATE_CODE));
    gameScoreController = new GameScoreController(this);
    statBoardController = new StatBoardController();
  }

  public void render() {
    ticketBoothController.render();
  }

  @Override
  public void ticketIssueSucceed(List<Ticket> result) {
    userIssuedTickets = result;
    gameScoreController.render(result);
  }

  @Override
  public void winningNumbersInputSucceed(
      LotteryWinningNumbers lotteryWinningNumbers) {
    statBoardController.render(lotteryWinningNumbers, userIssuedTickets);
  }
}
