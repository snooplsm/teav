package us.wmwm.teav.adapters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import us.wmwm.teav.DbHelper;
import us.wmwm.teav.fragments.FragmentDaySchedule;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.format.DateUtils;

public class ScheduleAdapter extends FragmentPagerAdapter {

	Cursor cursor;
	
	SharedPreferences prefs;
	
	static DateFormat DF = DateFormat.getDateInstance(DateFormat.SHORT);
	
	public ScheduleAdapter(Context ctx, FragmentManager manager) {
		super(manager);
		prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		cursor = DbHelper.getInstance().getDays(prefs.getAll().keySet(), Long.MIN_VALUE, Long.MAX_VALUE);
	}

	@Override
	public Fragment getItem(int arg0) {
		FragmentDaySchedule f = new FragmentDaySchedule();
		return f;
	}

	@Override
	public int getCount() {
		return cursor.getCount();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		cursor.moveToPosition(position);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(cursor.getLong(0)*1000);

		return DF.format(c.getTime());
	}

}
