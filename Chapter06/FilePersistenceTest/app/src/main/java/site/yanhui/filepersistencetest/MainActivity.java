package site.yanhui.filepersistencetest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 持久化技术的学习
 * 使用File 文件的储存和读取
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.Edit_text)
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String inputText=Load();
        //判断字符串是否为空的时候，采用这个方法，可以一次判断两种空值
        //1.当传入的字符串等于null，或者等于空字符串的时候，这个方法都会返回true。
        if (!TextUtils.isEmpty(inputText)){
            
            et.setText(inputText);
            et.setSelection(inputText.length()); //将光标放在字符串的末尾

            Toast.makeText(this, "Restoring Succeeded！", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在执行onDestroy的方法时候执行数据的本地化。
        String inputText=et.getText().toString(); //得到存储在editText里面的字符串
        save(inputText);

    }

    public void save(String inputText) {
        //初始化两个流对象，进行写进本地的操作
        FileOutputStream out=null;
        BufferedWriter writer=null;

        try {
            //文件创建在data/data/包名/files/data.xml
            out=openFileOutput("data.xml", Context.MODE_PRIVATE);//关联一个名字为data的文件夹，没有会自动创建
            writer=new BufferedWriter(new OutputStreamWriter(out));//使用包装类，得到一个可以写入的对象writer。
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer!=null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 1.写一个方法的时候，需要考虑返回值是什么类型
     * 2.需要传入参数吗？
     * 3.其次就是方法实现语句
     */


    public String Load(){
        //我们需要的是文件的输入流，因为是要读取文件
        FileInputStream in= null;
        BufferedReader reader= null;
        StringBuffer content=  new StringBuffer();//用一个容器来装字符串

        try {
            in=openFileInput("data.xml");//关联文件内容
            reader=new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return content.toString();
    }
}
