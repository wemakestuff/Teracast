package com.wemakestuff.podstuff.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import butterknife.InjectView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.squareup.otto.Bus;
import com.viewpagerindicator.TitlePageIndicator;
import com.wemakestuff.podstuff.R;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PodcastsActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener {
	@Inject
	Bus mBus;
	MyFragmentPagerAdapter mAdapter;
	@InjectView(R.id.vp_pages)
	ViewPager          mPager;
	@InjectView(R.id.tpi_indicator)
	TitlePageIndicator mIndicator;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.podcasts);

		List<String> titleList = new ArrayList<String>();
		titleList.add(getString(R.string.all));
		titleList.add(getString(R.string.unlistened));
		titleList.add(getString(R.string.trending));

		List<String> fragmentList = new ArrayList<String>();
		fragmentList.add(AllPodcastsFragment.class.getName());
		fragmentList.add(AllPodcastsFragment.class.getName());
		fragmentList.add(AllPodcastsFragment.class.getName());

		mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this, titleList, fragmentList);
		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(2);

		mIndicator.setViewPager(mPager);
		mIndicator.setOnPageChangeListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.podcasts, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.register:
				Intent intent = new Intent(this, RegisterActivity.class);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void onPageSelected(final int position) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void onPageScrollStateChanged(final int state) {
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
