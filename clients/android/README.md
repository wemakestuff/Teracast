# Teracast App

This repository contains the source code for the [Teracast](https://github.com/wemakestuff/Teracast)

Please see the [issues](https://github.com/wemakestuff/Teracast/issues) section
to report any bugs or feature requests and to see the list of known issues.

## About

The Teracast application is a Podcasting application for Android that doesn't suck.

## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

Copyright 2013 David Pate

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


## Building

The build requires [Maven](http://maven.apache.org/download.html)
v3.0.3+ and the [Android SDK](http://developer.android.com/sdk/index.html)
to be installed in your development environment. In addition you'll need to set
the `ANDROID_HOME` environment variable to the location of your SDK:

    export ANDROID_HOME=/home/donnfelker/tools/android-sdk

After satisfying those requirements, the build is pretty simple:

* Run `mvn clean package` from the `app` directory to build the APK only
* Run `mvn clean install` from the root directory to build the app and also run
  the integration tests, this requires a connected Android device or running
  emulator

You might find that your device doesn't let you install your build if you
already have the version from the Android Market installed.  This is standard
Android security as it it won't let you directly replace an app that's been
signed with a different key.  Manually uninstall Teracast from your device and
you will then be able to install your own built version.

## Acknowledgements

Teracast is the result of spending countless hours frustrated with sub-par Podcasting applications
on Android.

Teracast is a result of a template project I've developed over the years as well as
a combination of a lot of great work that the [GitHub Gaug.es](http://www.github.com/github/gauges-android)
app and [GitHub Android](http://www.github.com/github/android) app showcased. Some fo the
code in this project is based on the GitHub Gaug.es and GitHub Android app.

Teracast is built on the awesome [Parse.com API](http://www.parse.com/)
and uses many great open-source libraries from the Android dev community:

* [android-bootstrap](https://github.com/donnfelker/android-bootstrap) for providing
  a superb starting point and implementing the libraries below.
* [ActionBarSherlock](https://github.com/JakeWharton/ActionBarSherlock) for a
  consistent, great looking header across all Android platforms,
* [ViewPagerIndicator](https://github.com/JakeWharton/Android-ViewPagerIndicator)
  for swiping between fragments and
* [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids) for
  view animations - all from [Jake Wharton](http://jakewharton.com/).
* [Picasso](https://github.com/square/picasso) for image downloading and caching.
* [Dagger](https://github.com/square/dagger) for dependency-injection.
* [ButterKnife](https://github.com/JakeWharton/butterknife) for view injection
* [Otto](https://github.com/square/otto) as the event bus
* [Robotium](http://code.google.com/p/robotium/)
  for driving our app during integration tests.
* [android-maven-plugin](https://github.com/jayway/maven-android-plugin)
  for automating our build and producing release-ready APKs.
* [http-request](https://github.com/kevinsawicki/http-request) for interacting with
  remote HTTP resources (API's in this case).
* [google-gson](http://code.google.com/p/google-gson/) for consuming JSON and hydrating
  POJO's for use in the app.


## Contributing

Please fork this repository and contribute back using
[pull requests](https://github.com/wemakestuff/Teracast/pulls).

Any contributions, large or small, major features, bug fixes, additional
language translations, unit/integration tests are welcomed and appreciated
but will be thoroughly reviewed and discussed.
