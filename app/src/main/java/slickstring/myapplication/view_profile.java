package slickstring.myapplication;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import D5.Control;


public class view_profile extends Activity {

    public final static String controller_key = "slickstring.myapplication.controller";
    public final static String message_key = "slickstring.myapplication.message";
    public static Control controller;
    public static String otherUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        controller = (Control) getIntent().getSerializableExtra(controller_key);
        otherUser = getIntent().getStringExtra(message_key);


        ((TextView) findViewById(R.id.BioField)).setText(controller.getBio());
        ((TextView) findViewById(R.id.BioField)).setText(controller.getBio());
        ((RatingBar) findViewById(R.id.ratingBar)).setRating((float) controller.getRating());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_profile, menu);
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
}
