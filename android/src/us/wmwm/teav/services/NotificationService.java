package us.wmwm.teav.services;

import java.util.Calendar;

import us.wmwm.teav.DbHelper;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

public class NotificationService extends Service {

	NotificationManager nManager;
	
	AlarmManager alarmManager;
	
	PendingIntent timerIntent;
	
	SharedPreferences prefs;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		initTimer();
		showNotification(intent);
		return super.onStartCommand(intent, flags, startId);
	}

	private void showNotification(Intent intent) {
		if(intent==null || !intent.hasExtra("time")) {
			return;
		}
		
		long time = intent.getLongExtra("time", System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, 6);
		Cursor cursor = DbHelper.getInstance().getSchedule(prefs.getAll().keySet(),time,cal.getTimeInMillis());
		
		ScheduleNotification n = new ScheduleNotification(this);
		
		n.init(cursor);
		
		NotificationCompat.Builder b = new NotificationCompat.Builder(this);
		
		b.setWhen(System.currentTimeMillis());
		
		b.setContent(n.getView());
		
		b.setSmallIcon(android.R.drawable.stat_notify_voicemail);
		
		Notification notif = b.build();
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			notif.bigContentView = n.getView();
		}
		
		nManager.notify(0, notif);
	}

	private void initTimer() {
		Intent intent = new Intent(this, NotificationService.class);
		Calendar wakeup = Calendar.getInstance();
		
		wakeup.add(Calendar.HOUR_OF_DAY, 4);
		intent.putExtra("time", wakeup.getTimeInMillis());
		
		
		if(timerIntent!=null) {
			alarmManager.cancel(timerIntent);
		}
		timerIntent = PendingIntent.getService(this, 0, intent, 0);		
		alarmManager.set(AlarmManager.RTC, wakeup.getTimeInMillis(), timerIntent);
	}
}
