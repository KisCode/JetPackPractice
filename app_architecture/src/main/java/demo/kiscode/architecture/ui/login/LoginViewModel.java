package demo.kiscode.architecture.ui.login;

import android.os.AsyncTask;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import demo.kiscode.architecture.R;
import demo.kiscode.architecture.data.LoginRepository;
import demo.kiscode.architecture.data.Result;
import demo.kiscode.architecture.data.model.LoggedUserFromRemote;

public class LoginViewModel extends ViewModel {

    /**
     * 登录状态
     */
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();

    /**
     * 登录结果
     */
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();


    private LoginRepository loginRepository;

    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public MutableLiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    /**
     * 登录账户密码校验, loginRepository内部异步子线程登录，通过回调方式获取登录结果
     *
     * @param username 用户名
     * @param password 密码
     */
    public void loginAsync(String username, String password) {
        loginRepository.login(username, password, result -> {
            if (result instanceof Result.SuccessResult) {
                LoggedUserFromRemote data = ((Result.SuccessResult<LoggedUserFromRemote>) result).getData();
                //将服务端返回数据按需转为界面需要的数据
                loginResult.postValue(new LoginResult(new LoggedInUserToView(data.getName())));
            } else if (result instanceof Result.ErrorResult) {
                loginResult.postValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    /**
     * 登录账户密码校验
     *
     * @param username 用户名
     * @param password 密码
     */
    public void login(String username, String password) {
        AsyncTask.execute(() -> {
            Result<LoggedUserFromRemote> result = loginRepository.login(username, password);
            if (result instanceof Result.SuccessResult) {
                LoggedUserFromRemote data = ((Result.SuccessResult<LoggedUserFromRemote>) result).getData();
                //将服务端返回数据按需转为界面需要的数据
                loginResult.postValue(new LoginResult(new LoggedInUserToView(data.getName())));
            } else if (result instanceof Result.ErrorResult) {
                loginResult.postValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    /**
     * 登录账户密码校验
     *
     * @param username 用户名
     * @param password 密码
     */
    public void loginFormDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.postValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.postValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.postValue(new LoginFormState(true));
        }
    }


    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

}