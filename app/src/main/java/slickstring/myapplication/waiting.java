package slickstring.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        controller = (Control) getIntent().getSerializableExtra(controller_key);
        autoRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_waiting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    public void autoRefresh(){
        int currentMessages = 0;
        String[] passengers;
        while(true){
            sleep(5000);
            passengers = controller.getPassengers();
            if (passengers.length > currentMessages){
                for (int i = currentMessages; i < passengers.length;i++){
                    messagesButtonCreator(passengers[i]);
                }
                currentMessages = passengers.length;
            }
        }
    }

    public void messagesButtonCreator(final String passengerName){
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
