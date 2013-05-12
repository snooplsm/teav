package us.wmwm.teav.adapters;

import java.util.Set;

import us.wmwm.teav.DbHelper;
import us.wmwm.teav.views.ShowListItem;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

public class ShowAdapter extends CursorAdapter {

	SharedPreferences prefs;
	
	public ShowAdapter(Context context) {
		super(context, null,false);
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		swapCursor(DbHelper.getInstance().getShows());
	}

	@Override
	public void bindView(View v, Context ctx, Cursor c) {
		// TODO Auto-generated method stub
		ShowListItem i = (ShowListItem)v;
		i.text.setText(c.getString(0));
		boolean fav = false;
		if(prefs.contains(c.getString(1))) {
			fav = true;
		} 
		
		i.setFavorite(fav);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return new ShowListItem(context);
	}

	public void showFavs(Set<String> favs) {
		swapCursor(DbHelper.getInstance().getShows(favs));
	}
	
	public void update(String string) {
		swapCursor(DbHelper.getInstance().getShows(string));
	}

}
