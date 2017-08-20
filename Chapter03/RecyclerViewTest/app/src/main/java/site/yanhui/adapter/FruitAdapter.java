package site.yanhui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import site.yanhui.bean.Fruit;
import site.yanhui.recyclerviewtest.R;

/**
 * Created by Archer on 2017/8/20.
 * 这里有个点特别需要注意，传入的必须是FruiAdapter.ViewHolder的泛型，
 * 如果忘记了，无法通过后面的步骤。
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;


    /*
    1.新建一个内部类viewholer继承字RecyclerView.ViewHolder
    2.使用Holder把我们的数据关联
     */
    static class ViewHolder extends  RecyclerView.ViewHolder{
        View fruitView;
        ImageView fruitImage;
        TextView  fruitName;

      public   ViewHolder(View itemView) {
            super(itemView);
           fruitView =  itemView;
          //这是有一个转型的问题，不知道是不是新的支持的问题
          fruitName =  itemView.findViewById(R.id.fruit_name);
          fruitImage = itemView.findViewById(R.id.iv_fruit);
      }
    }

    //FruitAdapter的构造，传入一个list数据源
    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //通过layoutInflater得到View的对象
        //R.layout.fruit_item_1 和R.layout.fruit_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);

        final ViewHolder holder =new ViewHolder(view);

        //设置最外层view的点击事件
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();//得到最外层的点击点
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you click view: "+ fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        //设置图片点击事件
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked image: "+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }


    //设置图片资源和描述
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit=mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    //返回最大数
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
