package slickstring.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import D5.Control;

import static android.os.SystemClock.sleep;


public class waiting extends Activity {

    public final static String controller_key = "slickstring.myapplication.controller";
    public final static String message_key = "slickstring.myapplication.message";
    public static Control controller;
    public static String[] messages;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        controller = (Control) getIntent().getSerializableExtra(controller_key);
        messages = new String[0];

        //set up the automated refresh
        handler.postDelayed(refresh,1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_role_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_profile:
                openProfile();
        }

        return super.onOptionsItemSelected(item);
    }

    public void openProfile(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(controller_key, controller);

        Intent intent = new Intent(this, edit_profile.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public final Runnable refresh = new Runnable(){
        @Override
        public void run() {
            String[] passengers = controller.checkDriverMessages();
            if (passengers.length > messages.length) {
                for (int i = messages.length; i < passengers.length; i++) {
                    messagesButtonCreator(passengers[i]);
                }
                messages = passengers.clone();
                handler.postDelayed(refresh,5000);
            }
        }
    };

    public void messagesButtonCreator(final String passengerName){
        if (passengerName == null){
            return;
        }

        ((TextView) findViewById(R.id.waitingView)).setText("");
        final LinearLayout layout = (LinearLayout) findViewById(R.id.messageListLayout);
        final Button button = new Button(this);
        button.setText(passengerName);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Bundle bundle = new Bundle();
                bundle.putSerializable(controller_key, controller);
                bundle.putString(message_key,passengerName);

                Intent intent = new Intent(layout.getContext(), driver_message.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        layout.addView(button);
    }
}
