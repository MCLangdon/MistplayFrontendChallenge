package com.mc.mistplayfrontendchallenge;

import java.util.ArrayList;

public class CommunicationManager {

    private static CommunicationManager instance;


    public static CommunicationManager getInstance(){
        if (instance == null) {
            instance = new CommunicationManager();
        }
        return instance;
    }

    public ArrayList<Game> searchString(String toSearch){
        //TODO
        // REMOVE AFTER TESTING:
        System.out.println("Searching for : " + toSearch);
        Game gameResult = new Game();
        gameResult.setTitle(toSearch);
        gameResult.setRating(4.0);
        gameResult.setSubGenre("Strategy");
        ArrayList<Game> result = new ArrayList<>();
        result.add(gameResult);
        return result;
    }
}
