package slickstring.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import D5.Control;


public class role_select extends Activity {

    public final static String controller_key = "slickstring.myapplication.controller";
    public Control controller = login.controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_select);
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.logoutButton();
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

            switch (item.getItemId()) {
                case R.id.action_profile:
                    openProfile();
            }

            return super.onOptionsItemSelected(item);
        }

    public void openProfile(){
        Intent intent = new Intent(this, my_profile.class);
        startActivity(intent);
    }

    public void amDriver(View view){
        controller.driverButton();

        Intent intent = new Intent(this, waiting.class);
        startActivity(intent);
    }

    public void amPassenger(View view){
        controller.passengerButton();

        Intent intent = new Intent(this, list_drivers.class);
        startActivity(intent);
    }
}
