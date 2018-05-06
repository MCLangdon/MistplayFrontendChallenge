package com.mc.mistplayfrontendchallenge;

import android.media.Image;

public class Game {

    private String imgURL;
    private String subgenre;
    private String title;
    private double rating;
    private int rCount;


    public void setImgURL(String imgURL){
        this.imgURL = imgURL;
    }

    public String getImgURL(){
        return imgURL;
    }

    public void setSubGenre(String subGenre){
        this.subgenre = subGenre;
    }

    public String getSubGenre(){
        return subgenre;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public double getRating(){
        return rating;
    }

    public void setRatingCount(int ratingCount){
        this.rCount = ratingCount;
    }

    public int getRatingCount(){
        return rCount;
    }

}
