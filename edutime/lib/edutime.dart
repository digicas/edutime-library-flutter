
import 'dart:async';

import 'package:flutter/services.dart';

// Mapping of data structures
// https://flutter.dev/docs/development/platform-integration/platform-channels#codec

/// models

class CurrencyStats {
  /// Current amount of time coins the user is holding
  double currentAmount;
  /// Amount of time coins earned in the app's currently running instance
  double earnedInInstance;

  /// Constructor for deserialization of data received from the channel
  CurrencyStats.fromMap(Map channelData) {
    this.currentAmount = channelData["currentAmount"];
    this.earnedInInstance = channelData["earnedInInstance"];
  }

  String toString() {
    return "currentAmount: $currentAmount, earnedInInstance: $earnedInInstance";
  }
}

///

class Edutime {
  static const MethodChannel _channel =
      const MethodChannel('edutime');

  /// Requests current currency statistics. App can adjust the
  /// exercises to maximize the time user spends in the app by cherry picking longer tasks and
  /// vice versa.
  static Future<String> get platformVersion async {
    final Map currencyStatsMap = await _channel.invokeMethod('getCurrencyStats');
    final CurrencyStats currencyStats = CurrencyStats.fromMap(currencyStatsMap);
    print (currencyStats);
    return currencyStats.toString();
  }
}
