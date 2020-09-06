# EduTime

EduTime is the package plugin wrapping the EduTime library ( https://github.com/digicas/edutime-library-android ) for the Flutter ( https://flutter.dev/ ) based applications.

## Getting Started

This project is a starting point for a Flutter
[plug-in package](https://flutter.dev/developing-packages/),
a specialized package that includes platform-specific implementation code for
Android and/or iOS.

For help getting started with Flutter, view our
[online documentation](https://flutter.dev/docs), which offers tutorials,
samples, guidance on mobile development, and a full API reference.

This repo (plugin package) contains several projects:

**Flutter example application** code is in [./edutime/example/]. 

**Kotlin plugin** code and related gradle setups are in [./edutime/android/]. 

**Dart package/plugin** code is in [.edutime/lib/].

## Requirements 

Android min SDK: 21
Flutter min version: TBD

## For package developers

note: Due to the Flutter's usage of the parent folder name, the flutter project is moved into the [edutime subfolder](./edutime/).

We will call `edutime` subfolder to be **Flutter project root** from now on.

### Preparation

1. Prepare the Flutter environment (tested on flutter channel master version 1.22)
2. git clone this repo

### Test building apk from command line

```
cd ./edutime/example
flutter clean
flutter build apk 
```

WARNING: Flutter requires AGP plugin < 4

### Generating flutter related documentation

`%FLUTTER_ROOT%\bin\cache\dart-sdk\bin\dartdoc`


## Edit in IDE

Run `flutter create --project-name=edutime --template=plugin .` in the edutime root dir - that recreates the IntelliJ config files for Android Studio setup.

a) opening flutter project in Android Studio - use `Open an existing Android Studio project` with the edutime project root.

b) opening Android (Kotlin) only part in Android Studio - use `Open an existing Android Studio project` with the folder `.\example\android`. That will open the example app and also edutime module, which contains the Kotlin code for the plugin.


## iOS and web platforms

iOS and web code is yet to be implemented.
