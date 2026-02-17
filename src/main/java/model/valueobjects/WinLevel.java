package model.valueobjects;

import java.util.Arrays;
import java.util.List;

public enum WinLevel {
  FIRST(2_000_000_000L, "6개 일치"),
  SECOND(30_000_000L, "5개 일치, 보너스 볼 일치"),
  THIRD(1_500_000L, "5개 일치"),
  FOURTH(50_000L, "4개 일치"),
  FIFTH(5_000L, "3개 일치"),
  LOSER(0L, "당첨 되지 않았습니다.");

  private static final List<WinLevel> ALL_LEVELS =
      List.of(
          WinLevel.FIFTH,
          WinLevel.FOURTH,
          WinLevel.THIRD,
          WinLevel.SECOND,
          WinLevel.FIRST,
          WinLevel.LOSER);
  private final long price;
  private final String description;

  WinLevel(long price, String description) {
    this.price = price;
    this.description = description;
  }

  public Long getPrice() {
    return price;
  }

  public static WinLevel match(int winMatchCount, boolean bonusMatched) {
    if (winMatchCount == 6) return FIRST;
    if (winMatchCount == 5 && bonusMatched) return SECOND;
    if (winMatchCount == 5 && !bonusMatched) return THIRD;
    if (winMatchCount == 4) return FOURTH;
    if (winMatchCount == 3) return FIFTH;
    if (winMatchCount < 3) return LOSER;
    return LOSER;
  }

  public String getDescription() {
    return description;
  }

  public static List<WinLevel> getAll() {
    return Arrays.stream(values()).toList();
  }
}
