package com.wemakestuff.teracast.util;

import java.util.List;

public class ListUtils {
    public static Object getItem(List<?> list, int position) {
        if (!isOutOfBounds(list, position))
            return list.get(position);
        return null;
    }

    public static int getCount(List<?> list) {
        if (list == null)
            return 0;
        return list.size();
    }

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
