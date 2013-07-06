package com.wemakestuff.podstuff.util;

import java.util.List;

public class ListUtils {
	public static boolean isEmpty(List<?> list) {
		if (list == null)
			return true;
		else
			return list.isEmpty();
	}

	public static boolean isOutOfBounds(List<?> list, int position) {
		if (isEmpty(list))
			return true;
		else if (list.size() <= position)
			return true;
		else
			return false;
	}
}
