package demo.kiscode.architecture.data;

import java.util.concurrent.Executor;

import demo.kiscode.architecture.data.model.LoggedUserFromRemote;

public class LoginRepository {
    private static LoginRepository instance;
    private LoginDataSource dataSource;
    private Executor executor;

    private LoginRepository(LoginDataSource dataSource, Executor executor) {
        this.dataSource = dataSource;
        this.executor = executor;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource, Executor executor) {
        if (instance == null) {
            instance = new LoginRepository(dataSource, executor);
        }
        return instance;
    }


    /***
     * 同步登录方法
     * @param username
     * @param password
     * @return
     */
    public Result<LoggedUserFromRemote> login(String username, String password) {
        return dataSource.login(username, password);
    }

    /**
     * @param username 用户名
     * @param password 密码
     * @param callback 登录回调
     */
    public void login(String username, String password, RepositoryCallback<LoggedUserFromRemote> callback) {
        //repository内方法必须是线程安全的，确保主线程调用不会阻塞
        executor.execute(() -> {
            try {
                Result<LoggedUserFromRemote> result = dataSource.login(username, password);
                callback.onComplete(result);
            } catch (Exception e) {
                Result<LoggedUserFromRemote> errorResult = new Result.ErrorResult<>(e);
                callback.onComplete(errorResult);
            }
        });
    }

    public interface RepositoryCallback<T> {
        void onComplete(Result<T> result);
    }
}