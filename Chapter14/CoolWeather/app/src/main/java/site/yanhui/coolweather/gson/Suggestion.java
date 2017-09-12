package site.yanhui.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Archer on 2017/9/12.
 * <p>
 * 功能描述：
 "suggestion":{
 "air":{
 "brf":"较差",
 "txt":"气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"
 },
 "comf":{
 "brf":"舒适",
 "txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"
 },
 "cw":{
 "brf":"较适宜",
 "txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"
 },
 "drsg":{
 "brf":"舒适",
 "txt":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"
 },
 "flu":{
 "brf":"少发",
 "txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"
 },
 "sport":{
 "brf":"适宜",
 "txt":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"
 },
 "trav":{
 "brf":"适宜",
 "txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"
 },
 "uv":{
 "brf":"中等",
 "txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"
 }
 }
 */

public class Suggestion {

    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;


    public class Sport{
        @SerializedName("txt")
        public String info;
    }

    public class CarWash{
        @SerializedName("txt")
        public String info;
    }

    public class Comfort{
        @SerializedName("txt")
        public String info;
    }

}
