package us.wmwm.teav.activities;

import us.wmwm.teav.R;
import us.wmwm.teav.fragments.FragmentShows;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

public class FavoriteShowsActivity extends Activity {

	SharedPreferences prefs;

	Handler handler = new Handler();

;	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		getFragmentManager().beginTransaction().replace(R.id.fragment_main, new FragmentShows().showFavs(true)).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_favorites, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==R.id.menu_shows) {
			startActivity(new Intent(this,MainActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}
}
