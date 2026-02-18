package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.valueobjects.LottoNumber;
import view.components.LottoNumbersInputView;
import view.components.NumberInputView;

public class LotteryWinningNumbersView {
  private final Scanner scanner = new Scanner(System.in);
  private final NumberInputView numberInputView = new NumberInputView();
  private final LottoNumbersInputView lottoNumbersInputView = new LottoNumbersInputView();

  public void showInputWinNumberMessage() {
    System.out.println("지난 주 당첨 번호를 입력해 주세요.");
  }

  public List<LottoNumber> inputWinNumber(Integer ballCount) {
    return lottoNumbersInputView.render(ballCount);
  }

  public void showInputBonusBall() {
    System.out.println("보너스 볼을 입력해 주세요.");
  }

  public LottoNumber inputBonusBall() {
    return new LottoNumber(numberInputView.render());
  }

  public void showErrorMessage(IllegalArgumentException e) {
    System.out.println("[Error] " + e.getMessage());
  }
}
