package com.stuhawe.geigertim.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestShowInserate extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    public TestShowInserate() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_show_inserate);

        mTextViewResult = (TextView) findViewById(R.id.text_view_result);
        Button buttonParse = (Button) findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    private void jsonParse(){
        String url ="http://www.skillrex.de/appdatabase/allUser.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(TestShowInserate.this, "Json wurde erreicht", Toast.LENGTH_SHORT).show();
                            JSONArray jsonArray = response.getJSONArray("Angebote");
                            for (int i=0;i< jsonArray.length();i++){
                                JSONObject angebot = jsonArray.getJSONObject(i);

                                String Ersteller = angebot.getString("Ersteller");
                                String Kategorie = angebot.getString("Kategorie");
                                String Objekt = angebot.getString("Objekt");
                                String Erstelldatum = angebot.getString("Erstelldatum");

                                mTextViewResult.append("Erst: "+Ersteller+" Kat: "+Kategorie+" Obj: "+Objekt+"\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

}
