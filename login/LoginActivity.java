package com.stuhawe.geigertim.login;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import gr.net.maroulis.library.EasySplashScreen;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private boolean tutorialmode = true;

    Dialog myDialog;

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!tutorialmode) {
            setContentView(R.layout.activity_login);


        ButterKnife.inject(this);

        //dialog
        myDialog = new Dialog(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
        }else{
            setContentView(R.layout.tutorialmode);
            Intent i = new Intent(this,TestActivity.class);
            startActivity(i);
        }


    }

    //popup
    public void showpopup(View v){
        TextView btnweiter;
        myDialog.setContentView(R.layout.popup1);
        btnweiter=(TextView) myDialog.findViewById(R.id.btn_weiter);
        btnweiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView btnlsogehts;
                myDialog.setContentView(R.layout.popup2);
                myDialog.show();
                btnlsogehts=(TextView) myDialog.findViewById(R.id.btn_losgehts);
                btnlsogehts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login();
                    }
                });
            }
        });
        myDialog.show();
    }
    //popup Ende


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                //,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 2000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        startActivity(new Intent(LoginActivity.this, MapsActivity.class));
        //startActivity(new Intent(LoginActivity.this, TableViewActivity.class));
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 ) {
            _passwordText.setError("atleast 4 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


    public void splashscreenblack(View v){
        EasySplashScreen config = new EasySplashScreen(LoginActivity.this)
                .withFullScreen()
                .withTargetActivity(LoginActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#000000"))
                .withLogo(R.drawable.dinosaur_green_logo)
                .withAfterLogoText("kurze Arme, große Ideen");

        config.getAfterLogoTextView().setTextColor(Color.parseColor("#346426"));

        View view = config.create();

        setContentView(view);
    }

    public void splashscreenwhite(View v){
        EasySplashScreen config = new EasySplashScreen(LoginActivity.this)
                .withFullScreen()
                .withTargetActivity(LoginActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#FFFFFF"))
                .withLogo(R.drawable.dinosaur_green_logo)
                .withAfterLogoText("kurze Arme, große Ideen");

        config.getAfterLogoTextView().setTextColor(Color.parseColor("#346426"));

        View view = config.create();

        setContentView(view);
    }
}
