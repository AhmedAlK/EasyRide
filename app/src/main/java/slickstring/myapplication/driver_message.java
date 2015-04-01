package slickstring.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import D5.Control;


public class driver_message extends Activity {

    public final static String controller_key = "slickstring.myapplication.controller";
    public final static String message_key = "slickstring.myapplication.message";
    public static Control controller;
    public static String otherUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_message);
        controller = (Control) getIntent().getSerializableExtra(controller_key);
        otherUser = getIntent().getStringExtra(message_key);
        refreshConversation();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Riderequested(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(otherUser + " has requested a ride");
        alertDialog.setButton("cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                controller.cancelInvite(otherUser);
            }
        });
        alertDialog.setButton("accept",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                controller.acceptInvite(otherUser);
            }
        });
        alertDialog.show();
    }

    public void sendMessage(View view){
        EditText editText = (EditText) findViewById(R.id.messageText);
        controller.driverSend(editText.getText().toString(),otherUser);
        editText.setText("");
        refreshConversation();
    }

    public void refreshConversation(){
        ((TextView) findViewById(R.id.conversationView)).setText(controller.printConvo(otherUser, controller.getUserName()));
    }
}
