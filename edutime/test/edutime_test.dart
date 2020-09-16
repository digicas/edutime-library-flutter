import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:edutime/edutime.dart';
import 'package:edutime/model.dart';

void main() {
  const MethodChannel channel = MethodChannel('edutime');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return CurrencyStats.fromMap({"currentAmount": 76, "earnedInInstance": 32});
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getCurrencyStats', () async {
    expect(await Edutime.getCurrencyStats, "currentAmount: 76, earnedInInstance: 32");
  });
}
