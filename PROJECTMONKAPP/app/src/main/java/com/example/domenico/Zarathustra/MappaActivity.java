package com.example.domenico.Zarathustra;

import android.content.Context;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import org.altbeacon.beacon.*;

import java.util.Timer;
import java.util.TimerTask;

public class MappaActivity extends AppCompatActivity implements View.OnClickListener {
    int size = 10;
    public Button click0,click1,click2,click3,click4,click5,click6,click7,click8;
    public Context c;
    public ImageView imageTrack ;
    public final Handler handler=new Handler ();
    Runnable repeatTask = new Runnable () {
        @Override
        public void run() {
            PercentagePosition percentagePosition = BeaconInfo.getPosition();
            if(percentagePosition != null) {
                setPos(percentagePosition.x, percentagePosition.y);
            }
            handler.postDelayed (this,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mappa_activity);
        click0 = findViewById (R.id.button0);
        click1 = findViewById (R.id.button1);
        click2 = findViewById (R.id.button2);
        click3 = findViewById (R.id.button3);
        click4 = findViewById (R.id.button4);
        click5 = findViewById (R.id.button5);
        click6 = findViewById (R.id.button6);
        click7 = findViewById (R.id.button7);
        click8 = findViewById (R.id.button8);
        click0.setOnClickListener (this);
        click1.setOnClickListener (this);
        click2.setOnClickListener (this);
        click3.setOnClickListener (this);
        click4.setOnClickListener (this);
        click5.setOnClickListener (this);
        click6.setOnClickListener (this);
        click7.setOnClickListener (this);
        click8.setOnClickListener (this);
        imageTrack = findViewById (R.id.track);

        Beacon[] beacons = {
                new Beacon ("62263", new PercentagePosition (0.05, 0.05)),
                new Beacon ("6146", new PercentagePosition (0.02, 0.22)),
                new Beacon ("6130", new PercentagePosition (0.15, 0.32)),
                new Beacon ("6124", new PercentagePosition (0.295, 0.295)),
                new Beacon ("6125",  new PercentagePosition (0.68, 0.295)),
                new Beacon ("6134", new PercentagePosition (0.295, 0.71)),
                new Beacon ("6135", new PercentagePosition (0.68, 0.71)),
                new Beacon ("64520", new PercentagePosition (0.895, 0.24)),
                new Beacon ("38748", new PercentagePosition (0.895, 0.595))
        };
        BeaconInfo.init (this, beacons);
        getSupportActionBar().hide();

        handler.postDelayed (repeatTask,1000) ;
        Footer.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        //handler.removeCallbacks (repeatTask);
    }

    public void setPos(double x, double y) {
        ConstraintLayout cl = findViewById(R.id.relativeZoom);
        ConstraintSet constraintSet = new ConstraintSet ();
        constraintSet.clone (cl);
        constraintSet.setHorizontalBias(imageTrack.getId (), (float) x);
        constraintSet.setVerticalBias(imageTrack.getId (), (float) y);
        constraintSet.applyTo (cl);

    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        if(b.getText().toString().equals("L")) {
            b.setText ("M");
        } else

            b.setText ("L");
    }

}
