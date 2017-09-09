package site.yanhui.materialtest.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import site.yanhui.materialtest.R;
import site.yanhui.materialtest.bean.Fruit;

/**
 * Created by Archer on 2017/9/7.
 * <p>
 * 功能描述：
 * 新建一个fruitAdapter进行数据的适配
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;
    private Context mContext;

    //创建一个内部类ViewHolder，用于缓存数据
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView fruitImage;
        TextView fruitName;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView;
            fruitImage = (ImageView) itemView.findViewById(R.id.fruit_Image);
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

    //需要从传入一个list集合存放数据
    public FruitAdapter(List<Fruit> mList) {
        this.mFruitList = mList;
    }

    //解析viewHolder并传入view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext==null) {
            mContext=parent.getContext();
        }

        View view= LayoutInflater.from(mContext).inflate(R.layout.fruit_item_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit= mFruitList.get(position);//得到当前的fruit实例
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);//glide 加载图片
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }


}
