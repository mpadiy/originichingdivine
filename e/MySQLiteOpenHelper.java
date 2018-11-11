package comp.android.e;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLException;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static String NO_CREATE_TABLES = "no tables";
    public String[][] FieldNames;
    public String[][] FieldTypes;
    public String[] TableNames;
    private String message = "";

    public MySQLiteOpenHelper(Context context, String dbname, CursorFactory factory, int version, String[] tableNames, String[][] fieldNames, String[][] fieldTypes) {
        super(context, dbname, factory, version);
        this.TableNames = tableNames;
        this.FieldNames = fieldNames;
        this.FieldTypes = fieldTypes;
    }

    public void onCreate(SQLiteDatabase db) {
        if (this.TableNames == null) {
            this.message = NO_CREATE_TABLES;
            return;
        }
        for (int i = 0; i < this.TableNames.length; i++) {
            String sql = "CREATE TABLE " + this.TableNames[i] + " (";
            for (int j = 0; j < this.FieldNames[i].length; j++) {
                sql = new StringBuilder(String.valueOf(sql)).append(this.FieldNames[i][j]).append(" ").append(this.FieldTypes[i][j]).append(",").toString();
            }
            db.execSQL(sql.substring(0, sql.length() - 1) + ")");
        }
    }

    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        for (int i = 0; i < this.TableNames[i].length(); i++) {
            db.execSQL("DROP TABLE IF EXISTS " + this.TableNames[i]);
        }
        onCreate(db);
    }

    public void execSQL(String sql) throws SQLException {
        getWritableDatabase().execSQL(sql);
    }

    public Cursor select(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return getReadableDatabase().query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public long insert(String table, String[] fields, String[] values) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        for (int i = 0; i < fields.length; i++) {
            cv.put(fields[i], values[i]);
        }
        return db.insert(table, null, cv);
    }

    public int delete(String table, String where, String[] whereValue) {
        return getWritableDatabase().delete(table, where, whereValue);
    }

    public int update(String table, String[] updateFields, String[] updateValues, String where, String[] whereValue) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        for (int i = 0; i < updateFields.length; i++) {
            cv.put(updateFields[i], updateValues[i]);
        }
        return db.update(table, cv, where, whereValue);
    }

    public String getMessage() {
        return this.message;
    }

    public synchronized void close() {
        super.close();
    }
}
