package site.yanhui.servicebestpractice.listener;

/**
 * Created by Archer on 2017/9/4.
 * <p>
 * 功能描述：
 * 定义一个回调接口，用于对下载过程中的各种状态进行回调和监听
 */

public interface DownloadListener {
    void onProgress(int progress);//通知当前进度
    void onSuccess();//下载成功
    void onPause();//暂停
    void onCanceled();//取消
    void onFailed();//失败
}
