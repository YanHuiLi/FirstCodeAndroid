package site.yanhui.materialtest.bean;

/**
 * Created by Archer on 2017/9/7.
 * <p>
 * 功能描述：
 * 简历一个fruit的实体类
 * 1.有名字
 * 2.有图片
 */

public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fruit fruit = (Fruit) o;

        return imageId == fruit.imageId && (name != null ? name.equals(fruit.name) : fruit.name == null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + imageId;
        return result;
    }
}
