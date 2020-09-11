package cz.edukids.edutime_example

import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import cz.edukids.edutime.EdutimePlugin

class MainActivity: FlutterActivity() {

    override fun configureFlutterEngine(engine: FlutterEngine) {
        Log.i("MainActivity", "FlutterEngine is being configured")
        engine.getPlugins().add(EdutimePlugin())
    }

}
