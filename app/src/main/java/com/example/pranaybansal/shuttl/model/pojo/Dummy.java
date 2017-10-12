package com.example.pranaybansal.shuttl.model.pojo;

import java.io.Serializable;

/**
 * Created by Pranay Bansal on 10/11/2017.
 */

public class Dummy implements Serializable {
    private String time;

    private String text;

    private String title;

    private String imageUrl;

    private String description;

    private String name;

    private boolean liked;

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getImageUrl ()
    {
        return imageUrl;
    }

    public void setImageUrl (String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [time = "+time+", text = "+text+", title = "+title+", imageUrl = "+imageUrl+", description = "+description+", name = "+name+"]";
    }
}
