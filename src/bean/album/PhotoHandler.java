package bean.album;

import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;
/**
 * @desc:图片处理�?
 *BLOG:http://blog.sina.com.cn/cuil11
 * @date: 2015�?7�?3�? 下午4:40:54
 * QQ2050542273
 * @email:15162925211@163.com
 */
public class PhotoHandler {
    /**
     * 读取图片属�?�：旋转的角�?
   
     *            图片绝对路径
     * @return degree旋转的角�?
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            Log.e("fff", e.getMessage());
        }
        return degree;
    }

    /*
     * 旋转图片
     * 
     * @param angle
     * 
     * @param bitmap
     * 
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        if (null == bitmap) {
            return null;
        }
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }
}
