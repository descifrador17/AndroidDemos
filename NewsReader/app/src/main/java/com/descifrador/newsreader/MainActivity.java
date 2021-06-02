package com.descifrador.newsreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> newsID;
    static ArrayList<String> newsTitle;
    ArrayAdapter<String> arrayAdapter;
    static ArrayList<String> newsURL;
    ListView newsTitleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialising
        newsID = new ArrayList<>();
        newsTitle = new ArrayList<>();
        newsURL = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,newsTitle);
        newsTitleListView = findViewById(R.id.newsTitleListView);

        //making download task to retrieve top stories
        DownloadTask task = new DownloadTask();
        try {
            String result = task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty").get();
            extractJSONNewsID(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for(String ID:newsID){
                DownloadTask task1 = new DownloadTask();
                String result = task1.execute("https://hacker-news.firebaseio.com/v0/item/"+ID+".json?print=pretty").get();
                Log.i("RESULT",result);
                extractJSONData(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        newsTitleListView.setAdapter(arrayAdapter);

        newsTitleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),ArticleReader.class);
                intent.putExtra("Position",position);
                startActivity(intent);
            }
        });
    }

    public static class DownloadTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            String result="";
            URL url;
            HttpURLConnection httpURLConnection=null;
            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data != -1){
                    char current = (char) data;
                    result+=current;
                    data = reader.read();
                }
                return result;
                //Log.i("RESULT ",result);
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }
        }

    }

    //extract JSON NewsID
    public static void extractJSONNewsID(String input) throws JSONException {
        JSONArray jsonArray = new JSONArray(input);
        for(int i=0;i<10;i++){
            newsID.add(jsonArray.getString(i));
            Log.i("EXTRACT",jsonArray.getString(i));
        }
    }

    //extract JSON Data
    public static void extractJSONData(String input) throws JSONException {
        JSONObject jsonObject = new JSONObject(input);
        String s="No title";
        if(jsonObject.has("title")) {
            s = jsonObject.getString("title");
        }
        newsTitle.add(s);
        String url="no url";
        if(jsonObject.has("url")) {
            url = jsonObject.getString("url");
        }
        newsURL.add(url);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
                Toast.makeText(this, "help", Toast.LENGTH_SHORT).show();
                break;
            default:
                return false;
        }
        return true;
    }
}
