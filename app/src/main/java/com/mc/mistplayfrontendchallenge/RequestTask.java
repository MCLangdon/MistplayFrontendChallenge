package com.mc.mistplayfrontendchallenge;

import android.media.Image;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


class RequestTask extends AsyncTask<String, String, String> {

    Gson gson = new Gson();

    @Override
    protected String doInBackground(String... uri) {
        String responseString = null;
        try {
            // Create URL
            URL gamesDatabaseEndpoint = null;
//            dndLanguagesEndpoint = new URL("http://dnd5eapi.co/api/languages/1");
            gamesDatabaseEndpoint = new URL(uri[0]);

            // Create connection
            HttpURLConnection myConnection = null;
            try {
                myConnection = (HttpURLConnection) gamesDatabaseEndpoint.openConnection();
            } catch (Exception e){
                e.printStackTrace();
            }
            myConnection.setRequestProperty("User-Agent", "mistplayfrontendchallenge-app-v0.1");

            if (myConnection.getResponseCode() == 200) {
                // Success
                InputStream responseBody = myConnection.getInputStream();
                responseString = IOUtils.toString(responseBody, "UTF-8");
            } else {

                System.out.println("Error: response failed");
            }
            myConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }
    @Override
    protected void onPostExecute(String result) {
        try {
            super.onPostExecute(result);
            if (result == null) {
                System.out.println("Network error: no response received");
                Game error = new Game();
                error.setTitle("Network failure.");
                error.setSubGenre("");
                error.setRating(0);
            } else if (result.equals("")) {
                Game noResultsFound = new Game();
                noResultsFound.setTitle("No results found.");
                noResultsFound.setSubGenre("");
                noResultsFound.setRating(0);
            } else {
                System.out.println("Response received");
                ArrayList<Game> games = gson.fromJson(result, new TypeToken<ArrayList<Game>>() {
                }.getType());
                for (Game g : games) {
                    MainActivity.getInstance().addGame(g);
                }
            }
        } catch (JsonSyntaxException e){
            e.printStackTrace();
            System.out.println("Network error: no games found.");
            Game error = new Game();
            error.setTitle("Network failure.");
            error.setSubGenre("");
            error.setRating(0);
        }
    }
}
