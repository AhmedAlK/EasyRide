package slickstring.myapplication;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import D5.Control;


public class rate extends Activity {

    public final static String message_key = "slickstring.myapplication.message";
    public Control controller = login.controller;
    public static String otherUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        otherUser = getIntent().getStringExtra(message_key);
        ((TextView) findViewById(R.id.textView2)).setText("How would you rate " + otherUser + "?");
    }

    public void rated(View view){
        controller.findUser(otherUser).addRating(((RatingBar) findViewById(R.id.ratingBar2)).getRating());
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rate, menu);
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
