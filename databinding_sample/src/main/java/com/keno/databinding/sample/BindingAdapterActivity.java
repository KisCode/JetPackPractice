package com.keno.databinding.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.keno.databinding.sample.databinding.ActivityBindingAdapterBinding;
import com.keno.databinding.sample.pojo.UserFieldModel;

import java.util.Random;

public class BindingAdapterActivity extends AppCompatActivity {

    private final String[] imgUrls = {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583645280003&di=5fe6abe73858ecfb9bc64281711acde9&imgtype=0&src=http%3A%2F%2Fpic1.zhimg.com%2F50%2Fv2-60957c76fd38d48903276445b27ac714_hd.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583645348271&di=3af7ffff696d06a462932b054f66b04f&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201703%2F26%2F20170326161532_aGteC.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583645348270&di=4f9923fe4a1797ced33a87ee2a0ce84b&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201410%2F09%2F20141009224754_AswrQ.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583645348269&di=d711dd22720e2a69061475da605ae97c&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201704%2F10%2F20170410095843_SEvMy.thumb.700_0.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583645348268&di=4af9ee80ccc082f4ed4f6458a625f874&imgtype=0&src=http%3A%2F%2Fpic3.zhimg.com%2F50%2Fv2-c7abae8d49339ddbce5570141bc2cc04_hd.jpg"
    };

    private ActivityBindingAdapterBinding binding;
    private UserFieldModel userFieldModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_binding_adapter);
        //
        binding.setLifecycleOwner(this);
        userFieldModel = new UserFieldModel();

        binding.setUserInfo(userFieldModel);
        binding.setEventPresenter(new EventPresenter());
    }

    public class EventPresenter {
        public void loadRandImage() {
            int pos = new Random().nextInt(5);
            userFieldModel.avator.set(imgUrls[pos]);
        }
    }
}
