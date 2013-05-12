package us.wmwm.teav;

import us.wmwm.teav.services.NotificationService;
import android.app.Application;
import android.content.Intent;

public class App extends Application {

	private static App instance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		
		Intent i = new Intent(this,NotificationService.class);
		i.putExtra("time", System.currentTimeMillis());
		startService(i);
	}
	
	public static App getInstance() {
		return instance;
	}
	
}
