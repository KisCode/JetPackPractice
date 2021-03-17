package com.keno.caculator.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keno.caculator.R;
import com.keno.caculator.databinding.FragmentQuestionBinding;
import com.keno.caculator.viewmodel.MyViewModel;

/**
 * Description: 答题页面
 * Author: keno
 * CreateDate: 2020/6/1 11:45
 */

public class QuestionFragment extends Fragment {

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SavedStateViewModelFactory factory = new SavedStateViewModelFactory(requireActivity().getApplication(), requireActivity());
        final MyViewModel viewModel = new ViewModelProvider(requireActivity(), factory).get(MyViewModel.class);
        viewModel.generator();  //初始化题目

        FragmentQuestionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(viewModel);
        binding.setLifecycleOwner(this);


        final StringBuilder builder = new StringBuilder();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button0:
                        builder.append("0");
                        break;
                    case R.id.button1:
                        builder.append("1");
                        break;
                    case R.id.button2:
                        builder.append("2");
                        break;
                    case R.id.button3:
                        builder.append("3");
                        break;
                    case R.id.button4:
                        builder.append("4");
                        break;
                    case R.id.button5:
                        builder.append("5");
                        break;
                    case R.id.button6:
                        builder.append("6");
                        break;
                    case R.id.button7:
                        builder.append("7");
                        break;
                    case R.id.button8:
                        builder.append("8");
                        break;
                    case R.id.button9:
                        builder.append("9");
                        break;
                    case R.id.buttonClear:
                        builder.setLength(0); //清空
                        break;
                }

                viewModel.getMyAnswer().setValue(builder.toString());
            }
        };
        binding.button0.setOnClickListener(listener);
        binding.button1.setOnClickListener(listener);
        binding.button2.setOnClickListener(listener);
        binding.button3.setOnClickListener(listener);
        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.buttonClear.setOnClickListener(listener);
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(builder.toString()).intValue() == viewModel.getCorrectAnswer().getValue()) {
                    //正确答案
                    viewModel.answerCorrect();
                    builder.setLength(0);
                    builder.append(getString(R.string.answer_correct_message));
                } else {
                    final NavController controller = Navigation.findNavController(v);
                    if (viewModel.winFlag) {
                        controller.navigate(R.id.action_questionFragment_to_winFragment);
                        viewModel.winFlag = false;
                        viewModel.save();
                    } else {
                        controller.navigate(R.id.action_questionFragment_to_winFragment);
                    }
                }
            }
        });

        return binding.getRoot();
    }
}
