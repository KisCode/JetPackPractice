package kiscode.jetpack.hilt.model;

/**
* Description: Dog类 替代第三方库，不归项目所有，无法@inject ，则使用@provides
* Date : 2021/11/15 15:22
**/
public class Dog {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}