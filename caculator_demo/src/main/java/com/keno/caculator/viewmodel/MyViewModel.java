package com.keno.caculator.viewmodel;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;


public class MyViewModel extends AndroidViewModel {
    private SavedStateHandle handle;

    private static final String KEY_HIGH_SCORE = "key_high_score";
    private static final String KEY_LEFT_NUMBER = "key_high_score";
    private static final String KEY_RIGHT_NUMBER = "key_high_score";
    private static final String KEY_OPERATOR = "KEY_OPERATOR";
    private static final String KEY_MY_ANSWER = "KEY_MY_ANSWER";
    private static final String KEY_CORRECT_ANSWER = "KEY_CORRECT_ANSWER";
    private static final String KEY_CURRENT_SOCRE = "KEY_CURRENT_SOCRE";
    private static final String SAVE_SP_DATA_NAME = "SAVE_SP_DATA_NAME";

    public boolean winFlag = false;

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;

        if (!handle.contains(KEY_HIGH_SCORE)) {
            SharedPreferences sp = getApplication().getSharedPreferences(SAVE_SP_DATA_NAME, Context.MODE_PRIVATE);
            handle.set(KEY_HIGH_SCORE, 0);
            handle.set(KEY_LEFT_NUMBER, 0);
            handle.set(KEY_RIGHT_NUMBER, 0);
            handle.set(KEY_OPERATOR, "+");
            handle.set(KEY_MY_ANSWER, "");
            handle.set(KEY_CORRECT_ANSWER, 0);
            handle.set(KEY_CURRENT_SOCRE, 0);
        }
    }

    public MutableLiveData<Integer> getLeftNumber() {
        return handle.getLiveData(KEY_LEFT_NUMBER);
    }


    public MutableLiveData<Integer> getRightNumber() {
        return handle.getLiveData(KEY_RIGHT_NUMBER);
    }


    public MutableLiveData<String> getOperator() {
        return handle.getLiveData(KEY_OPERATOR);
    }


    public MutableLiveData<String> getMyAnswer() {
        return handle.getLiveData(KEY_MY_ANSWER);
    }

    public MutableLiveData<Integer> getCorrectAnswer() {
        return handle.getLiveData(KEY_CORRECT_ANSWER);
    }

    public MutableLiveData<Integer> getHightScore() {
        return handle.getLiveData(KEY_HIGH_SCORE);
    }


    public MutableLiveData<Integer> getCurrentScore() {
        return handle.getLiveData(KEY_CURRENT_SOCRE);
    }

    public void generator() {
        final int LEVEL = 20;
        Random random = new Random();
        int x, y;
        x = random.nextInt(LEVEL) + 1;
        y = random.nextInt(LEVEL) + 1;

        if (x % 2 == 0) {
            getOperator().setValue("+");
            getLeftNumber().setValue(x);
            getRightNumber().setValue(y);
            getCorrectAnswer().setValue(x + y);
        } else {
            getOperator().setValue("-");
            if (x > y) {
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y);
                getCorrectAnswer().setValue(x - y);
            } else {
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x);
                getCorrectAnswer().setValue(y - x);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    public void save() {
        SharedPreferences sp = getApplication().getSharedPreferences(SAVE_SP_DATA_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_HIGH_SCORE, getHightScore().getValue());
        editor.apply();
    }

    @SuppressWarnings("ConstantConditions")
    public void answerCorrect() {
        getCurrentScore().setValue(getCurrentScore().getValue() + 1);
        if (getCurrentScore().getValue() > getHightScore().getValue()) {
            getHightScore().setValue(getCurrentScore().getValue());
            winFlag = true;
        }
        generator();
    }
}
