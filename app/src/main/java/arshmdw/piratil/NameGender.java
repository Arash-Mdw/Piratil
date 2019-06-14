package arshmdw.piratil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javaclasses.customToast;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NameGender extends AppCompatActivity {
    com.android.volley.RequestQueue RequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_gender);
            final RadioButton male = (RadioButton) findViewById(R.id.male);
            defineObjects();

            findViewById(R.id.btn_submitinfo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String gender;

                    if (male.isChecked()){

                        gender="1";

                    } else {

                        gender="0";

                    }

                    RequestQueue = Volley.newRequestQueue(getApplicationContext());

                    final SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                    final String token = sharedPreferences.getString("token", null);
                    final String mobile = sharedPreferences.getString("mobile", null);
                    final EditText edt_name_family = (EditText) findViewById(R.id.edt_name_family);
                    final RadioButton male = (RadioButton) findViewById(R.id.male);
                    final RadioButton female = (RadioButton) findViewById(R.id.female);
                    if (edt_name_family.getText().toString().equals("")) {
                        customToast customToast = new customToast(getApplicationContext(), "نام و نام خانوادگی وارد نشده است", javaclasses.customToast.danger, javaclasses.customToast.Top);
                        customToast.getToast().show();
                    } else if (!male.isChecked() && !female.isChecked()) {
                        customToast customToast = new customToast(getApplicationContext(), "جنسیت انتخاب نشده است", javaclasses.customToast.danger, javaclasses.customToast.Bottom);
                        customToast.getToast().show();
                    }
                    else {


                        StringRequest stringRequest = new StringRequest(
                                Request.Method.POST,
                                "http://piratil.com/game/request/complateSubmit.php",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                    Toast.makeText(NameGender.this, response, Toast.LENGTH_SHORT).show();
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if(!jsonObject.getBoolean("version")){
                                                AlertDialog.Builder builder = new AlertDialog.Builder(NameGender.this);
                                                builder.setTitle("خطایی پیش آمده");
                                                builder.setMessage("نسخه جدید را دانلود کنید");
                                                builder.setCancelable(false);
                                                builder.show();
                                            }else{

                                                if (jsonObject.getBoolean("error")){
                                                    customToast customToast = new customToast(getApplicationContext(), jsonObject.getString("MSG"), javaclasses.customToast.danger, javaclasses.customToast.Bottom);
                                                    customToast.getToast().show();
                                                }

                                                else {
                                                    SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                                                    sharedPreferences.edit().putBoolean("signed", true).apply();
                                                    sharedPreferences.edit().putString("token", token).apply();
                                                    sharedPreferences.edit().putString("mobile", mobile).apply();
                                                    Intent intent = new Intent(NameGender.this, aboutus.class);
                                                    startActivity(intent);
                                                    finish();
                                                }

                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                        ){

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> stringStringHashMap = new HashMap<>();
                                stringStringHashMap.put("appVersion","1");
                                stringStringHashMap.put("device","android");
                                stringStringHashMap.put("mobile",mobile);
                                stringStringHashMap.put("token", token+getSaltString());
                                stringStringHashMap.put("name", edt_name_family.getText().toString().trim());
                                stringStringHashMap.put("gender", gender);
                                return  stringStringHashMap;
                            }

                        };

                        RequestQueue.add(stringRequest);

                    }

                }
            });

        }

        private void defineObjects() {

            final Button btn_submit_info = (Button) findViewById(R.id.btn_submitinfo);
            final EditText edt_name_family = (EditText) findViewById(R.id.edt_name_family);
            final RadioButton male = (RadioButton) findViewById(R.id.male);
            final RadioButton female= (RadioButton) findViewById(R.id.female);

        }

        @Override
        protected void attachBaseContext(Context newBase) {
            super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }

        protected String getSaltString() {
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 6) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            String saltStr = salt.toString();
            return saltStr;

        }

    }
