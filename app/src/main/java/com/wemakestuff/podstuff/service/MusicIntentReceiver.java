/*   
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wemakestuff.podstuff.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import com.squareup.otto.Bus;
import com.wemakestuff.podstuff.media.event.*;

import javax.inject.Inject;

/**
 * Receives broadcasted intents. In particular, we are interested in the android.media.AUDIO_BECOMING_NOISY,
 * android.intent.action.MEDIA_BUTTON, and android.intent.action.HEADSET_PLUG intents, which are broadcast, for example
 * , when the user disconnects the headphones. This class works because we are declaring it in a &lt;receiver&gt; tag in
 * AndroidManifest.xml.
 */
public class MusicIntentReceiver extends BroadcastReceiver {
	public static final String STATE      = "state";
	public static final String NAME       = "name";
	public static final String MICROPHONE = "microphone";
	@Inject
	protected Bus BUS;

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)) {
			BUS.register(this);
			producePausePlaybackEvent();
			BUS.unregister(this);
		} else if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
			BUS.register(this);
			int state = intent.getExtras().getInt(STATE, -1);
			String name = intent.getExtras().getString(NAME);
			int microphone = intent.getExtras().getInt(MICROPHONE, -1);

			if (state == 0) {
				produceHeadsetUnpluggedEvent();
			} else if (state == 1) {
				produceHeadsetPluggedInEvent();
			}
			BUS.unregister(this);
		} else if (intent.getAction().equals(Intent.ACTION_MEDIA_BUTTON)) {
			BUS.register(this);
			KeyEvent keyEvent = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
			if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
				return;
			}

			switch (keyEvent.getKeyCode()) {
				case KeyEvent.KEYCODE_HEADSETHOOK:
				case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
					produceTogglePlaybackEvent();
					break;
				case KeyEvent.KEYCODE_MEDIA_PLAY:
					producePlayPlaybackEvent();
					break;
				case KeyEvent.KEYCODE_MEDIA_PAUSE:
					producePausePlaybackEvent();
					break;
				case KeyEvent.KEYCODE_MEDIA_STOP:
					produceStopPlaybackEvent();
					break;
				case KeyEvent.KEYCODE_MEDIA_NEXT:
					produceNextPlaybackEvent();
					break;
				case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
					producePreviousPlaybackEvent();
					break;
			}
			BUS.unregister(this);
		}
	}

	/**
	 * Posts a {@link HeadsetPluggedInEvent} message to the {@link Bus}
	 */
	private void produceHeadsetPluggedInEvent() {
		BUS.post(new HeadsetPluggedInEvent());
	}

	/**
	 * Posts a {@link HeadsetUnpluggedEvent} message to the {@link Bus}
	 */
	private void produceHeadsetUnpluggedEvent() {
		BUS.post(new HeadsetUnpluggedEvent());
	}

	/**
	 * Posts a {@link com.wemakestuff.podstuff.media.event.StopEvent} message to the {@link Bus}
	 */
	private void produceStopPlaybackEvent() {
		BUS.post(new StopEvent());
	}

	/**
	 * Posts a {@link com.wemakestuff.podstuff.media.event.PlayEvent} message to the {@link Bus}
	 */
	private void producePlayPlaybackEvent() {
		BUS.post(new PlayEvent());
	}

	/**
	 * Posts a {@link com.wemakestuff.podstuff.media.event.PauseEvent} message to the {@link Bus}
	 */
	private void producePausePlaybackEvent() {
		BUS.post(new PauseEvent());
	}

	/**
	 * Posts a {@link com.wemakestuff.podstuff.media.event.PreviousEvent} message to the {@link Bus}
	 */
	private void producePreviousPlaybackEvent() {
		BUS.post(new PreviousEvent());
	}

	/**
	 * Posts a {@link com.wemakestuff.podstuff.media.event.NextEvent} message to the {@link Bus}
	 */
	private void produceNextPlaybackEvent() {
		BUS.post(new NextEvent());
	}

	/**
	 * Posts a {@link RewindPlaybackEvent} message to the {@link Bus}
	 */
	private void produceRewindPlaybackEvent() {
		BUS.post(new RewindPlaybackEvent());
	}

	/**
	 * Posts a {@link com.wemakestuff.podstuff.media.event.ToggleEvent} message to the {@link Bus}
	 */
	private void produceTogglePlaybackEvent() {
		BUS.post(new ToggleEvent());
	}
}
