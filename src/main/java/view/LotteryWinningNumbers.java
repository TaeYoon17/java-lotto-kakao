package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.LottoNumber;

public class LotteryWinningNumbers {
	private final Scanner scanner = new Scanner(System.in);

	public void showInputWinNumberMessage() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
	}

	public List<LottoNumber> inputWinNumber(Integer ballCount) {
		String rawNumbers = scanner.nextLine();
			if (!rawNumbers.matches("^[0-9][0-9, ]*$")) {
				throw new IllegalArgumentException("숫자와 ','만 입력 가능합니다.");
			}
			List<LottoNumber> result = new ArrayList<>();
			for (String rawNumber : rawNumbers.split(",")) {
				int number = Integer.parseInt(rawNumber.trim());
				result.add(new LottoNumber(number));
			}
			if(result.size() != ballCount) {
				throw new IllegalArgumentException("숫자 6개를 입력해주세요.");
			}
			return result;

	}

	public void showInputBonusBall() {
		System.out.println("보너스 볼을 입력해 주세요.");
	}

	public LottoNumber inputBonusBall() {
			String rawNumber = scanner.nextLine();
			if (!rawNumber.matches("[0-9]*$")) {
				throw new IllegalArgumentException("숫자만 입력 가능합니다.");
			}
			int result =  Integer.parseInt(rawNumber);
			return new LottoNumber(result);
	}

	public void showErrorMessage(IllegalArgumentException e) {
		System.out.println("[Error] " + e.getMessage());
	}
}
