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
  CurrencyStats.fromChannelMap(Map<String, double> channelData) {
    this.currentAmount = channelData["currentAmount"];
    this.earnedInInstance = channelData["earnedInInstance"];
  }

  String toString() {
    return "Currency stats: currentAmount: $currentAmount, earnedInInstance: $earnedInInstance";
  }
}

/// TimeConstraints of the app has the information on launcher's time category
/// propagated time limits towards the app
/// To be used in the [getTimeConstraints] call
class TimeConstraints {
  /// Datetime at which the app can be (in the future) or was (in the past) started.
  DateTime startTime;

  /// Datetime at which the app will be stopped (if it is running) or
  /// when it will blocked from starting.
  DateTime stopTime;

  /// Constructor for deserialization of data received from the channel
  TimeConstraints.fromChannelMap(Map<String, int> channelData) {
    this.startTime =
        DateTime.fromMillisecondsSinceEpoch(channelData["startTimestamp"]);
    this.stopTime =
        DateTime.fromMillisecondsSinceEpoch(channelData["stopTimestamp"]);
  }

  String toString() {
    return "Time constraints: startTime: $startTime, stopTime: $stopTime";
  }
}
