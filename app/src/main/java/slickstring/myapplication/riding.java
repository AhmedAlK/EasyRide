package slickstring.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
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


public class riding extends Activity {

    public final static String message_key = "slickstring.myapplication.message";
    public Control controller = login.controller;
    public String otherUser;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riding);
        otherUser = getIntent().getStringExtra(message_key);
        ((TextView) findViewById(R.id.conversationView)).setText(controller.printConvo(otherUser,controller.getUserName()));

        //set up the automated refresh
        handler.postDelayed(refresh, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_riding, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_end:
                endRide();
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view){
        EditText editText = (EditText) findViewById(R.id.messageText);
        controller.passengerSend(otherUser,editText.getText().toString());

        editText.setText("");
        refreshConversation();
    }

    private void endRide() {
        controller.endRide(otherUser);

        Intent intent = new Intent(this, rate.class);
        intent.putExtra(message_key,otherUser);
        startActivity(intent);

        finish();
    }

    public void refreshConversation(){
        String serverConvo = controller.printConvo(otherUser, controller.getUserName());
        String clientConvo = (String) ((TextView) findViewById(R.id.conversationView)).getText();
        if (serverConvo != clientConvo) {
            ((TextView) findViewById(R.id.conversationView)).setText(serverConvo);
        }
    }

    public final Runnable refresh = new Runnable() {
        @Override
        public void run() {
            refreshConversation();
            handler.postDelayed(refresh, 1000);
        }
    };

    @Override
    public void onBackPressed() {
    }
}
