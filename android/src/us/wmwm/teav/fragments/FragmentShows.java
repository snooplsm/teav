package us.wmwm.teav.fragments;

import java.util.Collections;

import us.wmwm.teav.DbHelper;
import us.wmwm.teav.R;
import us.wmwm.teav.adapters.ShowAdapter;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class FragmentShows extends Fragment {

	ListView list;

	EditText search;

	private ShowAdapter adapter;

	Handler handler = new Handler();

	SharedPreferences prefs;

	Runnable updateAdapter = new Runnable() {
		@Override
		public void run() {
			adapter.update(search.getText().toString());
			list.setSelectionFromTop(0, 0);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_shows, container, false);
		list = (ListView) root.findViewById(R.id.list);
		search = (EditText) root.findViewById(R.id.search_text);
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		init();
	}

	protected void init() {
		list.setAdapter(adapter = new ShowAdapter(getActivity()));

		search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				handler.removeCallbacks(updateAdapter);
				handler.postDelayed(updateAdapter, 300);
			}
		});

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg, View arg1, int arg2,
					long arg3) {
				Cursor c = (Cursor) arg.getItemAtPosition(arg2);
				if (prefs.contains(c.getString(1))) {
					prefs.edit().remove(c.getString(1)).commit();
				} else {
					prefs.edit()
							.putLong(c.getString(1), System.currentTimeMillis())
							.commit();
					Log.d("SHOWS",""+DbHelper.getInstance().getSchedule(Collections.singleton(c.getString(1))).getCount());
				}
				adapter.notifyDataSetChanged();
			}
		});
	}

}
