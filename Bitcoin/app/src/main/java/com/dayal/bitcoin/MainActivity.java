package com.dayal.bitcoin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";

    TextView Bitcoinrate;
    Spinner currency_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitcoinrate = (TextView) findViewById(R.id.bitcoin_rate);
        currency_spinner = (Spinner) findViewById(R.id.select_currency);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currency, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        currency_spinner.setAdapter(adapter);

        currency_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Bitcoin", "Selected " + parent.getItemAtPosition(position));
                String URL = BASE_URL + parent.getItemAtPosition(position);
                Log.d("Bitcoin" , "Final URL = "+URL);
                fetchingdata(URL);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("Bitcoin", "Nothing Selected");
            }
        });
    }

    public void fetchingdata(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Log.d("Bitcoin" , "JSON : " + response.toString());

                try{
                    String price = response.getString("last");
                    Bitcoinrate.setText(price);

                } catch(JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(MainActivity.this ,"Failed to fetch data",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
