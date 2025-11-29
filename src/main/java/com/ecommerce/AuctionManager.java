package com.ecommerce;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 *  Schedule task to run every hour, to check for any auctions that are ending on that hour.
 */
public class AuctionManager {
	
	public static void main(String[] args) {
		scheduleCheck();
	}
	
	public static void scheduleCheck() {
		ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutor.scheduleAtFixedRate(new AuctionCheckerTask(), msToNextHour(), 60*60*1000, TimeUnit.MILLISECONDS);		
	}

	public static long msToNextHour() {
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime nextHour = currentTime.plusHours(1).truncatedTo(ChronoUnit.HOURS);
		return currentTime.until(nextHour, ChronoUnit.MILLIS);
	}
	
}

