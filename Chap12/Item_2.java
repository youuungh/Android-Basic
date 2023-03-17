package com.example.pj0317_2;

public class Item {
    private Integer[] posterId;
    private String[] posterTitle;

    Item(Integer[] posterId, String[] posterTitle) {
        this.posterId = posterId;
        this.posterTitle = posterTitle;
    }

    public Integer[] getPosterId() {
        return posterId;
    }

    public void setPosterId(Integer[] posterId) {
        this.posterId = posterId;
    }

    public String[] getPosterTitle() {
        return posterTitle;
    }

    public void setPosterTitle(String[] posterTitle) {
        this.posterTitle = posterTitle;
    }
}
