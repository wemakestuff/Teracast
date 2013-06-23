package com.wemakestuff.podstuff.ui;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.core.News;

public class PlayerActivity extends BootstrapActivity {
	@InjectView(R.id.tv_title)
	protected TextView title;
	@InjectView(R.id.tv_content)
	protected TextView content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.news);

		if (getIntent() != null && getIntent().getExtras() != null) {
			newsItem = (News) getIntent().getExtras().getSerializable(NEWS_ITEM);
		}

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		setTitle(newsItem.getTitle());

		title.setText(newsItem.getTitle());
		content.setText(newsItem.getContent());

	}
}
