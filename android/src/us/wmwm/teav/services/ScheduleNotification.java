package us.wmwm.teav.services;

import java.util.Calendar;

import us.wmwm.teav.R;
import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;

public class ScheduleNotification {

	RemoteViews r;

	Context ctx;
	
	public ScheduleNotification(Context ctx) {
		r = new RemoteViews(ctx.getPackageName(), R.layout.notif_schedule);
		this.ctx = ctx;
	}

	public void init(Cursor cursor) {
		for (int i = 0; i < Math.min(cursor.getCount(), 10); i++) {
			int res = ctx.getResources().getIdentifier("notif_"+i, "id", ctx.getPackageName());
			String name = cursor.getString(0);
			long time= cursor.getLong(3);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(time);
			r.setTextViewText(res, name + " " + cal.getTime());			
		}
	}
	
	public RemoteViews getView() {
		return r;
	}

}
