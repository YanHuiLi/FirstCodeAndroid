package site.yanhui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import site.yanhui.bean.Msg;
import site.yanhui.uibestpractice.R;

/**
 *
 * Created by Archer on 2017/8/21.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {



    private List<Msg> msgList;



    //继承viewHolder，实现内容的缓存
    static class ViewHolder  extends RecyclerView.ViewHolder{

       TextView leftMsg;
        TextView rightMsg;
        LinearLayout rightLayout;
        LinearLayout leftLayout;
        ImageView iv_left;
       ImageView iv_right;


        //写入缓存的viewholder
        public ViewHolder(View itemView) {
            super(itemView);
            leftMsg = (TextView)itemView.findViewById(R.id.left_msg);//可能有bug
            rightMsg =( TextView) itemView.findViewById(R.id.right_msg);
            leftLayout = (LinearLayout) itemView.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) itemView.findViewById(R.id.r_layout);
            iv_left = (ImageView) itemView.findViewById(R.id.iv_left);
            iv_right = (ImageView) itemView.findViewById(R.id.iv_right);
        }
    }

    //传入一个集合，存放msg的数据
    public MsgAdapter(List<Msg> msgList) {
        this.msgList = msgList;
    }


    //这里完成对viewHolder的构建
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);
    }


    //绑定viewholder
    @Override
    public void onBindViewHolder(MsgAdapter.ViewHolder holder, int position) {
        Msg msg=msgList.get(position);//得到当前msg实例
        //如果是收到消息则显示左边的布局，右边隐藏
        if (msg.getType()==Msg.TYPE_RECEIVED){
            holder.leftLayout.setVisibility(View.VISIBLE);//左边显示
            holder.iv_left.setImageResource(R.mipmap.ic_launcher);
            holder.rightLayout.setVisibility(View.GONE);//右边隐藏，不占空间和大小
            holder.leftMsg.setText(msg.getContent());//设置左边的内容
        }else if (msg.getType()==Msg.TYPE_SENT){
            //如果是发送消息，左边布局隐藏，显示右边布局
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
            holder.iv_right.setImageResource(R.mipmap.ic_launcher);
        }

    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
