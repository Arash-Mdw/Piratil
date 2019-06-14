package arshmdw.piratil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//RequestQueue re Volley.newRequestQueue(this);
public class PhoneNumber extends AppCompatActivity {
EditText phone;
    RequestQueue RequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
         phone = (EditText) findViewById(R.id.phoneNumber);
        RequestQueue = Volley.newRequestQueue(this);
    }
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void PhoneNumber(View view) {
        final String TextPhoneNumber = phone.getText().toString().trim();
        if (TextPhoneNumber.length() != 11) {
            Snackbar.make(view, "شماره تلفن اشتباه است", Snackbar.LENGTH_LONG).show();
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(PhoneNumber.this);
            progressDialog.setMessage("منتظر بمانید");
            progressDialog.setCancelable(false);
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, "http://piratil.com/game/method/method.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent INtent = new Intent(PhoneNumber.this, AuthenticationCode.class);
                            INtent.putExtra("mobile", TextPhoneNumber);
                            startActivity(INtent);
                            progressDialog.dismiss();
                        } }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                } } ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> stringStringHashMap = new HashMap<>();
                    stringStringHashMap.put("mobile", TextPhoneNumber);
                    stringStringHashMap.put("appVersion", "1");
                    stringStringHashMap.put("device", "android");
                    stringStringHashMap.put("method", "submitUser");
                    return stringStringHashMap;
                }
            };
            RequestQueue.add(stringRequest);

        }

    }
}
