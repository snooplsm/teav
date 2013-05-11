package us.wmwm.teav.views;

import us.wmwm.teav.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShowListItem extends RelativeLayout {

	public TextView text;
	
	public TextView fav;
	
	public ShowListItem(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		LayoutInflater.from(context).inflate(R.layout.view_show_list_item, this);
		text = (TextView) findViewById(R.id.text);
		fav = (TextView) findViewById(R.id.favorite_text);
	}
	
	public void setFavorite(boolean fav) {
		if(fav) {
			this.fav.setVisibility(View.VISIBLE);
		} else {
			this.fav.setVisibility(View.GONE);
		}
	}

}
