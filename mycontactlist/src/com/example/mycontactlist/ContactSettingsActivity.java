package com.example.mycontactlist;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.os.Build;

public class ContactSettingsActivity extends ActionBarActivity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_settings);
		initListButton();
        initMapButton();        
        initSetttingsButton();
        initSortByClick();
        initSortOrderClick();
        initBackgroundColor();
        initSettings();
        View s = (View) findViewById(R.id.page);
        RadioButton rbRed = (RadioButton) findViewById(R.id.radioRed); 
    	if(rbRed.isChecked())
    	{
    		s.setBackgroundResource(R.color.red);	
    	}
    	else
    	{
    		s.setBackgroundResource(R.color.blue);
    	}
        
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
    private void initListButton(){
    	ImageButton list=(ImageButton) findViewById(R.id.imageButtonList);
    	list.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View V){
    			Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
    			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    		}
		});
    }
    private void initMapButton(){
    	ImageButton list=(ImageButton) findViewById(R.id.imageButtonMap);
    	list.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View V){
    			Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
    			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    		}
		});
    }
    private void initSetttingsButton(){
    	ImageButton list=(ImageButton) findViewById(R.id.imageButtonSettings);
    	list.setEnabled(false);
    }
    private void initSettings() {
    	String sortBy = getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE ).getString("sortfield","contactname"); //1
    	String sortOrder = getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE ).getString("sortorder" ,"asc");
    	String pickColor = getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE ).getString("backcolor" ,"red");
    	
    	RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
    	RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
    	RadioButton rbBirthDay = (RadioButton) findViewById(R.id.radioBirthday);
    	
    	if (sortBy.equalsIgnoreCase( "contactname" )) { 
    	rbName.setChecked( true );
    	}
    	else if (sortBy.equalsIgnoreCase( "city" )) {
    	rbCity.setChecked( true );
    	}
    	else {
    	rbBirthDay.setChecked( true );
    	}
    	
    	RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending); //4
    	RadioButton rbDescending = (RadioButton) findViewById(R.id.radioDescending);
    	
    	if (sortOrder.equalsIgnoreCase( "asc" )) {
    	rbAscending.setChecked( true );
    	}
    	else {
    	rbDescending.setChecked( true );
    	}
    	
    	RadioButton rbRed = (RadioButton) findViewById(R.id.radioRed); 
    	RadioButton rbBlue = (RadioButton) findViewById(R.id.radioBlue);
    	
    	if (pickColor.equalsIgnoreCase( "red" ))
    	{
    		rbRed.setChecked(true);
    	}
    	else
    	{
    		rbBlue.setChecked(true);
    	}
    	
    }
    	
   
    private void initSortByClick() {
    	RadioGroup rgSortBy = (RadioGroup) findViewById(R.id.radioGroup1 );
    	rgSortBy.setOnCheckedChangeListener( new OnCheckedChangeListener() {
    	@Override
    	public void onCheckedChanged(RadioGroup arg0, int arg1) {
    	RadioButton rbName = (RadioButton) findViewById(R.id.radioName );
    	RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity );
    	if (rbName.isChecked()) {
    	getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE ).edit()
    	.putString( "sortfield" , "contactname" ).commit();
    	}
    	else if (rbCity.isChecked()) {
    	getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE ).edit()
    	.putString( "sortfield" , "city" ).commit();
    	}
    	else {
    	getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE ).edit()
    	.putString( "sortfield" , "birthday" ).commit();
    	}
    	}
    	});
    }
    	

    private void initSortOrderClick() {
    	RadioGroup rgSortOrder = (RadioGroup) findViewById(R.id.radioGroup2 );
    	rgSortOrder.setOnCheckedChangeListener( new OnCheckedChangeListener() {
    	@Override
    	public void onCheckedChanged(RadioGroup arg0, int arg1) {
    	RadioButton rbAscending = (RadioButton)findViewById(R.id.radioAscending );
    	
    	if (rbAscending.isChecked()) {    	
    		getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE ).edit()
    	.putString( "sortorder" , "asc" ).commit();
    	}
    	else 
    	{
    	getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE ).edit()
    	.putString( "sortorder" , "desc" ).commit();
    	}
    	}
    	});
    }
    private void initBackgroundColor() {
    	RadioGroup rgSortOrder = (RadioGroup) findViewById(R.id.radioGroup3 );
    	
    	rgSortOrder.setOnCheckedChangeListener( new OnCheckedChangeListener() {
    	@Override
    	public void onCheckedChanged(RadioGroup arg0, int arg1) {RadioButton rbRed = (RadioButton)
    	findViewById(R.id.radioRed );
    	if (rbRed.isChecked()) {
    	getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE ).edit()
    	.putString( "backcolor" , "red" ).commit();
    	}
    	else {
    	getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE ).edit()
    	.putString( "backcolor" , "blue" ).commit();
    	}
    	}
    	});
    }
    
}


    	


