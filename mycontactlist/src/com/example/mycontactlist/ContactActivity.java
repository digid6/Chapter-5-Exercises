package com.example.mycontactlist;

import com.example.mycontactlist.DatePickerDialog.SaveDateListener;

import android.support.v4.app.FragmentActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ContactActivity extends FragmentActivity implements SaveDateListener{
	private Contact currentContact;
	
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initListButton();
        initMapButton();        
        initSetttingsButton();
        initToggleButton();
        setForEditing(false);
        initChangeDateButton();
        currentContact = new Contact();
        initTextChangedEvents();
        initTextChangedEvents();
        initSaveButton();
    }


    


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact, menu);
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
    			Intent intent = new Intent(ContactActivity.this, ContactListActivity.class);
    			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    		}
			
			
		});
    }
    private void initMapButton(){
    	ImageButton list=(ImageButton) findViewById(R.id.imageButtonMap);
    	list.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View V){
    			Intent intent = new Intent(ContactActivity.this, ContactMapActivity.class);
    			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    		}
			
			
		});
    }
    private void initSetttingsButton(){
    	ImageButton list=(ImageButton) findViewById(R.id.imageButtonSettings);
    	list.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View V){
    			Intent intent = new Intent(ContactActivity.this, ContactSettingsActivity.class);
    			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    		}
			
			
		});
    }
    
    private void initToggleButton() {
    	final ToggleButton editToggle = (ToggleButton) findViewById(R.id.toggleButtonEdit); 
    	editToggle.setOnClickListener( new OnClickListener() {
    	
    		@Override
    		public void onClick(View arg0){
    	setForEditing(editToggle.isChecked()); 
    	}
    	});
    }

   private void setForEditing(boolean enabled) {
    	EditText editName = (EditText) findViewById(R.id.editName );
    	EditText editAddress = (EditText) findViewById(R.id.editAddress );
    	EditText editCity = (EditText) findViewById(R.id.editCity );
    	EditText editState = (EditText) findViewById(R.id.editState );
    	EditText editZipCode = (EditText) findViewById(R.id.editZipcode );
    	EditText editPhone = (EditText) findViewById(R.id.editHome );
    	EditText editCell = (EditText) findViewById(R.id.editCell);
    	EditText editEmail = (EditText) findViewById(R.id.editEMail );
    	Button buttonChange = (Button) findViewById(R.id.btnBirthday );
    	Button buttonSave = (Button) findViewById(R.id.buttonSave );
    	editName.setEnabled(enabled);
    	editAddress.setEnabled(enabled);
    	editCity.setEnabled(enabled);
    	editState.setEnabled(enabled);
    	editZipCode.setEnabled(enabled);
    	editPhone.setEnabled(enabled);
    	editCell.setEnabled(enabled);
    	editEmail.setEnabled(enabled);
    	buttonChange.setEnabled(enabled);
    	buttonSave.setEnabled(enabled);
    	
    	if(enabled)
    	{
    		editName.requestFocus();
    	}
    	else{
    		ScrollView s= (ScrollView) findViewById(R.id.scrollView1);
    		s.fullScroll(ScrollView.FOCUS_UP);
    		s.clearFocus();
    	}
    }
   private void hideKeyboard() {
	   InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE );
	   EditText editName = (EditText) findViewById(R.id.editName );
	   imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
	   EditText editAddress = (EditText) findViewById(R.id.editAddress );
	   imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
	   EditText editCity= (EditText) findViewById(R.id.editCity );
	   imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
	   EditText editState = (EditText) findViewById(R.id.editState );
	   imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
	   EditText editZipCode = (EditText) findViewById(R.id.editZipcode );
	   imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
	   EditText editHome = (EditText) findViewById(R.id.editHome);
	   imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
	   EditText editCell = (EditText) findViewById(R.id.editCell);
	   imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
	   EditText editEmail = (EditText) findViewById(R.id.editEMail);
	   imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
	     
	   }


@Override
public void didFinishDatePickerDialog(Time selectedTime) {
	TextView birthDay = (TextView) findViewById(R.id.textBirthday);
	birthDay.setText(DateFormat.format("MM/dd/yyyy", selectedTime.toMillis(false)).toString());
	currentContact.setBirthday(selectedTime);
	
}
private void initChangeDateButton(){
	Button changeDate =(Button) findViewById(R.id.btnBirthday);
	changeDate.setOnClickListener(new OnClickListener(){
		
		@Override
		public void onClick(View v){
			android.support.v4.app.FragmentManager  fm = getSupportFragmentManager();
			DatePickerDialog datePickerDialog = new DatePickerDialog();
			datePickerDialog.show(fm, "DatePick");
		}
	});
}

