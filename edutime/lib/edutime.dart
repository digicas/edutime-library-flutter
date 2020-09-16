
import 'dart:async';

import 'package:flutter/services.dart';
import './model.dart';

class Edutime {
  static const MethodChannel _channel =
      const MethodChannel('edutime');

  /// Requests current currency statistics. App can adjust the
  /// exercises to maximize the time user spends in the app by cherry picking longer tasks and
  /// vice versa.
  static Future<CurrencyStats> get getCurrencyStats async {
    final Map currencyStatsMap = await _channel.invokeMethod('getCurrencyStats');
    final CurrencyStats currencyStats = CurrencyStats.fromChannelMap(currencyStatsMap);
    print (currencyStats);
    return currencyStats;
  }

  /// Requests current time constraints on app. App can adjust the
  /// exercises to maximize the time user spends in the app by cherry picking longer tasks and
  /// vice versa.
  static Future<TimeConstraints> get getTimeConstraints async {
    final Map timeConstraintsMap = await _channel.invokeMethod('getTimeConstraints');
    final TimeConstraints timeConstraints = TimeConstraints.fromChannelMap(timeConstraintsMap);
    print (timeConstraints);
    return timeConstraints;
  }

}
