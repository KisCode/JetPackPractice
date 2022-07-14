package demo.kiscode.architecture.ui.login;

import androidx.annotation.Nullable;

/**
 * Description: 登录表单状态
 * Author: keno
 * Date : 2022/7/14 15:01
 **/
public class LoginFormState {
    @Nullable
    private Integer userNameError;

    @Nullable
    private Integer passwordError;

    private boolean isDataValid;

    public LoginFormState(@Nullable Integer userNameError, @Nullable Integer passwordError) {
        this.userNameError = userNameError;
        this.passwordError = passwordError;
    }

    public LoginFormState(boolean isDataValid) {
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getUserNameError() {
        return userNameError;
    }

    @Nullable
    public Integer getPasswordError() {
        return passwordError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}