private void initSaveButton() {
	Button saveButton = (Button) findViewById(R.id. buttonSave );
	saveButton.setOnClickListener( new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			hideKeyboard();
			ContactDataSource ds = new ContactDataSource(ContactActivity. this ); //1
			ds.open(); //2
			boolean wasSuccessful = false ; //3
			if ( currentContact.getContactID()==-1) { //4
				wasSuccessful = ds.insertContact( currentContact );
				int newId =ds.getLastContactId();
				currentContact.setContactID(newId);
			}
			else {
				wasSuccessful = ds.updateContact( currentContact );
			}
			ds.close(); //5
			if (wasSuccessful) { //6
				ToggleButton editToggle = (ToggleButton)findViewById(R.id. toggleButtonEdit );
				editToggle.toggle();
				setForEditing(false);
			}
		}
});
}
private void initTextChangedEvents() {
	final EditText contactName = (EditText) findViewById(R.id.editName ); 
	contactName.addTextChangedListener( new TextWatcher() { 
	public void afterTextChanged(Editable s) { 
	currentContact .setContactName(contactName.getText().toString());
	}
	public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) { 
	// Auto-generated method stub
	}
	public void onTextChanged(CharSequence s, int start, int before, int count) { 
	// Auto-generated method stub
	}
	});
	final EditText Address = (EditText) findViewById(R.id.editAddress ); 
	Address.addTextChangedListener( new TextWatcher() {
	public void afterTextChanged(Editable s) {
	currentContact .setStreetAddress(Address.getText().toString()); 
	}
	public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) {
	// Auto-generated method stub
	}
	public void onTextChanged(CharSequence s, int start, int before,int count) {
	// Auto-generated method stub
	}
	});
	

final EditText City = (EditText) findViewById(R.id.editCity ); 
contactName.addTextChangedListener( new TextWatcher() { 
public void afterTextChanged(Editable s) { 
currentContact.setCity(City.getText().toString());
}
public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) { //5
// Auto-generated method stub
}
public void onTextChanged(CharSequence s, int start, int before, int count) { //6
// Auto-generated method stub
}
});

final EditText State = (EditText) findViewById(R.id.editState ); 
contactName.addTextChangedListener( new TextWatcher() { 
public void afterTextChanged(Editable s) { 
currentContact.setState(State.getText().toString());
}
public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) { //5
// Auto-generated method stub
}
public void onTextChanged(CharSequence s, int start, int before, int count) { //6
// Auto-generated method stub
}
});

final EditText zipCode = (EditText) findViewById(R.id.editZipcode ); 
contactName.addTextChangedListener( new TextWatcher() { 
public void afterTextChanged(Editable s) { 
currentContact.setZipCode(zipCode.getText().toString());
}
public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) { //5
// Auto-generated method stub
}
public void onTextChanged(CharSequence s, int start, int before, int count) { //6
// Auto-generated method stub
}
});
final EditText Phone = (EditText) findViewById(R.id.editHome ); 
contactName.addTextChangedListener( new TextWatcher() { 
public void afterTextChanged(Editable s) { 
currentContact.setPhoneNumber(Phone.getText().toString());
}
public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) { //5
// Auto-generated method stub
}
public void onTextChanged(CharSequence s, int start, int before, int count) { //6
// Auto-generated method stub
}

});
final EditText Cell = (EditText) findViewById(R.id.editCell ); 
contactName.addTextChangedListener( new TextWatcher() { 
public void afterTextChanged(Editable s) { 
currentContact.setCellNumber(Cell.getText().toString());
}
public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) { //5
// Auto-generated method stub
}
public void onTextChanged(CharSequence s, int start, int before, int count) { //6
// Auto-generated method stub
}
});
final EditText Email = (EditText) findViewById(R.id.editEMail ); 
contactName.addTextChangedListener( new TextWatcher() { 
public void afterTextChanged(Editable s) { 
currentContact.setEMail(Email.getText().toString());
}
public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) { //5
// Auto-generated method stub
}
public void onTextChanged(CharSequence s, int start, int before, int count) { //6
// Auto-generated method stub
}
});

Cell.addTextChangedListener( new PhoneNumberFormattingTextWatcher());
Phone.addTextChangedListener( new PhoneNumberFormattingTextWatcher());

}
}

