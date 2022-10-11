package com.example.login_application;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

 class MainActivity extends AppCompatActivity {
     ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        
        Button changelang =findViewById(R.id.button2);
        changelang.setOnClickListener(view -> showChangeLanguageDialog());
    }

    private void showChangeLanguageDialog()
    {
final String[] listItems = {"English", "Deutsch", "हिन्दी", "தமிழ்" };
        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("choose lang");
        builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0)
                {
                    setLocale("ENGLISH");
                    recreate();
                }



                 else if(i== 1)
                {
                    setLocale("GERMAN");
                    recreate();
                }
               else if(i==2)
                {
                    setLocale("HINDI");
                    recreate();
                }
                else if(i==3)
                {
                    setLocale("TAMIL");
                    recreate();
                }
                
                dialogInterface.dismiss();
            }
        });
        AlertDialog aDialog= builder.create();
        aDialog.show();
    }
    private void setLocale(String lang)
    {
        Locale locale= new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor= getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("MYLANG", lang);
        editor.apply();
        
    }
    
    public void loadLocale()
    {
    SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
    String language = preferences.getString("MYLANG", "");
    setLocale(language);
    }
}