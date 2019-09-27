package com.stuhawe.geigertim.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView arrowUp,arrowDown;
    private ImageView arrowUpImg,arrowDownImg;

    private String text;
    private Integer integer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        arrowUp = (TextView) findViewById(R.id.upvote);
        arrowDown = (TextView) findViewById(R.id.downvote);
        arrowUpImg = (ImageView) findViewById(R.id.upvote_img);
        arrowDownImg = (ImageView) findViewById(R.id.downvote_img);

        //on Click Listener
        arrowUp.setOnClickListener(this);
        arrowUpImg.setOnClickListener(this);
        arrowDown.setOnClickListener(this);
        arrowDownImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upvote:
            case R.id.upvote_img:
                text =  arrowUp.getText().toString();
                integer = Integer.parseInt(text)+1;
                arrowUp.setText(Integer.toString(integer));
                break;
            case R.id.downvote:
            case R.id.downvote_img:
                text =  arrowDown.getText().toString();
                integer = Integer.parseInt(text)+1;
                arrowDown.setText(Integer.toString(integer));
                break;
        }
    }
}
