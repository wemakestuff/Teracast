

package com.wemakestuff.teracast.core;

import com.wemakestuff.teracast.R;

/**
 * Bootstrap constants
 */
public class Constants {

    public static final int IMAGE_PLACEHOLDER = R.drawable.ic_contact_picture;
    public static final int SEEK_AMOUNT = 15000;

    public static class Auth {
        /**
         * Account type id
         */
        public static final String BOOTSTRAP_ACCOUNT_TYPE = "com.wemakestuff.teracast";
        /**
         * Account name
         */
        public static final String BOOTSTRAP_ACCOUNT_NAME = "PodStuff";
        /**
         * Provider id
         */
        public static final String BOOTSTRAP_PROVIDER_AUTHORITY = "com.wemakestuff.teracast.sync";
        /**
         * Auth token type
         */
        public static final String AUTHTOKEN_TYPE = BOOTSTRAP_ACCOUNT_TYPE;

        private Auth() {
        }
    }

    public static class System {
        /**
         * Wifi Lock Tag
         */
        public static final String WIFI_LOCK_TAG = "com.wemakestuff.teracast.WIFI_LOCK";

        private System() {
        }
    }

    /**
     * All HTTP is done through a REST style API built for demonstration purposes on Parse.com Thanks to the nice people at
     * Parse for creating such a nice system for us to use for bootstrap!
     */
    public static class Http {
        /**
         * Base URL for all requests
         */
        public static final String URL_BASE = "https://api.parse.com";
        /**
         * Authentication URL
         */
        public static final String URL_AUTH = URL_BASE + "/1/login";
        /**
         * List Users URL
         */
        public static final String URL_USERS = URL_BASE + "/1/users";
        /**
         * List News URL
         */
        public static final String URL_NEWS = URL_BASE + "/1/classes/News";
        /**
         * List Checkin's URL
         */
        public static final String URL_CHECKINS = URL_BASE + "/1/classes/Locations";
        public static final String PARSE_APP_ID = "zHb2bVia6kgilYRWWdmTiEJooYA17NnkBSUVsr4H";
        public static final String PARSE_REST_API_KEY = "N2kCY1T3t3Jfhf9zpJ5MCURn3b25UpACILhnf5u9";
        public static final String HEADER_PARSE_REST_API_KEY = "X-Parse-REST-API-Key";
        public static final String HEADER_PARSE_APP_ID = "X-Parse-Application-Id";
        public static final String CONTENT_TYPE_JSON = "application/json";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String SESSION_TOKEN = "sessionToken";

        private Http() {
        }

    }

    public static class Extra {
        public static final String NEWS_ITEM = "news_item";
        public static final String MEDIA_ITEM = "com.wemakestuff.teracast.extras.MEDIA_ITEM";
        public static final String USER = "user";

        private Extra() {
        }

    }

    public static class Intent {
        /**
         * Action prefix for all intents created
         */
        public static final String INTENT_PREFIX = "com.wemakestuff.teracast";
        /**
         * Value used to transfer Rss Items.
         */
        public static final String RSS_ITEM = INTENT_PREFIX + ".intent.RssItem";
        public static final String EXTRA_PODCAST = INTENT_PREFIX + ".intent.Podcast";
        public static final String ACTION_MEDIA_BUTTON = INTENT_PREFIX + ".action.MEDIA_BUTTON";

        private Intent() {
        }

    }

    public static class Notification {
        public static final int PLAYBACK_NOTIFICATION_ID = 42;
        public static final int TIMER_NOTIFICATION_ID = 1000; // Why 1000? Why not? :)

        private Notification() {
        }
    }

}


