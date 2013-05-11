package us.wmwm.teav.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TeavReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context ctx, Intent arg1) {
		ctx.startActivity(new Intent(ctx,NotificationService.class).putExtra("intent", arg1));
	}

}
