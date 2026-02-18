package view.components;

import model.valueobjects.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoNumbersInputView {
    private final Scanner scanner = new Scanner(System.in);
    public List<LottoNumber> render(int ballCount) {
        String rawNumbers = scanner.nextLine();
        if (!rawNumbers.matches("^[0-9][0-9, ]*$")) {
            throw new IllegalArgumentException("숫자와 ','만 입력 가능합니다.");
        }
        List<LottoNumber> result = new ArrayList<>();
        for (String rawNumber : rawNumbers.split(",")) {
            int number = Integer.parseInt(rawNumber.trim());
            result.add(new LottoNumber(number));
        }
        if (result.size() != ballCount) {
            throw new IllegalArgumentException("숫자 6개를 입력해주세요.");
        }
        return result;
    }
}
