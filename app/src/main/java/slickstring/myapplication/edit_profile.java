package slickstring.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import D5.Control;


public class edit_profile extends Activity {

    public Control controller = login.controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView) findViewById(R.id.userName)).setText(controller.getUserName());
        ((TextView) findViewById(R.id.BioField)).setText(controller.getBio());
        ((RatingBar) findViewById(R.id.ratingBar)).setRating((float) controller.getRating());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_save:
                controller.editBioButton(((EditText) findViewById(R.id.BioField)).getText().toString());
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
