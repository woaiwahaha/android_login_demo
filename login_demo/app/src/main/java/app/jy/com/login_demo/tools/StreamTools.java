package app.jy.com.login_demo.tools;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017-5-11.
 */

public class StreamTools {

    /**
     * 把inputstream中的内容读出来，返回一个byte数组
     * @param is
     * @return
     */
    public static byte[] getBytes(InputStream is){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            baos.close();
            return baos.toByteArray();
        }catch (IOException e){
            Log.i("读取文件错误",e.toString());
        }
        return null;
    }
}
