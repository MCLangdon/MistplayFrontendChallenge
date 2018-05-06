package com.mc.mistplayfrontendchallenge;

import java.util.ArrayList;
import java.util.Random;

public class CommunicationManager {

    private static CommunicationManager instance;
    private int resultsIndex = 1;
    private String query;

    public static CommunicationManager getInstance(){
        if (instance == null) {
            instance = new CommunicationManager();
        }
        return instance;
    }

    public void searchString(String toSearch){
        query = toSearch;
        // It is a neq query, so clear current search results.
        MainActivity.getInstance().clearGames();
        // Request first page of results from server
        //new RequestTask().execute("http://10.0.2.2:8080/games/1");

        // To prevent running out of pages on dummy server, randomly select a page number between 1 and 3.
        // When using the intended server, use above commented lines instead to get first page of results.
        Random rand = new Random();
        int randomIndex = rand.nextInt(3) + 1;
        String url = "http://10.0.2.2:8080/games/" + randomIndex;
        new RequestTask().execute(url);

    }

    public void loadMoreResults(){
        // Not a new query, so do not clear current results.
        resultsIndex++;

        // To prevent running out of pages on dummy server, randomly select a page number between 1 and 3.
        // When using the intended server, use resultsIndex instead of randomIndex.
        Random rand = new Random();
        int randomIndex = rand.nextInt(3) + 1;
        String url = "http://10.0.2.2:8080/games/" + randomIndex;
        new RequestTask().execute(url);
    }
}
