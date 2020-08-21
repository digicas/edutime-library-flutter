
import 'dart:async';

import 'package:flutter/services.dart';

class Edutime {
  static const MethodChannel _channel =
      const MethodChannel('edutime');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
