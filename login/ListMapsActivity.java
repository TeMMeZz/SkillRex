package com.stuhawe.geigertim.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.lucasurbas.listitemview.ListItemView;

public class ListMapsActivity extends AppCompatActivity{
    ListItemView lstMulti;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_show_inserate);



    }

    public void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("This is a Bottom Toolbar");

        setSupportActionBar(toolbar);

        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });*/
    }

    public void returnToMain(){
        Intent intent = new Intent(this, ListMapsActivity.class);
        startActivity(intent);
    }


}

