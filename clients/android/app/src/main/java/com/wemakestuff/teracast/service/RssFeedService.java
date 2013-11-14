package com.wemakestuff.teracast.service;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.squareup.otto.Bus;
import com.wemakestuff.teracast.rss.RssFeedParser;
import com.wemakestuff.teracast.rss.event.ProvideRssFeedEvent;
import com.wemakestuff.teracast.rss.model.RssFeed;

import javax.inject.Inject;
import java.sql.SQLException;

public class RssFeedService extends HttpService {

	private static final String TAG = RssFeedService.class.getSimpleName();
	@Inject
	Bus bus;

	@Override
	public void onCreate() {
		super.onCreate();
		bus.register(this);
	}

	@Override
	public void onDestroy() {
		bus.unregister(this);
		super.onDestroy();
	}

	@Override
	public void onRequestComplete(final Intent result) {
		super.onRequestComplete(result);

		Bundle args = result.getBundleExtra(EXTRA_BUNDLE);
		RequestCode mRequestCode = (RequestCode) args.getSerializable(EXTRA_REQUEST_CODE);
		int mHttpStatusCode = args.getInt(EXTRA_STATUS_CODE);
		String mJson = args.getString(REST_RESULT);

		Log.i(TAG, "Request complete!");
		if (mRequestCode != null)
			Log.i(TAG, "RequestCode = " + mRequestCode.name());

		Log.i(TAG, "HTTP Status Code = " + mHttpStatusCode);
		Log.i(TAG, "JSON/XML = " + mJson);

		RssFeedParser parser = new RssFeedParser(mJson);
		RssFeed feed = parser.parse();
		try {
			feed.insertIntoDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		produceProvideRssFeedEvent(feed);
	}

	protected void produceProvideRssFeedEvent(RssFeed rssFeed) {
		bus.post(new ProvideRssFeedEvent(rssFeed));
	}

	public static void getRssFeed(Context context, Uri uri) {
		Intent intent = new Intent(context, RssFeedService.class);
		intent.putExtra(EXTRA_USER_AGENT, USER_AGENT);
		intent.setData(uri);

		context.startService(intent);
	}
}
