package kiscode.jetpack.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import kiscode.jetpack.hilt.model.Cat;
import kiscode.jetpack.hilt.model.RedCat;

/**
 * Description: 为同一类型提供多个绑定
 * Date : 2021/11/15 16:01
 **/
@Module
@InstallIn(ActivityComponent.class)
public class CatModule {

    @MakeRedCat
    @Provides
    public static Cat provideRedCat() {
        return new RedCat("虹猫");
    }

    @MakeBlueCat
    @Provides
    public static Cat provideBlueCat() {
        return new RedCat("蓝猫");
    }
} 