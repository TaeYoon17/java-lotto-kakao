package view.components;

import java.util.Scanner;

public class NumberInputView {
    private final Scanner scanner = new Scanner(System.in);
    public int render() {
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자만 입력해주세요.");
        }
    }
}
