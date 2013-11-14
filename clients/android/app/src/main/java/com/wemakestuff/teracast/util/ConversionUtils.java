package com.wemakestuff.teracast.util;

import java.util.concurrent.TimeUnit;

public class ConversionUtils {

	public static String formatMilliseconds(int milliseconds) {
		return formatMilliseconds((long) milliseconds);
	}

	public static String formatMilliseconds(long milliseconds) {
		long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

		if (hours > 0) {
			return String.format("%d:%02d:%02d", hours, minutes, seconds);
		}
		return String.format("%d:%02d", minutes, seconds);
	}
}
