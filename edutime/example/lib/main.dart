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
  String _timeCoins = 'Unknown';
  String resultsText = "";

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String timeCoins;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      timeCoins = await Edutime.getCurrencyStats;
    } on PlatformException {
      timeCoins = 'Failed to get info on time coins.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _timeCoins = timeCoins;
    });
  }

  Future<void> updateTimeCoins() async {
    String timeCoins;
    print("Checking time coins");
    try {
      Timeline.startSync('getCurrencyStats');
      timeCoins = await Edutime.getCurrencyStats;
      Timeline.finishSync();
    } on PlatformException {
      timeCoins = 'Failed to get info on time coins.';
    }
    if (!mounted) return;
    setState(() {
      _timeCoins = timeCoins;
      logResult(timeCoins);
    });
  }

  void logResult(String result) {
    var now = DateTime.now();
    String nowString = "${now.hour}:${now.minute}:${now.second}.${now.millisecond}";

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
                  icon: Icon(Icons.refresh),
                  label: Text("Check time coins amount"),
                  onPressed: updateTimeCoins),
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
