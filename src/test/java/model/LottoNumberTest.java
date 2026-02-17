package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.valueobjects.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

  @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외를 발생시킨다.")
  @ParameterizedTest(name = "입력값: {0}")
  @ValueSource(ints = {0, -1, 46})
  void createLottoNumber_withInvalidNumber_shouldThrowException(int invalidNumber) {
    assertThatThrownBy(() -> new LottoNumber(invalidNumber))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @DisplayName("1~45 범위의 숫자로 로또 번호를 생성할 수 있다.")
  @ParameterizedTest(name = "입력값: {0}")
  @ValueSource(ints = {1, 45, 23})
  void createLottoNumber_withValidNumber_shouldNotThrowException(int validNumber) {
    assertThatCode(() -> new LottoNumber(validNumber)).doesNotThrowAnyException();
  }

  @DisplayName("두 LottoNumber 객체의 값이 같은지 비교한다.")
  @ParameterizedTest(name = "left: {0}, right: {1} -> expected: {2}")
  @CsvSource({"1, 1, true", "1, 2, false", "45, 45, true", "45, 1, false"})
  void equals_shouldReturnCorrectResult(int leftValue, int rightValue, boolean expected) {
    LottoNumber left = new LottoNumber(leftValue);
    LottoNumber right = new LottoNumber(rightValue);
    assertThat(left.equals(right)).isEqualTo(expected);
  }
}
