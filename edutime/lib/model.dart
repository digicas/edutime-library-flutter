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
  CurrencyStats.fromChannelMap(Map channelData) {
    this.currentAmount = channelData["currentAmount"];
    this.earnedInInstance = channelData["earnedInInstance"];
  }

  String toString() {
    return "Currency stats: currentAmount: $currentAmount, earnedInInstance: $earnedInInstance";
  }
}

/// TimeConstraints of the app has the information on launcher's time category
/// which applies time limits towards the app
/// To be used in the [getTimeConstraints] call
class TimeConstraints {
  /// Datetime at which the app can be (in the future) or was (in the past) started.
  DateTime startTime;

  /// Datetime at which the app will be stopped (if it is running) or
  /// when it will blocked from starting.
  DateTime stopTime;

  /// Constructor for deserialization of data received from the channel
  /// expects startTimestamp and stopTimestamp to be int
  TimeConstraints.fromChannelMap(Map channelData) {
    this.startTime =
        DateTime.fromMillisecondsSinceEpoch(channelData["startTimestamp"]);
    this.stopTime =
        DateTime.fromMillisecondsSinceEpoch(channelData["stopTimestamp"]);
  }

  String toString() {
    return "Time constraints: startTime: $startTime, stopTime: $stopTime";
  }
}

/// Configuration and state of the launcher's category (app folder)
class ScreenTimeCategory {
  /// Category IDs are normalised and unique:
  /// OFF, BASIC, EDU, CREATE, GROWTH, CONSUME
  String id;

  /// Category name as defined by child's parent
  String name;

  /// Category might be currently locked due to rules set by the child's parent
  bool isLocked;

  /// Whether the category is applied at the current moment.
  /// Current means at the time of SDK request.
  bool isSelected;

  /// Constructor for deserialization of data received from the channel
  ScreenTimeCategory.fromChannelMap(Map channelData) {
    this.id = channelData["id"];
    this.name = channelData["name"];
    this.isLocked = channelData["isLocked"];
    this.isSelected = channelData["isSelected"];
  }

  String toString() {
    return "cat: $id: $name: ${isLocked ? '' : 'un'}locked";
  }
}

/// Setup of the screentime categories for the launcher's session
class ScreenTimeCategoryInfo {
  /// Launcher's currently running category (in this time period)
  ScreenTimeCategory currentCategory;

  /// Launcher's category assigned to this app
  ScreenTimeCategory assignedCategory;

  /// Available categories under which the app can be listed (up to cca 10)
  List<ScreenTimeCategory> availableCategories;

  /// Constructor for deserialization of data received from the channel
  ScreenTimeCategoryInfo.fromChannelMap(Map channelData) {
    this.currentCategory = channelData["currentCategory"];
    this.assignedCategory = channelData["assignedCategory"];
    List<Map> availableCategoriesMaps = channelData["availableCategories"];
    this.availableCategories = availableCategoriesMaps
        .map((m) => ScreenTimeCategory.fromChannelMap(m))
        .toList();
  }

  @override
  String toString() {
    return "catset: current: $currentCategory, assigned: $assignedCategory, available: $availableCategories";
  }
}
