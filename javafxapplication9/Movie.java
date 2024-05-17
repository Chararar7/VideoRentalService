/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication9;

/**
 *
 * @author ALI CHARARA
 */
public class Movie {
   

    
    private int id;
    private String title;
    private String director;
    private String summary;
    private boolean availability;
    private String theme;
    private boolean rented;
    private boolean damaged;
    
    
    public Movie(int id, String title, String director, String summary, boolean availability, String theme, boolean rented, boolean damaged) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.summary = summary;
        this.availability = availability;
        this.theme = theme;
        this.rented = rented;
        this.damaged = damaged;
    }
   public Movie(String title,String director,String summary)
   {
       this.title = title;
        this.director = director;
        this.summary = summary;
   }
    
    // getters and setters
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public boolean isAvailability() {
        return availability;
    }
    
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
    public String getTheme() {
        return theme;
    }
    
    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    public boolean isRented() {
        return rented;
    }
    
    public void setRented(boolean rented) {
        this.rented = rented;
    }
    
    public boolean isDamaged() {
        return damaged;
    }
    
    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }
    
    // toString method
    
    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", summary=" + summary
                + ", availability=" + availability + ", theme=" + theme + ", rented=" + rented + ", damaged=" + damaged + "]";
    }
    
}

