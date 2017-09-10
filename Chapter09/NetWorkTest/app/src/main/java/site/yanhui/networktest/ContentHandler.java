package site.yanhui.networktest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Archer on 2017/9/10.
 * <p>
 * 功能描述：
 * 使用sax的方式解析数据
 */

public class ContentHandler extends DefaultHandler {
    private static final String TAG = "ContentHandler";

   private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;

    @Override
    public void startDocument() throws SAXException {
        id= new StringBuilder();
        name =new StringBuilder();
        version=new StringBuilder();
    }

    //开始的元素
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //记录当前结点的名字
        nodeName=localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //根据当前节点的点判断内容应该添加到哪一个stringbuilder对象中
        if ("id".equals(nodeName)) {
            id.append(ch,start,length);
        }else if ("name".equals(nodeName)){
            name.append(ch, start, length);
        }else if ("Version".equals(nodeName)){
            version.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(localName)){
            Log.d(TAG, "endElement: id is "+id.toString().trim());
            Log.d(TAG, "endElement: name is "+name.toString().trim());
            Log.d(TAG, "endElement: version is "+version.toString().trim());
            //最后将stringBuilder清空
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
