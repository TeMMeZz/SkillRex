package com.stuhawe.geigertim.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

/**
 * Created by geiger.tim on 22.01.2018.
 */

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView karte,profil,erstellen,anschauen,einstellungen;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        karte = (CardView) findViewById(R.id.Karte);
        profil = (CardView) findViewById(R.id.profil);
        erstellen = (CardView) findViewById(R.id.erstellen);
        anschauen = (CardView) findViewById(R.id.anschauen);
        einstellungen = (CardView) findViewById(R.id.einstellungen);

        karte.setOnClickListener(this);
        profil.setOnClickListener(this);
        erstellen.setOnClickListener(this);
        anschauen.setOnClickListener(this);
        einstellungen.setOnClickListener(this);

    }

    public void onClick(View v){
        Intent i;

        switch (v.getId()){
            case R.id.Karte: i = new Intent(this,MapsActivity.class);startActivity(i);break;
            case R.id.profil: i = new Intent(this,ProfileActivity.class);startActivity(i);break;
            case R.id.erstellen: i = new Intent(this,MapsActivity.class);startActivity(i);break;
            case R.id.anschauen: i = new Intent(this,ListMapsActivity.class);startActivity(i);break;
            case R.id.einstellungen: i = new Intent(this,ProfileActivity.class);startActivity(i);break;
            default:break;
        }
    }


    public void change(Class myclass){
        Intent activity = new Intent(MenuActivity.this, myclass);
        startActivity(activity);
    }

}
