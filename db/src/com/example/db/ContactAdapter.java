package com.example.db;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {
	Activity mActivity;
	ArrayList<Contact> mContacts = new ArrayList<Contact>();
	LayoutInflater mInflater;
	private char preLetter;
	private char currLetter;
	private char currentChar;
	private boolean[] separatorAtIndex;

	public ContactAdapter() {
		// TODO Auto-generated constructor stub
	}

	public ContactAdapter(Activity activity, ArrayList<Contact> contacts) {
		mActivity = activity;
		mContacts = contacts;
		mInflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		assignSeparatorPositions(contacts);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mContacts.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mContacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Contact contact = mContacts.get(position);
		currentChar = contact.getName().charAt(0);
		if (convertView == null) {
			holder = new ViewHolder();
			if (separatorAtIndex[position]) {
				convertView = mInflater.inflate(
						R.layout.view_conatct_header_item, null);

			} else {
				convertView = mInflater.inflate(R.layout.view_conatct_item,
						null);

			}
			holder = new ViewHolder();
			holder.layout = (LinearLayout) convertView
					.findViewById(R.id.layout);
			holder.phone = (TextView) convertView.findViewById(R.id.phone);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (separatorAtIndex[position]) {
			holder.layout.setBackgroundColor(Color.DKGRAY);
			holder.name.setText(contact.getName().charAt(0) + "");
			holder.phone.setVisibility(View.GONE);

		} else {
			holder.layout.setBackgroundColor(Color.WHITE);
			holder.phone.setVisibility(View.VISIBLE);
			holder.name.setText(contact.getName());
			holder.phone.setText(contact.getPhone());

		}
		return convertView;
	}

	private class ViewHolder {
		TextView name;
		TextView phone;
		LinearLayout layout;
	}

	/**
	 * Method takes the alphabetically sorted ArrayList as argument
	 */
	private void assignSeparatorPositions(ArrayList<Contact> items) {
		separatorAtIndex = new boolean[items.size()];
		char currentChar = 0;

		for (int i = 0; i < items.size(); i++) {
			if (mContacts.get(i).getName().charAt(0) != currentChar) {
				separatorAtIndex[i] = true;
			} else {
				separatorAtIndex[i] = false;
			}
			currentChar = items.get(i).getName().charAt(0);
		}
		for (int i = 0; i < separatorAtIndex.length; i++) {
			Log.d("IS", separatorAtIndex[i] + "");
		}
	}
}
