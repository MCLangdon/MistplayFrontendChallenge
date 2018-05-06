package com.mc.mistplayfrontendchallenge;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ImageRequest extends AsyncTask<Game, String, Drawable> {

    private Gson gson = new Gson();
    private ImageView view;
    private String imageURL;

    public ImageRequest(ImageView view, String imageURL){
        super();
        this.view = view;
        this.imageURL = imageURL;
    }

    @Override
    protected Drawable doInBackground(Game... games) {
        Drawable result = null;
        try {
            InputStream is = (InputStream) new URL(imageURL).getContent();
            result = Drawable.createFromStream(is, "src name");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(Drawable result){
        super.onPostExecute(result);
        if (result == null){
            System.out.println("Error: Image not found.");
        }
        else {
            view.setImageDrawable(result);
        }
    }
}
