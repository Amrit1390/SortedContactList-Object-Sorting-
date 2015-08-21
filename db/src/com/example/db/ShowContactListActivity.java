package com.example.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class ShowContactListActivity extends Activity {
	private DBHelper db;
	private ListView list;
	ContactAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_contact_list);
		db = new DBHelper(this);
		init();
		updateUI();
	}

	private void updateUI() {
		adapter = new ContactAdapter(this, getList());
		list.setAdapter(adapter);
	}

	private void init() {
		list = (ListView) findViewById(R.id.list_contacts);
	}

	private ArrayList<Contact> getList() {
		ArrayList<Contact> arrayList = new ArrayList<Contact>();
		arrayList = db.getContact();
		for (Contact contact : arrayList) {
			Log.d("Unsorted", "Name : " + contact.getName());
		}
		if (arrayList.size() > 0) {
			Collections.sort(arrayList, new Comparator<Contact>() {
				@Override
				public int compare(final Contact object1, final Contact object2) {
					return object1.getName().compareTo(object2.getName());
				}
			});
		}
		for (Contact contact : arrayList) {
			Log.d("sorted", "Name : " + contact.getName());
		}
		return arrayList;
	}
}
