package com.mc.mistplayfrontendchallenge;

public class Game {

    private String imgURL;
    private String subGenre;
    private String title;
    private double rating;
    private int ratingCount;

    public void setImgURL(String imgURL){
        this.imgURL = imgURL;
    }

    public String getImgURL(){
        return imgURL;
    }
    public void setSubGenre(String subGenre){
        this.subGenre = subGenre;
    }

    public String getSubGenre(){
        return subGenre;
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
        this.ratingCount = ratingCount;
    }

    public int getRatingCount(){
        return ratingCount;
    }

    public String toString(){
        return "HIHIHIHI";
    }
}
