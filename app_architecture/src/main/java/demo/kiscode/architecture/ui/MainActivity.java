package demo.kiscode.architecture.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import demo.kiscode.architecture.R;
import demo.kiscode.architecture.databinding.ActivityMainBinding;
import demo.kiscode.architecture.ui.login.LoggedInUserToView;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_LOGGER_USERNAME = "LOGGER_USERNAME";
    private ActivityMainBinding binding;

    public static void start(Context context, LoggedInUserToView user) {
        Intent intent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_LOGGER_USERNAME, user);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
    }

    private void initData() {
        String info = getString(R.string.welcome);
        if (getIntent() != null && getIntent().hasExtra(KEY_LOGGER_USERNAME)) {
            LoggedInUserToView user = (LoggedInUserToView) getIntent().getSerializableExtra(KEY_LOGGER_USERNAME);
            info += user.getName();
        }
        binding.tvContent.setText(info);
    }
}