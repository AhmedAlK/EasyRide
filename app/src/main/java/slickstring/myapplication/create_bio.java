
package slickstring.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class create_bio extends Activity {

    String myUserName = "uninitialized";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bio);
        myUserName = getIntent().getStringExtra(login.email);
        ((TextView) findViewById(R.id.userName)).setText(myUserName);
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
                saveProfile();
                next();
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveProfile(){
//            startActivity( new Intent(this, create_bio.class));
    }

    public void next(){
        Intent intent = new Intent(this, role_select.class);
        startActivity(intent);
    }
}
