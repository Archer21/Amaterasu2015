package com.archer.amaterasu.domain;

public class Song {
    private int    songPlaycount;
    private int    songViews;
    private float  songRating;
    private String songTitle;
    private String songArtist;
    private String songImageSmall;
    private String songImageMedium;

    public int getSongPlaycount() {
        return songPlaycount;
    }

    public void setSongPlaycount(int songPlaycount) {
        this.songPlaycount = songPlaycount;
    }

    public int getSongViews() {
        return songViews;
    }

    public void setSongViews(int songViews) {
        this.songViews = songViews;
    }

    public float getSongRating() {
        return songRating;
    }

    public void setSongRating(float songRating) {
        this.songRating = songRating;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongImageSmall() {
        return songImageSmall;
    }

    public void setSongImageSmall(String songImageSmall) {
        this.songImageSmall = songImageSmall;
    }

    public String getSongImageMedium() {
        return songImageMedium;
    }

    public void setSongImageMedium(String songImageMedium) {
        this.songImageMedium = songImageMedium;
    }
}