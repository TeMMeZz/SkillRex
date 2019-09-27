package com.stuhawe.geigertim.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(LoginActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#000000"))
                .withLogo(R.drawable.dinosaur_white_logo)
                .withHeaderText("SkillRex App")
                .withFooterText("Copyright by TeMMeZz 2018")
                .withBeforeLogoText("Loading...")
                .withAfterLogoText("kurze Arme, große Ideen");

        config.getHeaderTextView().setTextColor(Color.BLACK);
        config.getFooterTextView().setTextColor(Color.BLACK);
        config.getAfterLogoTextView().setTextColor(Color.parseColor("#346426"));
        config.getBeforeLogoTextView().setTextColor(Color.BLACK);

        View view = config.create();

        setContentView(view);
    }
}
