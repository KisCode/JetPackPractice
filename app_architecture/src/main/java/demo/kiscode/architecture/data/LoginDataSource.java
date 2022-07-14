package demo.kiscode.architecture.data;

import android.util.Log;

import java.io.IOException;
import java.util.UUID;

import demo.kiscode.architecture.data.model.LoggedUserFromRemote;

public class LoginDataSource {
    private static final String TAG = "LoginDataSource";

    public Result<LoggedUserFromRemote> login(String username, String password) {
        try {
            Log.i(TAG, "login..." + Thread.currentThread().getName());
            Thread.sleep(3000);
            LoggedUserFromRemote mockUser = new LoggedUserFromRemote(
                    UUID.randomUUID().toString(),
                    username,
                    "",
                    28);
            return new Result.SuccessResult<>(mockUser);
        } catch (Exception e) {
            return new Result.ErrorResult<>(new IOException("Error logging in", e));
        }
    }


}