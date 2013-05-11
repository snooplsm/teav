package us.wmwm.teav;

import java.util.Collection;
import java.util.Iterator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbHelper {

	private static DbHelper instance;

	private SQLiteDatabase db;

	private DbHelper(SQLiteDatabase db) {
		this.db = db;
	}

	public static DbHelper getInstance() {
		if (instance == null) {
			instance = new DbHelper(SQLiteDatabase.openDatabase(
					App.getInstance().getFileStreamPath("tvdb.db")
							.getAbsolutePath(), null,
					SQLiteDatabase.OPEN_READONLY|SQLiteDatabase.NO_LOCALIZED_COLLATORS));
		}
		return instance;
	}
	
	public Cursor getShows() {
		return getShows("");
	}
	
	public Cursor getSchedule(Collection<String> favs, long begin, long end) {
		StringBuilder b = new StringBuilder("select name, id as _id, id, time, network, title, episode from schedule where 1=1 ");
		if(favs!=null && !favs.isEmpty()) {
			b.append("and id in (");
			Iterator<String> k = favs.iterator();
			while(k.hasNext()) {
				b.append("'"+k.next()+"'");
				if(k.hasNext()) {
					b.append(",");
				}
			}
			b.append(")");
		}
		
		b.append(" AND time between ").append(begin/1000).append(" AND " ).append(end/1000);
		
		b.append(" order by time asc ");
		Log.d("DB",b.toString());
		return db.rawQuery(b.toString(), null);
		
	}

	public Cursor getShows(String query) {
		//StringTokenizer t = new StringTokenizer(query, " ");
		StringBuilder b = new StringBuilder("select name, id as _id, id from show where name like ? ");
//		String[] args = null;
//		if (t.hasMoreTokens()) {
//			b.append("AND ( ");
//			List<String> parms = new ArrayList<String>(t.countTokens());
//			while (t.hasMoreTokens()) {
//				b.append(" name like ? ");
//				parms.add(t.nextToken()+'%');
//				if(t.hasMoreTokens()) {
//					b.append(" OR ");
//				}
//			}
//			b.append(") ");
//			args = new String[parms.size()];
//			parms.toArray(args);
//		}
		
		b.append(" order by name asc");
		return db.rawQuery(b.toString(), new String[]{query+"%"});
	}

}
