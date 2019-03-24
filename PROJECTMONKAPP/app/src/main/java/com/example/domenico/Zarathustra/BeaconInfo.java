package com.example.domenico.Zarathustra;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BeaconInfo implements BeaconConsumer {
    private static Context context;
    private static BeaconManager beaconManager;
    private static PercentagePosition nearestPercentagePosition;
    private static com.example.domenico.Zarathustra.Beacon[] beacons;

    public static void init(final AppCompatActivity context, com.example.domenico.Zarathustra.Beacon[] beacons) {
        BeaconInfo.beacons = beacons;
        BeaconInfo.context = context;
        beaconManager = BeaconManager.getInstanceForApplication(context);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(new BeaconInfo());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can detect beacons");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        context.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    }
                });
                builder.show();
            }
        }
        try {
            beaconManager.startRangingBeaconsInRegion(new Region("MyRegionId", null, null, null));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBeaconServiceConnect() {
        this.beaconManager.setRangeNotifier(new RangeNotifier () {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size () > 0) {
                    PercentagePosition percentagePosition;
                    double di = Double.MAX_VALUE;
                    String id = null;
                    for (Beacon beacon : beacons) {
                        if(beacon.getDistance () < di) {
                            di = beacon.getDistance ();
                            id = beacon.getId3 ().toString ();
                        }
                    }
                    for(com.example.domenico.Zarathustra.Beacon b : BeaconInfo.beacons) {
                        if(b.id.equals (id))  {
                            nearestPercentagePosition = b.percentagePosition;
                        }
                    }
                }
        }
        });}

    @Override
    public Context getApplicationContext() {
        return context;
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {

    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return false;
    }


    public static PercentagePosition getPosition() {
        return nearestPercentagePosition;
    }

}