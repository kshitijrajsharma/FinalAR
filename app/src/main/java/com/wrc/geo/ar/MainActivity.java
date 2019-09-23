package com.wrc.geo.ar;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class MainActivity extends Activity {
    Dialog myDialog;
    ImageView bgapp, clover,imageView;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom,fromleft;

    String URL = "https://www.mdpi.com/2078-2489/10/1/12/pdf";
    private static final String TAG = "MainActivity";
    private static final String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};


    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

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

        ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, 112);



        bgapp.animate().translationY(-1750).setDuration(4000).setStartDelay(2100);
        clover.animate().alpha(0).setDuration(1000).setStartDelay(2000);
        textsplash.animate().translationY(500).alpha(0).setDuration(4000).setStartDelay(3000);
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
        }, 7000);
        myDialog = new Dialog(this);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!hasPermissions(MainActivity.this, PERMISSIONS)) {

                    Log.v(TAG, "download() Method DON'T HAVE PERMISSIONS ");

                    Toast t = Toast.makeText(getApplicationContext(), "You don't have read access !", Toast.LENGTH_LONG);
                    t.show();
                }
                else{
                    new DownloadTask(MainActivity.this, URL);
                }

            }
        });

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
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/sharma.kshitizraj?ref=bookmarks";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
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
