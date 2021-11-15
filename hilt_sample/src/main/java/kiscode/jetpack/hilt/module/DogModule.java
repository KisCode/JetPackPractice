package kiscode.jetpack.hilt.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import kiscode.jetpack.hilt.model.Dog;

@Module
@InstallIn(ActivityComponent.class)
public class DogModule {
    //使用 @Provides 注入实例
    @Provides
    public static Dog provideDog() {
        return new Dog("阿拉斯加犬");
    }
} 