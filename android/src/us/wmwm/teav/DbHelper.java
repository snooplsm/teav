package us.wmwm.teav;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
