package service;

import java.util.ArrayList;
import model.Media;

public class WatchlistService {

    private ArrayList<Media> mediaList;

    public WatchlistService() {
        mediaList = new ArrayList<>();
    }

    public void addMedia(Media media) {
        mediaList.add(media);
    }

    public void displayAllMedia() {
        if (mediaList.isEmpty()) {
            System.out.println("No movies or series available.");
            return;
        }

        for (Media media : mediaList) {
            media.displayDetails();
            System.out.println("--------------------");
        }
    }

    public Media search(String title) {
        for (Media media : mediaList) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }

        return null;
    }

    public Media search(String title, String genre) {
        for (Media media : mediaList) {
            if (media.getTitle().equalsIgnoreCase(title)
                    && media.getGenre().equalsIgnoreCase(genre)) {
                return media;
            }
        }

        return null;
    }

    public ArrayList<Media> getMediaList() {
        return mediaList;
    }
}