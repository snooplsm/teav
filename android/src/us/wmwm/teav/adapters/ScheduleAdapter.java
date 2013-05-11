package us.wmwm.teav.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ScheduleAdapter extends FragmentPagerAdapter {

	Cursor cursor;
	
	public ScheduleAdapter(Context ctx, FragmentManager manager) {
		super(manager);
		//cursor = DbHelper.getInstance().getSchedule(PreferenceManager.getDefaultSharedPreferences(ctx).getAll().keySet());
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}


}
