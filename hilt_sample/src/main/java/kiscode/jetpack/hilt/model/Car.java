package kiscode.jetpack.hilt.model;

import javax.inject.Inject;

public class Car {
    private Engine engine;

    @Inject
    public Car(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }
}