package us.wmwm.teav;

import org.apache.commons.io.IOUtils;

import us.wmwm.teav.fragments.FragmentShows;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;

import com.happytap.schedule.service.ThreadHelper;

public class MainActivity extends Activity {

	SharedPreferences prefs;

	Handler handler = new Handler();

	private Runnable createDatabase = new Runnable() {
		@Override
		public void run() {
			try {
				Log.d("DBCREATE", "Installing DB");
				IOUtils.copy(getResources().openRawResource(R.raw.tvdb),
						openFileOutput("tvdb.db", Context.MODE_PRIVATE));
				Log.d("DBCREATE", "DB Installed");
				prefs.edit()
						.putInt("db_version",
								getPackageManager().getPackageInfo(
										getPackageName(), 0).versionCode)
						.commit();
				handler.post(onDbCreate);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	};

	private Runnable onDbCreate = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			init();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		setContentView(R.layout.activity_main);
		try {
			if (prefs.getInt("db_version", 0) == getPackageManager()
					.getPackageInfo(getPackageName(), 0).versionCode) {
				init();
			} else {
				ThreadHelper.getScheduler().submit(createDatabase);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		getFragmentManager().beginTransaction().replace(R.id.fragment_main, new FragmentShows()).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
