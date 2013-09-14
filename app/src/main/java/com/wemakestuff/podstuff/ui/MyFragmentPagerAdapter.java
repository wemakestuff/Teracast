package com.wemakestuff.podstuff.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
	protected List<String> titleList;
	protected List<String> fragmentList;
	protected Context      context;

	public MyFragmentPagerAdapter(FragmentManager fm, Context context, List<String> titleList, List<String> fragmentList) {
		super(fm);
		this.context = context;
		this.fragmentList = fragmentList;
		this.titleList = titleList;
	}

	@Override
	public Fragment getItem(final int position) {
		return Fragment.instantiate(context, fragmentList.get(position));
	}

	@Override
	public int getCount() {
		return titleList.size();
	}

	@Override
	public CharSequence getPageTitle(final int position) {
		return titleList.get(position);
	}
}
