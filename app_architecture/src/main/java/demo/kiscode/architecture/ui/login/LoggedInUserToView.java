package demo.kiscode.architecture.ui.login;

import java.io.Serializable;

/**
 * Description: 登录成功返回用户信息
 * Author: keno
 * Date : 2022/7/14 15:19
 **/
public class LoggedInUserToView implements Serializable {
    private String name;

    public LoggedInUserToView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}