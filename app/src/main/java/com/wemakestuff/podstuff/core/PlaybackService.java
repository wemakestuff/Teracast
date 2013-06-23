package com.wemakestuff.podstuff.core;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import com.squareup.otto.Bus;
import com.wemakestuff.podstuff.BootstrapApplication;
import com.wemakestuff.podstuff.service.AudioFocusHelper;
import com.wemakestuff.podstuff.service.MusicFocusable;
import com.wemakestuff.podstuff.service.RemoteControlClientCompat;
import com.wemakestuff.podstuff.util.Ln;

import javax.inject.Inject;

import static com.wemakestuff.podstuff.core.Constants.Notification.PLAYBACK_NOTIFICATION_ID;

public class PlaybackService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener,
                                                        MediaPlayer.OnErrorListener, MusicFocusable {
	// These are the Intent actions that we are prepared to handle. Notice that the fact these
	// constants exist in our class is a mere convenience: what really defines the actions our
	// service can handle are the <action> tags in the <intent-filters> tag for our service in
	// AndroidManifest.xml.
	public static final String ACTION_PREVIOUS           = "com.wemakestuff.podstuff.action.PREVIOUS";
	public static final String ACTION_REWIND             = "com.wemakestuff.podstuff.action.REWIND";
	public static final String ACTION_PLAY               = "com.wemakestuff.podstuff.action.PLAY";
	public static final String ACTION_TOGGLE_PLAYBACK    = "com.wemakestuff.podstuff.action.TOGGLE_PLAYBACK";
	public static final String ACTION_PAUSE              = "com.wemakestuff.podstuff.action.PAUSE";
	public static final String ACTION_STOP               = "com.wemakestuff.podstuff.action.STOP";
	public static final String ACTION_NEXT               = "com.wemakestuff.podstuff.action.NEXT";
	public static final String ACTION_STREAM             = "com.wemakestuff.podstuff.action.STREAM";
	public static final String ACTION_DOWNLOAD           = "com.wemakestuff.podstuff.action.DOWNLOAD";
	public static final String ACTION_HEADSET_PLUGGED_IN = "com.wemakestuff.podstuff.action.HEADSET_PLUGGED_IN";
	public static final String ACTION_HEADSET_UNPLUGGED  = "com.wemakestuff.podstuff.action.HEADSET_UNPLUGGED";
	@Inject
	protected Bus BUS;
	@Inject
	NotificationManager notificationManager;
	// Wifi lock that we hold when streaming files from the internet, in order to prevent the
	// device from shutting off the Wifi radio
	WifiManager.WifiLock mWifiLock;
	@Inject
	AudioManager              mAudioManager;
	// our RemoteControlClient object, which will use remote control APIs available in
	// SDK level >= 14, if they're available.
	@Inject
	RemoteControlClientCompat mRemoteControlClientCompat;
	// why did we pause? (only relevant if mState == State.Paused)
	PauseReason mPauseReason = PauseReason.UserRequest;
	AudioFocus  mAudioFocus  = AudioFocus.NoFocusNoDuck;
	// our AudioFocusHelper object, if it's available (it's available on SDK level >= 8)
	// If not available, this will be null. Always check for null before using!
	AudioFocusHelper mAudioFocusHelper = null;

	@Override
	public void onCreate() {
		super.onCreate();

		BootstrapApplication.getInstance().inject(this);

		// Register the bus so we can send notifications.
		BUS.register(this);

		// Create the Wifi lock (this does not acquire the lock, this just creates it)
		mWifiLock = ((WifiManager) getSystemService(Context.WIFI_SERVICE))
				            .createWifiLock(WifiManager.WIFI_MODE_FULL, "mylock");

		// create the Audio Focus Helper, if the Audio Focus feature is available (SDK 8 or above)
		if (android.os.Build.VERSION.SDK_INT >= 8) {
			mAudioFocusHelper = new AudioFocusHelper(getApplicationContext(), this);
		} else {
			mAudioFocus = AudioFocus.Focused; // no focus feature, so we always "have" audio focus
		}

	}

	@Override
	public void onDestroy() {

		// Unregister bus, since its not longer needed as the service is shutting down
		BUS.unregister(this);

		notificationManager.cancel(PLAYBACK_NOTIFICATION_ID);

		Ln.d("Service has been destroyed");

		super.onDestroy();
	}

	@Override
	public void onGainedAudioFocus() {

	}

	@Override
	public void onLostAudioFocus(final boolean canDuck) {

	}

	@Override
	public void onCompletion(final MediaPlayer mp) {

	}

	@Override
	public boolean onError(final MediaPlayer mp, final int what, final int extra) {
		return false;
	}

	@Override
	public void onPrepared(final MediaPlayer mp) {

	}

	@Override
	public IBinder onBind(final Intent intent) {
		return null;
	}

	// indicates the state our service:
	enum State {
		Retrieving, // the MediaRetriever is retrieving music
		Stopped,    // media player is stopped and not prepared to play
		Preparing,  // media player is preparing...
		Playing,    // playback active (media player ready!). (but the media player may actually be
		// paused in this state if we don't have audio focus. But we stay in this state
		// so that we know we have to resume playback once we get focus back)
		Paused      // playback paused (media player ready!)
	}

	enum PauseReason {
		UserRequest,  // paused by user request
		FocusLoss,    // paused because of audio focus loss
	}

	// do we have audio focus?
	enum AudioFocus {
		NoFocusNoDuck,    // we don't have audio focus, and can't duck
		NoFocusCanDuck,   // we don't have focus, but can play at a low volume ("ducking")
		Focused           // we have full audio focus
	}
}
