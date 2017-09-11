package site.yanhui.networktest.bean;

/**
 * Created by Archer on 2017/9/10.
 * <p>
 * 功能描述：
 * Gson最牛的一点就是，可以把jsonobject的字段映射成一个对象，直接使用
 * app类就是映射的bean
 */

public class AppBean {


        private  String id;
        private String name;
        private String version;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }


