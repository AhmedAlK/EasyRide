
package slickstring.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import D5.Control;


public class create_bio extends Activity {

    public final static String controller_key = "slickstring.myapplication.controller";
    public Control controller = login.controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bio);
        ((TextView) findViewById(R.id.userName)).setText(controller.getUserName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_bio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_next:
                next();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void next(){
        String newBio = ((EditText) findViewById(R.id.BioField)).getText().toString();
        controller.firstNextButton(newBio);


        Intent intent = new Intent(this, role_select.class);
        startActivity(intent);
    }
}
