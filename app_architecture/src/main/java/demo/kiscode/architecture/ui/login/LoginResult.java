package demo.kiscode.architecture.ui.login;

import androidx.annotation.Nullable;

public class LoginResult {

    @Nullable
    private LoggedInUserToView success;

    @Nullable
    private Integer error;

    public LoginResult(@Nullable LoggedInUserToView success) {
        this.success = success;
    }

    public LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    @Nullable
    public LoggedInUserToView getSuccess() {
        return success;
    }

    @Nullable
    public Integer getError() {
        return error;
    }
}