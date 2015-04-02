package slickstring.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import D5.Control;


public class login extends Activity {

    public static Control controller = new Control();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupNameListener();
    }

    //creates a TextListener to enable the login button when a name is written added
    private void setupNameListener() {
        final EditText email = (EditText) findViewById(R.id.emailInput);
        EditText password = (EditText) findViewById(R.id.passwordInput);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((TextView) findViewById(R.id.warningView)).setVisibility(View.INVISIBLE);              //make the invalid password warning disappear
                if (email.getText().toString().equals("")){
                    findViewById(R.id.loginButton).setEnabled(false);
                }
                else{
                    findViewById(R.id.loginButton).setEnabled(true);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((TextView) findViewById(R.id.warningView)).setVisibility(View.INVISIBLE);              //make the invalid password warning disappear
                if (!email.getText().toString().equals("")){
                    findViewById(R.id.loginButton).setEnabled(true);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void enableLogin() {
        if (!((EditText) findViewById(R.id.emailInput)).getText().toString().equals("") && !((EditText) findViewById(R.id.passwordInput)).getText().toString().equals("")){
            findViewById(R.id.loginButton).setEnabled(true);
        }
        else {
            findViewById(R.id.loginButton).setEnabled(false);
        }
    }

    public void verifyLogin(View view) {
        EditText email = (EditText) findViewById(R.id.emailInput);
        EditText password = (EditText) findViewById(R.id.passwordInput);
        if (controller.loginButton(email.getText().toString(), password.getText().toString())) {

            ((TextView) findViewById(R.id.warningView)).setVisibility(View.INVISIBLE);              //make the invalid password warning disappear



            Intent intent = new Intent(this, create_bio.class);

            startActivity(intent);
        } else {
            ((TextView) findViewById(R.id.warningView)).setVisibility(View.VISIBLE);                //shows the invalid password warning
            findViewById(R.id.loginButton).setEnabled(false);
        }
    }
}
