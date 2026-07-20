package com.example.capstone.model;

import java.time.LocalDateTime;

public class Review {
    private String reviewId;
    private int rating;
    private String comment;
    private LocalDateTime datePosted;

    public Review(String reviewId, int rating, String comment) {
        this.reviewId   = reviewId;
        this.rating     = rating;
        this.comment    = comment;
        this.datePosted = LocalDateTime.now();
    }

    public int getRating()     { return rating; }
    public String getComment() { return comment; }
}