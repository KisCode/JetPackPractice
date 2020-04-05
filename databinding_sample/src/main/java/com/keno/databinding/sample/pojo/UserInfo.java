package com.keno.databinding.sample.pojo;


import androidx.databinding.BaseObservable;

/****
 * ProjectName: JetPackPractice
 * Package: com.keno.databinding.sample.pojo
 * ClassName: UserInfo
 * Description:
 * Author:  Administrator
 * CreateDate: 2020/4/4 12:14
 */

public class UserInfo extends BaseObservable {
    private String name;
    private String email;

    public UserInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
