package com.example.newsapi23.domen;

public class ListNewsHeadlines extends NewsHeadlines{ //wrapper class, with an additional Favorites parameter. Custom class
    private boolean isFavorite;

    public ListNewsHeadlines(NewsHeadlines newsHeadlines, boolean isFavorite){
        super(newsHeadlines.getSource(), newsHeadlines.getAuthor(), newsHeadlines.getTitle(), newsHeadlines.getDescription(), newsHeadlines.getUrl(), newsHeadlines.getUrlToImage(), newsHeadlines.getPublishedAt(), newsHeadlines.getContent());
        this.isFavorite = isFavorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
