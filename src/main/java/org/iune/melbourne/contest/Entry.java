package org.iune.melbourne.contest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Entry {
    private String country;
    private File flag;
    private String artist;
    private String song;

    private List<String> votes;
    private List<Integer> displayPoints;
    private List<Integer> sortingPoints;
    private List<Boolean> disqualificationStatus;

    public Entry(String country, File flag, String artist, String song, List<String> votes) {
        this.country = country;
        this.flag = flag;
        this.artist = artist;
        this.song = song;

        this.votes = votes;
        this.displayPoints = this.setDisplayPoints();
        this.sortingPoints = this.setSortingPoints();
        this.disqualificationStatus = this.setDisqualificationStatus();
    }

    public boolean validateFlag() {
        return this.flag.exists();
    }

    private List<Integer> setDisplayPoints() {
        List<Integer> displayPoints = new ArrayList<>();

        int total = 0;
        for(String vote : this.votes) {
            int currentVote;
            try {
                currentVote = Integer.parseInt(vote);
            }
            catch (NumberFormatException e) {
                currentVote = 0;
            }

            total += currentVote;
            displayPoints.add(total);
        }

        return displayPoints;
    }

    private List<Integer> setSortingPoints() {
        List<Integer> sortingPoints = new ArrayList<>();

        boolean isDisqualified = false;
        for (int i = 0; i < this.votes.size(); i++) {
            if (this.votes.get(i).equalsIgnoreCase("DQ")) isDisqualified = true;

            if (isDisqualified) sortingPoints.add(-1000);
            else sortingPoints.add(this.displayPoints.get(i));
        }
        return sortingPoints;
    }

    private List<Boolean> setDisqualificationStatus() {
        List<Boolean> disqualificationStatus = new ArrayList<>();

        boolean isDisqualified = false;
        for (int i = 0; i < this.votes.size(); i++) {
            if (this.votes.get(i).equalsIgnoreCase("DQ")) isDisqualified = true;
            disqualificationStatus.add(isDisqualified);
        }
        return disqualificationStatus;
    }

    public String getCountry() {
        return this.country;
    }

    public File getFlag() {
        return this.flag;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getSong() {
        return this.song;
    }

    public String getVotes(int voter) {
        return this.votes.get(voter);
    }

    public int getDisplayPoints(int voter) {
        return this.displayPoints.get(voter);
    }

    public int getFinalDisplayPoints() { return this.displayPoints.get(this.displayPoints.size() - 1); }

    public int getSortingPoints(int voter) {
        return this.sortingPoints.get(voter);
    }

    public int getFinalSortingPoints() { return this.sortingPoints.get(this.sortingPoints.size() - 1); }

    public boolean getDisqualificationStatus(int voter) {
        return this.disqualificationStatus.get(voter);
    }
}
