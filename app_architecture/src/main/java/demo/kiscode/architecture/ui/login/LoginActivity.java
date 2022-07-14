package demo.kiscode.architecture.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import demo.kiscode.architecture.databinding.ActivityLoginBinding;
import demo.kiscode.architecture.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViewModel();
        initViews();
    }

    private void initViewModel() {
//        loginViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(LoginViewModel.class);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory()).get(LoginViewModel.class);
        loginViewModel.getLoginFormState()
                .observe(this, new Observer<LoginFormState>() {
                    @Override
                    public void onChanged(LoginFormState loginFormState) {
                        if (loginFormState == null) {
                            return;
                        }

                        binding.login.setEnabled(loginFormState.isDataValid());
                        if (loginFormState.getUserNameError() != null) {
                            binding.username.setError(getString(loginFormState.getUserNameError()));
                        }
                        if (loginFormState.getPasswordError() != null) {
                            binding.password.setError(getString(loginFormState.getPasswordError()));
                        }
                    }
                });

        loginViewModel.getLoginResult()
                .observe(this, new Observer<LoginResult>() {
                    @Override
                    public void onChanged(LoginResult loginResult) {
                        if (loginResult == null) {
                            return;
                        }

                        binding.loading.setVisibility(View.GONE);

                        //登录失败
                        if (loginResult.getError() != null) {
                            showShortToast(loginResult.getError());
                            return;
                        }

                        //登录成功
                        if (loginResult.getSuccess() != null) {
                            LoggedInUserToView loggedInUserToView = loginResult.getSuccess();
                            updateUiWithUser(loggedInUserToView);
                        }
                    }
                });
    }

    private void initViews() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginFormDataChanged(binding.username.getText().toString()
                        , binding.password.getText().toString());
            }
        };

        binding.username.addTextChangedListener(textWatcher);
        binding.password.addTextChangedListener(textWatcher);
        binding.login.setOnClickListener(v -> {
            binding.loading.setVisibility(View.VISIBLE);
            loginViewModel.loginAsync(binding.username.getText().toString()
                    , binding.password.getText().toString());
        });
    }

    private void updateUiWithUser(LoggedInUserToView user) {
        MainActivity.start(this, user);
        //finish
        finish();
    }

    private void showShortToast(@StringRes Integer strResId) {
        Toast.makeText(getApplicationContext(), strResId, Toast.LENGTH_SHORT).show();
    }
}