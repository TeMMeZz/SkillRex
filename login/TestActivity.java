package com.stuhawe.geigertim.login;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity{

    public Button karte;
    public Button menü;
    public Button AngebotErstellenBtn;
    public Button AngeboteAnsehenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorialmode);
        Toast toast= Toast.makeText(getApplicationContext(), "making toast", Toast.LENGTH_SHORT);
        toast.show();


        karte = (Button) findViewById(R.id.Testkartending);
        menü = (Button) findViewById(R.id.Testmenüding);
        AngebotErstellenBtn = (Button) findViewById(R.id.AngebotErstellen);
        AngeboteAnsehenBtn = (Button) findViewById(R.id.AngeboteAnsehen);

        karte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT);
                toast.show();
                change(MapsActivity.class);
            }
        });

        menü.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT);
                toast.show();
                change(d_or_v_Activity.class);
                //setContentView(R.layout.activity_choose_d_or_v);
            }
        });
        AngebotErstellenBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Angebot erstellen Maske", Toast.LENGTH_SHORT);
                toast.show();
                change(TestInserierenActivity.class);
                //WebView webView = new WebView(TestActivity.this);
                //webView.loadUrl("http://skillrex.de/appdatabase/erstelleAngebote.php?ID=1&ersteller=27&objekt=penis&kategorie=1&date=2018-10-15%2020:20:20&abholzeit=2018-10-15%2020:20:20&abgabezeit=2018-10-15%2020:20:20&lat=0.0&lng=0.0");
            }
        });

        AngeboteAnsehenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"Zeige alle Angebote", Toast.LENGTH_SHORT);
                change(TestShowInserate.class);
            }
        });

    }

    private void change(Class thingindabobble) {
        startActivity(new Intent(this, thingindabobble));
    }


}