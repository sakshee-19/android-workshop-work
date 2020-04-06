package io.hasura.myblog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    ProgressDialog progressDialog;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Button login, register;
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isvalidForm()) {
                    //perform login
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isvalidForm()) {
                    //perform registration
                }
            }
        });
        //initializing
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Please Wait");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private Boolean isvalidForm() {
        if (username.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Username can not be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (password.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Password can not bt empty", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void showProgressDialoge(boolean shouldShould) {
        if (shouldShould) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }

    }

    private void performLogin() {
        //make an asynck call
        new loginTask().execute(username.getText().toString(), password.getText().toString());
    }


    private void performRegistration() {

    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    class loginTask extends AsyncTask<String, Void, Boolean> {
        String mockusername = "test";
        String mockpassword = "password";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialoge(true);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            showProgressDialoge(false);
            if (aBoolean) {
                showAlert("Welcome", "You are successfully logged in");
            } else {
                showAlert("Failed", "Username/Password is incorrect");
            }
        }


        @Override
        protected Boolean doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];
            return username.contentEquals(mockusername) && password.contentEquals(mockpassword); //or false
        }
    }
}