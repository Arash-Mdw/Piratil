package arshmdw.piratil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Credentials;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import swarajsaaj.smscodereader.interfaces.OTPListener;
import swarajsaaj.smscodereader.receivers.OtpReader;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.R.attr.editable;

public class AuthenticationCode extends AppCompatActivity implements OTPListener {
    EditText Authentication;
    com.android.volley.RequestQueue RequestQueue;
    String mobile;
//    private String smsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_code);
        OtpReader.bind(this, "5000958");
        Authentication = (EditText) findViewById(R.id.Authentication);
        RequestQueue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
String smsText;
        String  messageText;
        String[] phoneNumber = new String[] { "5000958" };
        Cursor cursor1 = getContentResolver().query(Uri.parse("content://sms/inbox"), new String[] { "_id", "thread_id", "address", "person", "date","body", "type" }, "address=?", phoneNumber, null);
        StringBuffer msgData = new StringBuffer();
        if (cursor1.moveToFirst()) {
            do {


                for(int idx=0;idx<cursor1.getColumnCount();idx++)
                {
                    msgData.append(" " + cursor1.getColumnName(idx) + ":" + cursor1.getString(idx));
                }

            } while (cursor1.moveToNext());
        } else {


        }

    }

    public void otpReceived(String smsText) {
        // Authentication.setText(smsText);
        //Do whatever you want to do with the text
        Toast.makeText(this, "Got " + smsText, Toast.LENGTH_LONG).show();
      //  Log.d("Otp", smsText);
    }


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public void authentication(final View view) {
        final String AuthenticationCode = Authentication.getText().toString();
        if (AuthenticationCode.equals("")) {
            Snackbar.make(view, "لطفا اول کد را وارد کنید", Snackbar.LENGTH_LONG).show();
                }


        else {
            final ProgressDialog progressDialog = new ProgressDialog(AuthenticationCode.this);
            progressDialog.setMessage("منتظر بمانید");
            progressDialog.setCancelable(false);
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    "http://piratil.com/game/method/method.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                            try {
                                JSONObject json = new JSONObject();
                                String token = json.getJSONObject(response).getString("token");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                            Intent intent = new Intent(AuthenticationCode.this, NameGender.class);
                            startActivity(intent);
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Snackbar.make(view, "کد اشتباه است", Snackbar.LENGTH_LONG).show();

                }

            }
            )
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> stringStringHashMap = new HashMap<>();

                    stringStringHashMap.put("mobile", mobile);
                    stringStringHashMap.put("code", AuthenticationCode);
                    stringStringHashMap.put("method", "checkCode");
                    stringStringHashMap.put("appVersion", "1");
                    stringStringHashMap.put("device", "android");

                    return stringStringHashMap;
                }
            };
            //TODO
            RequestQueue.add(stringRequest);

        }

    }


//    public void otpReceived(String messageText) {
//           String smsText;
//            //Do whatever you want to do with the text
//            Toast.makeText(this,"Got "+smsText,Toast.LENGTH_LONG).show();
//            Log.d("Otp",smsText);


    }


