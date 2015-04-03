package slickstring.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import D5.Control;


public class driver_message extends Activity {

    public final static String message_key = "slickstring.myapplication.message";
    public Control controller = login.controller;
    public String otherUser;
    Handler handler = new Handler();
    AlertDialog alertDialog;
    boolean requestSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_message);
        otherUser = getIntent().getStringExtra(message_key);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setAlert();
        //set up the automated refresh
        handler.postDelayed(refresh, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_messages, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_otherUser:
                viewProfile();
        }
        return super.onOptionsItemSelected(item);
    }

    public void viewProfile(){
        Bundle bundle = new Bundle();
        bundle.putString(message_key, otherUser);

        Intent intent = new Intent(this, view_profile.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void setAlert(){
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(otherUser + " has requested a ride");
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                controller.cancelInvite(otherUser);
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"accept",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                controller.acceptInvite(otherUser);
                rideAccepted();
            }
        });
    }
    public void sendMessage(View view){
        EditText editText = (EditText) findViewById(R.id.messageText);
        controller.driverSend(editText.getText().toString(),otherUser);
        editText.setText("");
        refreshConversation();
    }

    public void rideRequested(){
        alertDialog.show();
        requestSent = true;
    }

    private void rideAccepted() {
        alertDialog.dismiss();
        requestSent=false;
        handler.removeCallbacks(null);
        Intent intent = new Intent(this, riding.class);
        intent.putExtra(message_key, otherUser);
        startActivity(intent);
    }

    public void refreshConversation(){
        String serverConvo = controller.printConvo(otherUser, controller.getUserName());
        String clientConvo = (String) ((TextView) findViewById(R.id.conversationView)).getText();
        if (!serverConvo.equals(clientConvo)) {
            ((TextView) findViewById(R.id.conversationView)).setText(serverConvo);
        }
    }

    public final Runnable refresh = new Runnable(){
        @Override
        public void run() {
            refreshConversation();

            if (controller.checkInRideWith(otherUser)){
                rideAccepted();
            }
            else if (controller.checkForInvites(otherUser) && !requestSent){
                rideRequested();
            }
            else if (!controller.checkForInvites(otherUser) && requestSent){
                requestSent = false;
                alertDialog.dismiss();
            }
            else{
                handler.postDelayed(refresh,1000);
            }
        }
    };
}