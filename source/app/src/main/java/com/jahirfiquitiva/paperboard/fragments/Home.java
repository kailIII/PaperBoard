package com.jahirfiquitiva.paperboard.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jahirfiquitiva.paperboard.activities.Main;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;

import java.util.Observable;

import com.jahirfiquitiva.dashboardsample.R;

/**
 * Created by Jahir on 28/02/2015.
 */
public class Home extends Fragment {

    private Context context;
    private FloatingActionButton fab;
    private static final String MARKET_URL = "https://play.google.com/store/apps/details?id=";

    private String PlayStoreDevAccount, PlayStoreListing, AppOnePackage, AppTwoPackage, AppThreePackage;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.section_home, null);

        context = getActivity();

        PlayStoreDevAccount = getResources().getString(R.string.play_store_dev_link);
        PlayStoreListing = context.getPackageName();
        AppOnePackage = getResources().getString(R.string.app_one_package);
        AppTwoPackage = getResources().getString(R.string.app_two_package);
        AppThreePackage = getResources().getString(R.string.app_three_package);

        ActionBar toolbar = ((ActionBarActivity)context).getSupportActionBar();
        toolbar.setTitle(R.string.app_name);
        toolbar.setElevation(getResources().getDimension(R.dimen.toolbar_elevation));

        ObservableScrollView content = (ObservableScrollView) root.findViewById(R.id.HomeContent);

        //Cards
        CardView cardone = (CardView) root.findViewById(R.id.cardOne);
        CardView cardtwo = (CardView) root.findViewById(R.id.cardTwo);
        CardView cardthree = (CardView) root.findViewById(R.id.cardThree);
        if (AppIsInstalled(AppOnePackage)){
            cardone.setVisibility((cardone.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));
        }
        if (AppIsInstalled(AppTwoPackage)){
            cardtwo.setVisibility((cardtwo.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));
        }
        if (AppIsInstalled(AppThreePackage)) {
            cardthree.setVisibility((cardthree.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));
        }


        TextView playbtn = (TextView) root.findViewById(R.id.play_button);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devPlay = new Intent(Intent.ACTION_VIEW, Uri.parse(PlayStoreDevAccount));
                startActivity(devPlay);
            }
        });

        TextView apponebtn = (TextView) root.findViewById(R.id.appone_button);
        apponebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appone = new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_URL + AppOnePackage));
                startActivity(appone);
            }
        });

        TextView apptwobtn = (TextView) root.findViewById(R.id.apptwo_button);
        apptwobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent apptwo = new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_URL + AppTwoPackage));
                startActivity(apptwo);
            }
        });

        TextView appthreebtn = (TextView) root.findViewById(R.id.appthree_button);
        appthreebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appthree = new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_URL + AppThreePackage));
                startActivity(appthree);
            }
        });

        TextView ratebtn = (TextView) root.findViewById(R.id.rate_button);
        ratebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rate = new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_URL + PlayStoreListing));
                startActivity(rate);
            }
        });

        fab = (FloatingActionButton) root.findViewById(R.id.apply_btn);
        fab.setColorNormal(getResources().getColor(R.color.accent));
        fab.setColorPressed(getResources().getColor(R.color.accent_pressed));
        fab.setColorRipple(getResources().getColor(R.color.semitransparent_white));
        fab.show(true);
        fab.attachToScrollView(content);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main)getActivity()).result.setSelectionByIdentifier(3);
                ((Main)getActivity()).switchFragment(3, getResources().getString(R.string.section_three), "Apply");
            }
        });

        return root;
    }

    private boolean AppIsInstalled(String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

}
