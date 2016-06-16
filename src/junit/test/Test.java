package junit.test;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 宇强 on 2016/3/14 0014.
 */
public class Test {

    public static void main(String[] args){
        /*
        Pattern pattern = Pattern.compile("/blog/([0-9]+)/([0-9]+)-([0-9]+)");
        Matcher matcher = pattern.matcher("http://coselding.cn/MyBlog/blog/1/3-124.html");
        System.out.println(matcher.matches());
        System.out.println(matcher.find());
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(3));
        */
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        System.out.print(format.format(date));

        try {
            FileInputStream fis = new FileInputStream("D:\\1.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(fis,baos);
            String content = new String(baos.toByteArray(),"GBK");
            //System.out.println(content);

            if(content.contains("<html>")) {
                Pattern p = Pattern.compile("<body>([\\s\\S]*)</body>");
                Matcher matcher = p.matcher(content);
                if(matcher.find()){
                    System.out.println(matcher.group(1));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
