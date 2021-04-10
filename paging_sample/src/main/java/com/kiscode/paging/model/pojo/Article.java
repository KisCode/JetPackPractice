package com.kiscode.paging.model.pojo;


/**** 
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/10 19:27
 */
public class Article {
    private int id;
    private String title;

    public Article() {
    }

    public Article(int id, String title) {
        this.id = id;
        this.title = title;
    }

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
}
