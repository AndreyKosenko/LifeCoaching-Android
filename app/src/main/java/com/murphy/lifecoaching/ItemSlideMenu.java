package com.murphy.lifecoaching;

/**
 * Created by Administrator on 2/20/2016.
 */
public class ItemSlideMenu {
    private int imgId;
    private String title;
    private String mainTitle;

    public ItemSlideMenu(int imgId, String title, String mainTitle) {
        this.imgId = imgId;
        this.title = title;
        this.mainTitle = mainTitle;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainTitle(){return mainTitle; };
}
