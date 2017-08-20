package site.yanhui.listviewtest;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import site.yanhui.adapter.FruitAdapter;
import site.yanhui.bean.Fruit;

/*
使用listView填装数据
 */
public class MainActivity extends AppCompatActivity {
    //初始化一个数组对象
    /*

    private  String[] data= {
            "Apple","Banana","Orange","Watermelon",
            "Pear","Grape","Pineapple","Strawberry",
            "Cherry","Mango","Banana","Orange","Watermelon",
            "Pear","Grape","Pineapple","Strawberry",
            "Cherry","Mango"
    };
     */

    //面向接口编程，初始化一个fruitList集合来存储数据
    private List<Fruit>  mFruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar= getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }



        initFruits(); //初始化数据
        ListView listView= (ListView) findViewById(R.id.list_view);
        FruitAdapter mAdapter= new FruitAdapter(MainActivity.this,R.layout.fruit_item,mFruitList);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fruit fruit= mFruitList.get(position);//得到当前点击的水果实例
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        /*
        1.第一个参数传入当前的context
        2.传入android.R包下的simple_list_item_1 文件
        3.传入第一对象集当作数据源
        ArrayAdapter<String> mArrayAdapter= new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                data);

        listView.setAdapter(mArrayAdapter);
         */

    }

    //初始化fruit集合数据
    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit Apple = new Fruit("Apple",R.drawable.apple_pic);
            mFruitList.add(Apple);
            Fruit  Banana= new Fruit("Banana",R.drawable.banana_pic);
            mFruitList.add(Banana);
            Fruit  cherry= new Fruit("cherry",R.drawable.cherry_pic);
            mFruitList.add(cherry);
            Fruit grape = new Fruit("grape",R.drawable.grape_pic);
            mFruitList.add(grape);
            Fruit  mango= new Fruit("mango",R.drawable.mango_pic);
            mFruitList.add(mango);
            Fruit  orange= new Fruit("orange",R.drawable.orange_pic);
            mFruitList.add(orange);
            Fruit pear = new Fruit("pear",R.drawable.pear_pic);
            mFruitList.add(pear);
            Fruit  pineapple= new Fruit("pineapple",R.drawable.pineapple_pic);
            mFruitList.add(pineapple);
            Fruit  strawberry= new Fruit("strawberry",R.drawable.strawberry_pic);
            mFruitList.add(strawberry);
            Fruit watermelon = new Fruit("watermelon",R.drawable.watermelon_pic);
            mFruitList.add(watermelon);

        }


    }
}
