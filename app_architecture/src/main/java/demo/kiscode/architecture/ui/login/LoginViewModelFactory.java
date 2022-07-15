package demo.kiscode.architecture.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.Executor;

import demo.kiscode.architecture.App;
import demo.kiscode.architecture.data.LoginDataSource;
import demo.kiscode.architecture.data.LoginRepository;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            LoginDataSource loginDataSource = new LoginDataSource();
            Executor executor = App.getInstance().getExecutor();
            return (T) new LoginViewModel(LoginRepository.getInstance(loginDataSource, executor));
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}