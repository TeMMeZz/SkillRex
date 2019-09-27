package com.stuhawe.geigertim.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestInserierenActivity extends AppCompatActivity{

    public Button inserierenBtn;
    public TextView _email;
    public TextView _object;
    public TextView _ersteller;
    public TextView _category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_inserieren);
        inserierenBtn = (Button) findViewById(R.id.inserierenBtn);
        _email = (EditText) findViewById(R.id.input_email);
        _object = (EditText) findViewById(R.id.input_objekt);
        _ersteller = (EditText) findViewById(R.id.input_ErstellerID);
        _category = (EditText) findViewById(R.id.input_Kategorie);


        inserierenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserieren();
            }
        });

    }

    public void inserieren() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        String erstellerID = _ersteller.getText().toString();
        String object = _object.getText().toString();
        String category = _category.getText().toString();
        String combinedURL = "http://skillrex.de/appdatabase/erstelleAngebote.php?ID=1&ersteller=" + erstellerID + "&objekt=" + object + "&kategorie=" + category + "&date="+timeStamp+"&abholzeit=2018-10-15%2020:20:20&abgabezeit=2018-10-15%2020:20:20&lat=47.354&lng=16.587";
        WebView webView = new WebView(TestInserierenActivity.this);
        webView.loadUrl(combinedURL);
        Toast.makeText(this, "Angebot erfolgreich erstellt", Toast.LENGTH_SHORT).show();
    }
}
