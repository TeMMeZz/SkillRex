package com.stuhawe.geigertim.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class d_or_v_Activity extends AppCompatActivity {
    public CardView verleihBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_d_or_v);

        verleihBtn = (CardView) findViewById(R.id.verleih);

        verleihBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                setContentView(R.layout.activity_grid);
            }
        });
    }
}
