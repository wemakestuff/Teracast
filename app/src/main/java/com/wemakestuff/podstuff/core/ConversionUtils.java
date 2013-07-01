package com.wemakestuff.podstuff.core;

import java.util.concurrent.TimeUnit;

public class ConversionUtils {

	public static String ConvertMillisecondsToMinutesSeconds(int milliseconds) {
		return String.format("%d:%02d", TimeUnit.MILLISECONDS.toMinutes(milliseconds), TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
		                                                                               TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
	}
}
