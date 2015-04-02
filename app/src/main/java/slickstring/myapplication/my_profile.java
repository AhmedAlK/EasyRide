package slickstring.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import D5.Control;


public class my_profile extends Activity {

    public Control controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller = login.controller;
        ((TextView) findViewById(R.id.userName)).setText(controller.getUserName());
        ((TextView) findViewById(R.id.BioField)).setText(controller.getBio());
        ((RatingBar) findViewById(R.id.ratingBar)).setRating((float) controller.getRating());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_edit:
                editProfile();
        }

        return super.onOptionsItemSelected(item);
    }

    public void editProfile(){
        Bundle bundle = new Bundle();

        Intent intent = new Intent(this, edit_profile.class);
       intent.putExtras(bundle);
        startActivity(intent);
    }
}
