package top.suvvm.nilmusic.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ClassName: DattaUtils
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/31 21:02
 */
public class DataUtils {
    // 读取资源文件中的json
    public static String getJsonFromAssets(Context context, String fileName) {
        // 用StringBuilder存放读取出的数据
        StringBuilder stringBuilder = new StringBuilder();
        // AssetManager Open指定资源文件位InputStream
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // 用bufferedReader速度比Scanner快些
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            // 循环读取行
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
