package site.yanhui.bean;

/**
 * Created by Archer on 2017/8/21.
 * 新建了一个Msg实体类
 *
 *content： 消息的内容
 * type ： 消息的类型（接收的还是，发出的）
 */

public class Msg {

    public static final  int TYPE_RECEIVED =0;
    public static final int  TYPE_SENT =1;


    private  String content;
    private  int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
