package com.example.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends Activity implements OnClickListener {
	private Button btnSave;
	private EditText editTextName;
	private EditText editTextEmail;
	private EditText editTextPhone;
	private EditText editTextStreet;
	private EditText editTextPlace;
	DBHelper db;
	private Button btnShowList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		init();
		setListeners();
	}

	private void setListeners() {
		btnSave.setOnClickListener(this);
		btnShowList.setOnClickListener(this);
	}

	private void init() {
		db = new DBHelper(this);
		btnSave = (Button) findViewById(R.id.save);
		btnShowList = (Button) findViewById(R.id.showList);
		editTextName = (EditText) findViewById(R.id.editTextName);
		editTextPhone = (EditText) findViewById(R.id.editTextPhone);
		editTextEmail = (EditText) findViewById(R.id.editTextEmail);
		editTextStreet = (EditText) findViewById(R.id.editTextStreet);
		editTextPlace = (EditText) findViewById(R.id.editTextCity);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.save:
			insetContact();
			break;
		case R.id.showList:
			Intent intent = new Intent(this, ShowContactListActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	private void insetContact() {
		// TODO Auto-generated method stub
		Contact contact = new Contact();
		contact.setEmail(editTextEmail.getText().toString().trim());
		contact.setName(editTextName.getText().toString().trim());
		contact.setPhone(editTextPhone.getText().toString().trim());
		contact.setPlace(editTextPlace.getText().toString().trim());
		contact.setStreet(editTextStreet.getText().toString().trim());
		boolean isSuccess = db.insertContact(contact);
		if (isSuccess) {
			Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
			// emptyFields();
		} else {
			Toast.makeText(this, "Contact Not Added", Toast.LENGTH_SHORT)
					.show();
		}
	}

	private void emptyFields() {
		editTextEmail.setText("");
		editTextName.setText("");
		editTextPhone.setText("");
		editTextPlace.setText("");
		editTextStreet.setText("");
	}
}
