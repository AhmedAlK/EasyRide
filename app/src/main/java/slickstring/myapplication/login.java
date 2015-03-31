package slickstring.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import D5.*;


public class login extends Activity {

    public final static String controller_key = "slickstring.myapplication.controller";
    public static Control controller = new Control();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void verifyLogin(View view){
        EditText email = (EditText) findViewById(R.id.emailInput);
        EditText password = (EditText) findViewById(R.id.passwordInput);
        controller.loginButton(email.getText().toString(),password.getText().toString());


        Bundle bundle = new Bundle();
        bundle.putSerializable(controller_key, controller);

        Intent intent = new Intent(this, create_bio.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
