# edutime

A new flutter plugin project.

## Getting Started

This project is a starting point for a Flutter
[plug-in package](https://flutter.dev/developing-packages/),
a specialized package that includes platform-specific implementation code for
Android and/or iOS.

For help getting started with Flutter, view our
[online documentation](https://flutter.dev/docs), which offers tutorials,
samples, guidance on mobile development, and a full API reference.

## For package developers

1. git clone 
2. run `flutter create --project-name=edutime --template=plugin .` in the project root dir - that recreates the IntelliJ config files for Android Studio setup.

a) opening flutter project in Android Studio - use ## Open an existing Android Studio project ## with the project root.

b) opening Android (Kotlin) only part in Android Studio - use ## Open an existing Android Studio project ## with the folder `.\example\android`.


In order to run flutter example application, type from within the project root:

  $ cd example
  $ flutter run

Or run it from within the Android Studio.

Flutter ##example application## code is in `.\example\lib\main.dart`

Dart ##package/plugin## code is in `.\lib\edutime.dart`

Kotlin ##plugin## code is in `.\android\src\main\kotlin\cz\edukids\edutime\EdutimePlugin.kt`. 

iOS and web code is yet to be implemented.
