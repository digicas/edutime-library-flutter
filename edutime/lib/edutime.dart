
import 'dart:async';

import 'package:flutter/services.dart';
import './model.dart';

class Edutime {
  static const MethodChannel _channel =
      const MethodChannel('edutime');

  /// Requests current currency statistics. App can adjust the
  /// exercises to maximize the time user spends in the app by cherry picking longer tasks and
  /// vice versa.
  static Future<String> get getCurrencyStats async {
    final Map currencyStatsMap = await _channel.invokeMethod('getCurrencyStats');
    final CurrencyStats currencyStats = CurrencyStats.fromMap(currencyStatsMap);
    print (currencyStats);
    return currencyStats.toString();
  }
}
