package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatBoard {

	private final Map<WinLevel, Integer> matchedWinLevelCount;
	private final Integer ticketCount;

	public StatBoard(LotteryWinningNumbers lotteryWinningNumbers, List<Ticket> tickets) {
		ticketCount = tickets.size();
		matchedWinLevelCount = new HashMap<>();

		for(WinLevel winLevel: WinLevel.getAll()) {
			matchedWinLevelCount.put(winLevel, 0);
		}

		for(Ticket ticket: tickets) {
			WinLevel level = ticket.getWinLevel(lotteryWinningNumbers);
			matchedWinLevelCount.put(level, matchedWinLevelCount.getOrDefault(level, 0) + 1);
		}
	}

	public Integer getLevelCount(WinLevel winLevel) {
		return matchedWinLevelCount.get(winLevel);
	}

	public Double getProfitRatio() {
		long ticketRevenue = 0L;
		long cost = 1000L * ticketCount;
		for(var zip: matchedWinLevelCount.entrySet())
			ticketRevenue += zip.getKey().getPrice() * zip.getValue();
		return (double)ticketRevenue / cost;
	}
}