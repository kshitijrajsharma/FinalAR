package com.wrc.geo.ar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    Dialog myDialog;
    ImageView bgapp, clover,imageView;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom,fromleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bgapp = (ImageView) findViewById(R.id.bgapp);
        imageView=(ImageView) findViewById(R.id.imageView);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);
        menus.setVisibility(View.GONE);
        texthome.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);


        bgapp.animate().translationY(-1400).setDuration(2100).setStartDelay(2100);
        clover.animate().alpha(0).setDuration(1000).setStartDelay(2000);
        textsplash.animate().translationY(500).alpha(0).setDuration(2000).setStartDelay(1500);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromleft=AnimationUtils.loadAnimation(this,R.anim.fromleft);
        clover.startAnimation(fromleft);
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                menus.setVisibility(View.VISIBLE);
                texthome.setVisibility(View.VISIBLE);
                texthome.startAnimation(frombottom);
                menus.startAnimation(frombottom);
            }
        }, 2000);
        Handler handler1= new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(View.VISIBLE);
                imageView.startAnimation(fromleft);
            }
        }, 6000);
        myDialog = new Dialog(this);


    }

    public void outdoor                                                                                                                                                                                      (View view)
        {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("krs.ar.outar");
            if (launchIntent != null) {
                super.startActivity(launchIntent);//null pointer check in case package name was not found
            }
        }
    public void indoor                                                                                                                                                                                      (View view)
    {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.ar.indoor");
        if (launchIntent != null) {
            super.startActivity(launchIntent);//null pointer check in case package name was not found
        }
    }
    public void ShowPopup(View v) {
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    }
