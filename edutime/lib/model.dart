// Data structures used for exchange between Flutter app and EduKids launcher

// Mapping of data structures
// https://flutter.dev/docs/development/platform-integration/platform-channels#codec

/// CurrencyStats holds the information on the [currentAmount] and [earnedInInstance]
/// To be used in the [getCurrencyStats] call
class CurrencyStats {
  /// Current amount of time coins the user is holding
  double currentAmount;
  /// Amount of time coins earned in the app's currently running instance
  double earnedInInstance;

  /// Constructor for deserialization of data received from the channel
  CurrencyStats.fromMap(Map<String, double> channelData) {
    this.currentAmount = channelData["currentAmount"];
    this.earnedInInstance = channelData["earnedInInstance"];
  }

  String toString() {
    return "currentAmount: $currentAmount, earnedInInstance: $earnedInInstance";
  }
}

