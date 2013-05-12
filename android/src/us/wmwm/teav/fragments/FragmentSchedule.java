package us.wmwm.teav.fragments;

import us.wmwm.teav.R;
import us.wmwm.teav.adapters.ScheduleAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentSchedule extends Fragment {

	ViewPager pager;
	
	Handler handler = new Handler();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_day_schedule, container,false);
		pager = (ViewPager) root.findViewById(R.id.pager);
		return root;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		handler.post(new Runnable() {
			@Override
			public void run() {
				pager.setAdapter(new ScheduleAdapter(getActivity(), getFragmentManager()));	
			}
		});
		
	}
	
}
