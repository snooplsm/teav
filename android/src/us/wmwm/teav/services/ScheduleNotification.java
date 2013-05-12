package us.wmwm.teav.services;

import java.text.DateFormat;
import java.util.Calendar;

import us.wmwm.teav.R;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.RemoteViews;

public class ScheduleNotification {

	RemoteViews r;

	Context ctx;
	
	private static final DateFormat DF = DateFormat.getTimeInstance(DateFormat.SHORT);
	
	public ScheduleNotification(Context ctx) {
		r = new RemoteViews(ctx.getPackageName(), R.layout.notif_schedule);
		this.ctx = ctx;
	}

	public void init(Cursor cursor) {
		for (int i = 0; i < Math.min(cursor.getCount(), 10); i++) {
			cursor.moveToNext();
			int res = ctx.getResources().getIdentifier("notif_"+i, "id", ctx.getPackageName());
			String name = cursor.getString(0);
			String network = cursor.getString(4);
			long time= cursor.getLong(3) * 1000;
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(time);
			r.setTextViewText(res, " | "+ name + " - " + network);
			res = ctx.getResources().getIdentifier("time_"+i, "id", ctx.getPackageName());			
			r.setTextViewText(res, DF.format(cal.getTime()).toLowerCase());
			res = ctx.getResources().getIdentifier("container_"+i, "id", ctx.getPackageName());
			r.setViewVisibility(res, View.VISIBLE);
		}
		cursor.close();		
	}
	
	public RemoteViews getView() {
		return r;
	}

}
