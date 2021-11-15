package kiscode.jetpack.hilt.model;

import javax.inject.Inject;

public class User {
    public String name;

    @Inject
    public User() {
        this.name = "Hilt";
    }
}