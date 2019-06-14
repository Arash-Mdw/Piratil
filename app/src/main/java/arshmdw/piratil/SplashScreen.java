package arshmdw.piratil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import arshmdw.piratil.PhoneNumber;
import arshmdw.piratil.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {
                //TODO
                SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                Boolean accessNotGranted  = sharedPreferences.getBoolean("signed", false);
                Boolean allreadySigned = sharedPreferences.getBoolean("allreadySigned", true);
                if (accessNotGranted) {
                    startActivity(new Intent(SplashScreen.this, PhoneNumber.class));

                }
                if (allreadySigned) {

                }
                else {
                    startActivity(new Intent(SplashScreen.this, aboutus.class));
                }
            }
        },3000);
    }
}
