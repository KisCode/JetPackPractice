package demo.kiscode.architecture.data.model;

import java.io.Serializable;

/**
 * Description: 登录从服务端返回的用户信息
 * Author: keno
 * Date : 2022/7/14 15:47
 **/
public class LoggedUserFromRemote implements Serializable {
    private String id;
    private String name;
    private String position;
    private int age;

    public LoggedUserFromRemote(String id, String name, String position, int age) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
} 