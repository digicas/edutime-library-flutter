import 'package:edutime/model.dart';
import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:edutime/edutime.dart';
import 'dart:developer';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String resultsText = "";

  @override
  void initState() {
    super.initState();
    updateTimeCoinsInfo();
    // initPlatformState();
  }

  // // Platform messages are asynchronous, so we initialize in an async method.
  // Future<void> initPlatformState() async {...}

  /// Example of getting the information on time coins amounts
  /// Platform messages are asynchronous, so we call them in an async method.
  Future<void> updateTimeCoinsInfo() async {
    CurrencyStats timeCoins;
    String resultText;
    print("Checking time coins info");
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      Timeline.startSync('getCurrencyStats');
      timeCoins = await Edutime.getCurrencyStats;
      Timeline.finishSync();
      resultText = timeCoins.toString();
    } on PlatformException {
      resultText = 'Failed to get info on time coins.';
    }
    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;
    setState(() {
      logResult(resultText);
    });
  }

  /// The approach is similar for other cases
  Future<void> getAppTimeConstraints() async {
    TimeConstraints timeConstraints;
    String resultText;
    print("Checking app's time constraints info");
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      Timeline.startSync('getTimeConstraints');
      timeConstraints = await Edutime.getTimeConstraints;
      Timeline.finishSync();
      resultText = timeConstraints.toString();
    } on PlatformException {
      resultText = 'Failed to get info on app`s time constraints.';
    }
    logResult(resultText);
  }

  /// The approach is similar for other cases
  Future<void> getScreenTimeCategorySetup() async {
    ScreenTimeCategorySetup screenTimeCategorySetup;
    String resultText;
    print("Checking app's time constraints info");
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      Timeline.startSync('getScreenTimeCategorySetup');
      screenTimeCategorySetup = await Edutime.getScreenTimeCategorySetup;
      Timeline.finishSync();
      resultText = screenTimeCategorySetup.toString();
    } on PlatformException {
      resultText = 'Failed to get info on screentime categories.';
    }
    logResult(resultText);
  }



  void logResult(String result) {
    var now = DateTime.now();
    String nowString = "${now.hour}:${now.minute}:${now.second}.${now.millisecond}";

    if (!mounted) return;
    setState(() {
      resultsText = "$nowString: $result \n\n" + resultsText;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('EduTime Plugin example app'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              RaisedButton.icon(
                  icon: Icon(Icons.monetization_on),
                  label: Text("Check time coins amount user has"),
                  onPressed: updateTimeCoinsInfo),
              RaisedButton.icon(
                  icon: Icon(Icons.timelapse),
                  label: Text("Check time constraints for current app"),
                  onPressed: getAppTimeConstraints),
              RaisedButton.icon(
                  icon: Icon(Icons.category),
                  label: Text("Check relevant screentime categories"),
                  onPressed: getScreenTimeCategorySetup),
              Expanded(
                  child: Container(
                    color: Color(0xaabbccdd),
                    child: SingleChildScrollView(
                        child: Text(resultsText),
                ),
                  ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
