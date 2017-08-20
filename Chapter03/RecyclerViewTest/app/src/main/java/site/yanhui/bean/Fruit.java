package site.yanhui.bean;

/**
 * Created by Archer on 2017/8/20.
 * Fruit的实体类
 */

public class Fruit {

    //有两个属性，图片和描述
    private String name;
    private int mImageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        mImageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }
}
