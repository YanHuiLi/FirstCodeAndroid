package site.yanhui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import site.yanhui.bean.Fruit;
import site.yanhui.listviewtest.R;

/**
 * Created by Archer on 2017/8/20.
 *使用FruitAdapter继承自ArrayAdapter
 * 完成数据的适配器
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {
    /*
    为什么要单独提出来的这个resourceId？
    这个resourceId就是绑定的以后初始化FriutAdapter里面传入的fruit_item的id
    只有把这个id单独抽取出来，才能在后面的getView中使用
    LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
    即是.inflate(resourceId, parent, false),如果不那么做，view对象无法生成。
    所以把resourceId 单独抽取出来，因为用layoutInflater的inflate方法中还要使用。
     */
    private int resourceId;
    private View mView;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public FruitAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Fruit> objects) {
        super(context, resource, objects);
       resourceId=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Fruit fruit=getItem(position); //获取当前的fruit实例

        /*
        三个参数，第一个获得当前的context，inflate的三个参数，
        1.resourceId其实就是以后要传进去的fruit_item的id（因为要写成通用的）
        2.父类控件
        3.第三个参数如果设置为true就是为当前控件添加父布局，如果添加，就不能再加入到listview里面了。违例
           选择成false是一个标准写法，表示只是为了让我们在父布局中的layout属性生效，但是不添加父布局

        使用convertView提高效率
        使用ViewHolder提高效率，缓存实例
         */

        View mView;
        viewHolder mViewHolder;
        if (convertView==null){
              mView= LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
              mViewHolder= new viewHolder();
               mViewHolder.fruitView= mView.findViewById(R.id.image_view);
            mViewHolder.fruitName=mView.findViewById(R.id.fruit_name);
             mView.setTag(mViewHolder); //将ViewHolder存储在mView中
        }else {
            mView=convertView;
            mViewHolder = (viewHolder) mView.getTag();//重新获取viewHolder
        }


//        ImageView FruitImage=mView.findViewById(R.id.image_view);
//        TextView FruitName = mView.findViewById(R.id.fruit_name);

//        FruitImage.setImageResource(fruit.getImageId());//设置图片
//        FruitName.setText(fruit.getName());

        mViewHolder.fruitName.setText(fruit.getName());
        mViewHolder.fruitView.setImageResource(fruit.getImageId());

        return mView;
    }

    class viewHolder {

        ImageView fruitView;
        TextView   fruitName;

    }
}
