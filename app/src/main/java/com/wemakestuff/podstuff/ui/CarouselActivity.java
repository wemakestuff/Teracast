

package com.wemakestuff.podstuff.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import butterknife.InjectView;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.viewpagerindicator.TitlePageIndicator;
import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.R.id;

/**
 * Activity to view the carousel and view pager indicator with fragments.
 */
public class CarouselActivity extends BootstrapFragmentActivity {

	@InjectView(id.tpi_header)
	TitlePageIndicator indicator;
	@InjectView(id.vp_pages)
	ViewPager          pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.carousel_view);

		pager.setAdapter(new BootstrapPagerAdapter(getResources(), getSupportFragmentManager()));

		indicator.setViewPager(pager);
		pager.setCurrentItem(1);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case id.timer:
				final Intent i = new Intent(this, BootstrapTimerActivity.class);
				startActivity(i);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
