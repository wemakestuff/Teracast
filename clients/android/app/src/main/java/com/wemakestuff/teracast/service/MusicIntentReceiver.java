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

package com.wemakestuff.teracast.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.squareup.otto.Bus;
import com.wemakestuff.teracast.Injector;
import com.wemakestuff.teracast.core.Constants;
import com.wemakestuff.teracast.media.event.HeadsetPluggedInEvent;
import com.wemakestuff.teracast.media.event.HeadsetUnpluggedEvent;
import com.wemakestuff.teracast.media.event.NextEvent;
import com.wemakestuff.teracast.media.event.PauseEvent;
import com.wemakestuff.teracast.media.event.PlayEvent;
import com.wemakestuff.teracast.media.event.PreviousEvent;
import com.wemakestuff.teracast.media.event.RelativeSeekEvent;
import com.wemakestuff.teracast.media.event.StopEvent;
import com.wemakestuff.teracast.media.event.ToggleEvent;
import com.wemakestuff.teracast.util.Ln;

import javax.inject.Inject;

/**
 * Receives broadcasted intents. In particular, we are interested in the android.media.AUDIO_BECOMING_NOISY,
 * android.intent.action.MEDIA_BUTTON, and android.intent.action.HEADSET_PLUG intents, which are broadcast, for example
 * , when the user disconnects the headphones. This class works because we are declaring it in a &lt;receiver&gt; tag in
 * AndroidManifest.xml.
 */
public class MusicIntentReceiver extends BroadcastReceiver {
    public static final String STATE = "state";
    public static final String NAME = "name";
    public static final String MICROPHONE = "microphone";
    @Inject
    protected Bus BUS;

    public MusicIntentReceiver() {
        super();
        Injector.inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)) {
            producePausePlaybackEvent();
        } else if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getExtras().getInt(STATE, -1);
            String name = intent.getExtras().getString(NAME);
            int microphone = intent.getExtras().getInt(MICROPHONE, -1);

            if (state == 0) {
                produceHeadsetUnpluggedEvent();
            } else if (state == 1) {
                produceHeadsetPluggedInEvent();
            }
        } else if (intent.getAction().equals(Intent.ACTION_MEDIA_BUTTON) || intent.getAction().equals(Constants.Intent.ACTION_MEDIA_BUTTON)) {
            KeyEvent keyEvent = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
            if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
                return;
            }

            switch (keyEvent.getKeyCode()) {
                case KeyEvent.KEYCODE_HEADSETHOOK:
                    Ln.d("Got Headphone Hook");
                    break;
                case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                    Ln.d("Got Toggle Request");
                    produceTogglePlaybackEvent();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PLAY:
                    Ln.d("Got Play Request");
                    producePlayPlaybackEvent();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PAUSE:
                    Ln.d("Got Pause Request");
                    producePausePlaybackEvent();
                    break;
                case KeyEvent.KEYCODE_MEDIA_STOP:
                    Ln.d("Got Stop Request");
                    produceStopPlaybackEvent();
                    break;
                case KeyEvent.KEYCODE_MEDIA_NEXT:
                    Ln.d("Got Next Request");
                    produceNextPlaybackEvent();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                    Ln.d("Got Previous Request");
                    producePreviousPlaybackEvent();
                    break;
                case KeyEvent.KEYCODE_MEDIA_REWIND:
                    Ln.d("Got Rewind Request");
                    produceRewindEvent();
                    break;
                case KeyEvent.KEYCODE_MEDIA_FAST_FORWARD:
                    Ln.d("Got Fast Forward Request");
                    produceFastForwardEvent();
                    break;
                default:
                    break;
            }
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
     * Posts a {@link com.wemakestuff.teracast.media.event.StopEvent} message to the {@link Bus}
     */
    private void produceStopPlaybackEvent() {
        BUS.post(new StopEvent());
    }

    /**
     * Posts a {@link com.wemakestuff.teracast.media.event.PlayEvent} message to the {@link Bus}
     */
    private void producePlayPlaybackEvent() {
        BUS.post(new PlayEvent());
    }

    /**
     * Posts a {@link com.wemakestuff.teracast.media.event.PauseEvent} message to the {@link Bus}
     */
    private void producePausePlaybackEvent() {
        BUS.post(new PauseEvent());
    }

    /**
     * Posts a {@link com.wemakestuff.teracast.media.event.PreviousEvent} message to the {@link Bus}
     */
    private void producePreviousPlaybackEvent() {
        BUS.post(new PreviousEvent());
    }

    /**
     * Posts a {@link com.wemakestuff.teracast.media.event.NextEvent} message to the {@link Bus}
     */
    private void produceNextPlaybackEvent() {
        BUS.post(new NextEvent());
    }

    /**
     * Posts a {@link com.wemakestuff.teracast.media.event.ToggleEvent} message to the {@link Bus}
     */
    private void produceTogglePlaybackEvent() {
        BUS.post(new ToggleEvent());
    }

    /**
     * Posts a {@link RelativeSeekEvent} message to the {@link Bus} which advances the playback by {@link
     * com.wemakestuff.teracast.core.Constants#SEEK_AMOUNT}
     *
     * @see #produceRelativeSeekEvent(int)
     */
    private void produceFastForwardEvent() {
        produceRelativeSeekEvent(Constants.SEEK_AMOUNT);
    }

    /**
     * Posts a {@link RelativeSeekEvent} message to the {@link Bus} which reverses the playback by {@link
     * Constants#SEEK_AMOUNT}
     *
     * @see #produceRelativeSeekEvent(int)
     */
    private void produceRewindEvent() {
        produceRelativeSeekEvent(-1 * Constants.SEEK_AMOUNT);
    }

    /**
     * Posts a {@link RelativeSeekEvent} message to the {@link Bus}
     */
    private void produceRelativeSeekEvent(int seekAmount) {
        BUS.post(new RelativeSeekEvent(seekAmount));
    }
}
