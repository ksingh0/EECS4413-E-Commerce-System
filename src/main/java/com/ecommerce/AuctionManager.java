package com.ecommerce;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 *  Schedule task to run every hour, to check for any auctions that are ending on that hour.
 */
public class AuctionManager implements ServletContextListener {
	 private static ScheduledExecutorService scheduledExecutor;
	 
	 @Override
	 public void contextInitialized(ServletContextEvent event) {
		 scheduleCheck();
		 System.out.println("context initialized");
	  }

	  @Override
	  public void contextDestroyed(ServletContextEvent event) { 
	      if (scheduledExecutor != null) {
	    	  scheduledExecutor.shutdown();
	          try {
	                if (!scheduledExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
	                	scheduledExecutor.shutdownNow();
	                }
	            } catch (InterruptedException e) {
	            	scheduledExecutor.shutdownNow();
	                Thread.currentThread().interrupt();
	          }
	     }
	}

	  
	public static void scheduleCheck() {
		ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutor.scheduleAtFixedRate(new AuctionCheckerTask(), msToNextMin(), 60*1000, TimeUnit.MILLISECONDS);
		System.out.println("executor has been scheduled");
	}

	public static long msToNextHour() {
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime nextHour = currentTime.plusHours(1).truncatedTo(ChronoUnit.HOURS);
		return currentTime.until(nextHour, ChronoUnit.MILLIS);
	}
	
	// get ms to the next minute
	public static long msToNextMin() {
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime nextMin = currentTime.plusMinutes(1).truncatedTo(ChronoUnit.MINUTES);
		return currentTime.until(nextMin, ChronoUnit.MILLIS);
	}
	
	
}

