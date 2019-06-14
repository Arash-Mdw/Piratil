package javaclasses;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Arash on 6/12/2019.
 */

public class vollayRequest {
//    HashMap<String, String> stringStringHashMap;
//    Context context;
//    String url;
//    serverCallback ServerCallback;
//    RequestQueue requestQueue;
//    JSONObject json;
//
//
//    public void requester(final HashMap<String, String> stringStringHashMap,
//                          final Context context, String url,
//                          final ServerCallback serverCallback) {
//        this.stringStringHashMap = stringStringHashMap;
//        this.context = context;
//        this.url = url;
//        this.ServerCallback = ServerCallback;
//        requestQueue = Volley.newRequestQueue(context);
//
//        StringRequest stringRequest = new StringRequest(
//                Request.Method.POST,
//                "http://piratil.com/game/request/" + url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            versionController versionController = new versionController(jsonObject.getBoolean("version"),context);
//                            serverCallback.onSuccess(jsonObject);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                stringStringHashMap.put("appVersion", "1");
//                stringStringHashMap.put("device", "android");
//                return stringStringHashMap;
//            }
//        };
//
//        requestQueue.add(stringRequest);
//    }
//
//
//
//    protected String getSaltString() {
//        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        StringBuilder salt = new StringBuilder();
//        Random rnd = new Random();
//        while (salt.length() < 6) { // length of the random string.
//            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//            salt.append(SALTCHARS.charAt(index));
//        }
//        String saltStr = salt.toString();
//        return saltStr;
//
//    }
//
//    public static final String md5(final String s) {
//        final String MD5 = "MD5";
//        try {
//            // Create MD5 Hash
//            MessageDigest digest = java.security.MessageDigest
//                    .getInstance(MD5);
//            digest.update(s.getBytes());
//            byte messageDigest[] = digest.digest();
//
//            // Create Hex String
//            StringBuilder hexString = new StringBuilder();
//            for (byte aMessageDigest : messageDigest) {
//                String h = Integer.toHexString(0xFF & aMessageDigest);
//                while (h.length() < 2)
//                    h = "0" + h;
//                hexString.append(h);
//            }
//            return hexString.toString();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
}
