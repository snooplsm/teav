package us.wmwm.teav.fragments;

import us.wmwm.teav.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentDaySchedule extends Fragment {
	
	ListView list;
	
	View search;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_shows, container,false);
		list = (ListView) root.findViewById(R.id.list);
		search = root.findViewById(R.id.search_text);
		search.setVisibility(View.GONE);
		return root;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
}
