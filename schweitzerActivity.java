package com.example.tyler.shred_v2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class schweitzerActivity extends AppCompatActivity {
    final String TAG = "SchweitzerActivity";
    String date = "as";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schweitzer);
        APIRequestAsyncTask asyncTask = new APIRequestAsyncTask();
        String urlRequest = "http://api.wunderground.com/api/f49718550e66efbc/conditions/q/id/colburn.json";
        asyncTask.execute(urlRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class APIRequestAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: ");
        }

        // must override doInBackground()
        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            // THIS CODE RUNS ON A BACKGROUND THREAD
            // varargs is the ...
            // variable number of arguments
            // treat like an array
            Log.d(TAG, "doInBackground: HELLO FROM BACKGROUND THREAD");
            Log.d(TAG, "doInBackground: " + strings[0]);

            ArrayList<String> weatherInfo = new ArrayList<>();

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)
                        url.openConnection();

                // 2) get the JSON response
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                String result = "";
                int data = reader.read();
                while (data != -1) {
                    result += (char) data;
                    data = reader.read();
                }

                // 3) parse the response for the data you are looking for
                JSONObject jsonObject = new JSONObject(result); // throws a JSONException
                Log.d("Main", "Connection established");
                Log.d("Main json obj", jsonObject.toString());
                JSONObject mainRows = jsonObject.getJSONObject("current_observation");
                JSONObject locationItems = mainRows.getJSONObject("display_location");
                String full = locationItems.getString("full");
                weatherInfo.add(full);
                String latitude = locationItems.getString("latitude");
                weatherInfo.add(latitude);
                String longitude = locationItems.getString("longitude");
                weatherInfo.add(longitude);
                String elevation = locationItems.getString("elevation");
                weatherInfo.add(elevation);
                String temp = mainRows.getString("temperature_string");
                weatherInfo.add(temp);
                String windDir = mainRows.getString("wind_dir");
                weatherInfo.add(windDir);
                String wind_mph = mainRows.getString("wind_mph");
                weatherInfo.add(wind_mph);
//                String cityItem = rows.getString("city");
//                JSONObject firstRowItem = rows.getJSONObject(0);
//                Log.d("Rows item:", rows.toString());
//                Log.d("dateItem:", dateItem.toString());
//                Log.d("City item:", cityItem.toString());
//                JSONArray elements = firstRowItem.getJson("txt_forecast");
//                JSONObject firstElementItem = elements.getJSONObject(0);
//                JSONObject distanceObject = firstElementItem.getJSONObject("fcttext");
//                date = rows.getString("date");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // always assume success!!!
            return weatherInfo;
        }

        @Override
        protected void onPostExecute(ArrayList<String> weatherInfo) {
            super.onPostExecute(weatherInfo);
            // run on the UI thread
            Log.d(TAG, "onPostExecute: " + weatherInfo.toString());
            if (!weatherInfo.isEmpty()) {
                TextView textView = (TextView) findViewById(R.id.fullTextView);
                textView.setText(getString(R.string.location) + " " + weatherInfo.get(0));

                textView = (TextView) findViewById(R.id.latitudeTextView);
                textView.setText(getString(R.string.latitude) + " " + weatherInfo.get(1));

                textView = (TextView) findViewById(R.id.longitudeTextView);
                textView.setText(getString(R.string.longitude) + " " + weatherInfo.get(2));

                textView = (TextView) findViewById(R.id.elevationTextView);
                textView.setText(getString(R.string.elevation) + " " + weatherInfo.get(3));

                textView = (TextView) findViewById(R.id.tempTextView);
                textView.setText(getString(R.string.temp) + " " + weatherInfo.get(4));

                textView = (TextView) findViewById(R.id.windmphTextView);
                textView.setText(getString(R.string.windmph) + " " + weatherInfo.get(5));

                textView = (TextView) findViewById(R.id.winddirTextView);
                textView.setText(getString(R.string.winddir) + " " + weatherInfo.get(6));
            }
        }
    }
}