package com.chengqianyun.eeweb2network.common.util;

public class SystemClock {

	public static void sleepRandom(long min, long max) {
		sleep(RandomUtil.random(min, max));
	}

	public static long sleepRandomAndRetrun(long min, long max) {
		long sleepTime = RandomUtil.random(min, max);
		sleep(sleepTime);
		return sleepTime;
	}

	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			sleepRandom(5000, 15000);
		}
	}
}