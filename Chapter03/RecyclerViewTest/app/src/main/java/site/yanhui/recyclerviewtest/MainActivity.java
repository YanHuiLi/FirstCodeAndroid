package site.yanhui.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import site.yanhui.adapter.FruitAdapter;
import site.yanhui.bean.Fruit;

public class MainActivity extends AppCompatActivity {

    //初始化一个集合存
    private List<Fruit> mFruitList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();//初始化水果数据

        //拿到recyclerView的id
        RecyclerView  recyclerView= (RecyclerView) findViewById(R.id.recycler_view);

        //得到layoutManager对象，指定布局方式
//        LinearLayoutManager layoutManager= new LinearLayoutManager(this);

        //网格layoutManager有5列
//        GridLayoutManager  layoutManager = new GridLayoutManager(this,5,GridLayoutManager.VERTICAL,false);

//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL); //设置可以滑动的方向

        StaggeredGridLayoutManager  layoutManager =
                new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter fruitAdapter= new FruitAdapter(mFruitList);//初始化fruitAdapter
        recyclerView.setAdapter(fruitAdapter);//设置adapter

    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit Apple = new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic);
            mFruitList.add(Apple);
            Fruit Banana = new Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic);
            mFruitList.add(Banana);
            Fruit cherry = new Fruit(getRandomLengthName("cherry"), R.drawable.cherry_pic);
            mFruitList.add(cherry);
            Fruit grape = new Fruit(getRandomLengthName("grape"), R.drawable.grape_pic);
            mFruitList.add(grape);
            Fruit mango = new Fruit(getRandomLengthName("mango"), R.drawable.mango_pic);
            mFruitList.add(mango);
            Fruit orange = new Fruit(getRandomLengthName("orange"), R.drawable.orange_pic);
            mFruitList.add(orange);
            Fruit pear = new Fruit(getRandomLengthName("pear"), R.drawable.pear_pic);
            mFruitList.add(pear);
            Fruit pineapple = new Fruit(   getRandomLengthName("pineapple"), R.drawable.pineapple_pic);
            mFruitList.add(pineapple);
            Fruit strawberry = new Fruit(  getRandomLengthName("strawberry"), R.drawable.strawberry_pic);
            mFruitList.add(strawberry);
            Fruit watermelon = new Fruit(  getRandomLengthName("watermelon"), R.drawable.watermelon_pic);
            mFruitList.add(watermelon);
            Fruit watermelon1 = new Fruit( getRandomLengthName("watermelon") , R.drawable.watermelon_pic);
            mFruitList.add(watermelon1);

        }
    }


    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20) + 1; //得到一个1到20的随机整数
        StringBuilder builder= new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);//加入名字
        }
        return builder.toString();


    }
}
