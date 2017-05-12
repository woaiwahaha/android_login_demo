package app.jy.com.login_demo.service;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.jy.com.login_demo.tools.StreamTools;

/**
 * Created by Administrator on 2017-5-11.
 */

public class FileService {
    private Context context;

    public FileService(Context context){
        this.context = context;
    }


    /**
     * 把用户名密码保存到手机ROM
     * @param username 用户名
     * @param password 密码
     * @param fileName 保存的文件名
     * @return
     */
    public boolean saveToRom(String username,String password,String fileName){
        try {
            //以私有的方式打开一个文件
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            String result = username+":"+password;
            fos.write(result.getBytes());
            fos.flush();
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            Log.i("没有文件",""+e);
        } catch (IOException e){
            Log.d("写出文件异常",""+e);
        }
        return false;
    }


    /**
     * 从文件中读取用户名和密码
     * @param fileName
     * @return
     */
    public Map<String,String> getUserInfo(String fileName){
        File file = new File("data/data/app.jy.com.login_demo/files/"+fileName);
        try{
           FileInputStream fis = new FileInputStream(file);
            byte[] data = StreamTools.getBytes(fis);
            String result = new String(data);
            String[] results = result.split(":");
            Map<String,String> map = new HashMap<>();
            map.put("username",results[0]);
            map.put("password",results[1]);
            return map;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
