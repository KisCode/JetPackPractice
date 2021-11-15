package kiscode.jetpack.hilt.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import kiscode.jetpack.hilt.model.ChinesEngine;
import kiscode.jetpack.hilt.model.Engine;

@Module
@InstallIn(ActivityComponent.class)
public interface EngineModule {
    //使用 @Binds 指定注入具体类型
    @Binds
    Engine bindEngine(ChinesEngine engine);
}