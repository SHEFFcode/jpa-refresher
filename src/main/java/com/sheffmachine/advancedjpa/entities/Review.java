package com.sheffmachine.advancedjpa.entities;

import javax.persistence.*;

@Entity
@Table
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private String rating;

    public Review() { }

    public Review(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", name='" + description + '\'' +
                '}';
    }
}
