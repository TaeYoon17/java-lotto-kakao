package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ticket {

	public static final int TICKET_NUMBER_COUNT = 6;

	private final List<LottoNumber> numbers;

	public Ticket(LottoNumber... numbers) {
		this(new ArrayList<>(Arrays.asList(numbers)));
	}

	public Ticket(List<LottoNumber> numbers) {
		List<LottoNumber> sortedNumbers = new ArrayList<>(numbers);
		sortedNumbers.sort(null);
		this.numbers = sortedNumbers;
	}
	
	public WinLevel getWinLevel(LotteryWinningNumbers lotteryWinningNumbers) {
		long key = 0L;
		for(LottoNumber number: numbers) {
			key |= (1L << number.getNumber());
		}
		int winMatchcount = 0;
		for(var winNumber: lotteryWinningNumbers.getWinNumbers()) {
			winMatchcount += (key & (1L <<winNumber.getNumber())) == 0 ? 0 : 1;
		}
		boolean bonusMatched = (key & (1L << lotteryWinningNumbers.getBonusNumber().getNumber())) > 0;
		return WinLevel.make(winMatchcount, bonusMatched);
	}

	public List<LottoNumber> getNumbers() {
		return numbers;
	}

	private static List<LottoNumber> toLottoNumbers(int... nums) {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		for (int num : nums) {
			lottoNumbers.add(new LottoNumber(num));
		}
		return lottoNumbers;
	}


}
