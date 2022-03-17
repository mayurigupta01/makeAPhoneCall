package com.example.makeaphonecall;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.makeaphonecall.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    EditText url, phone;
    Button launch, ring;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("onCreate", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void closeapp(View view){
        finish();
        System.exit(0);
    }

    public void onClickOpenUrl(View view) {
        launch = (Button) findViewById(R.id.button);
        Log.e("MainActivity", "Open the url");
        url = (EditText) findViewById(R.id.file1);
        String openUrl =  url.getText().toString();
        System.out.println(openUrl);
        if (!openUrl.startsWith("https://") && !openUrl.startsWith("http://")){
            openUrl = "http://" + openUrl;
        }
            Log.e("open url", "opening url now");
            Uri uri = Uri.parse(openUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

                startActivity(intent);
                Log.e("open URL", "opened the  URL");
    }


    public void onClickMakePhone(View view) {
        ring = (Button) findViewById(R.id.startdownload);
        Log.e("MainActivity", "make a phone call");
        phone = (EditText) findViewById(R.id.file2);
        String ringNumber = "tel:" + phone.getText().toString();
        System.out.println(ringNumber);
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
            Log.e("OnCall", "make a call");
            Intent phoneIntent = new Intent(Intent.ACTION_CALL);
            phoneIntent.setData(Uri.parse(ringNumber));
            try {
                startActivity(phoneIntent);
                Log.e("OnCall", "call is made");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "call failed, please try again later'", Toast.LENGTH_SHORT).show();
        }
    }
}



