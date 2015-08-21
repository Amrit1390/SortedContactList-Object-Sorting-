package com.example.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "MyDBName.db";
	public static final int DATABASE_VERSION = 1;

	public static final String TABLE_CONTACT = "contact";

	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_PHONE = "phone";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_STREET = "street";
	public static final String FIELD_PLACE = "place";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String query = "CREATE TABLE " + TABLE_CONTACT + " ( " + FIELD_ID
				+ " INTEGER PRIMARY KEY, " + FIELD_NAME + " TEXT,"
				+ FIELD_PHONE + " TEXT, " + FIELD_EMAIL + " TEXT, "
				+ FIELD_STREET + " TEXT," + FIELD_PLACE + " )";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
		onCreate(db);
	}

	public boolean insertContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FIELD_NAME, contact.getName());
		values.put(FIELD_PHONE, contact.getPhone());
		values.put(FIELD_PLACE, contact.getPlace());
		values.put(FIELD_STREET, contact.getStreet());
		values.put(FIELD_EMAIL, contact.getEmail());
		try {
			db.insert(TABLE_CONTACT, null, values);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Contact> getContact() {
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor resultSet = db.rawQuery("SELECT * from " + TABLE_CONTACT, null);
		resultSet.moveToFirst();

		while (resultSet.moveToNext()) {
			Contact contact = new Contact();
			contact.setEmail(resultSet.getString(resultSet
					.getColumnIndex(FIELD_EMAIL)));
			contact.setName(resultSet.getString(resultSet
					.getColumnIndex(FIELD_NAME)));
			contact.setPhone(resultSet.getString(resultSet
					.getColumnIndex(FIELD_PHONE)));
			contact.setPlace(resultSet.getString(resultSet
					.getColumnIndex(FIELD_PLACE)));
			contact.setStreet(resultSet.getString(resultSet
					.getColumnIndex(FIELD_STREET)));
			contactList.add(contact);
		}
		return contactList;
	}
}
