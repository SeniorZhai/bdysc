package com.baida.util;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;

public class ImageUtil {
	
	  /**
     * 图片缩放
     * 
     * @param bitmap
     * @param width
     * @param height
     * 
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
            if (bitmap == null) {
                    return null;
            }
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            Matrix matrix = new Matrix();
            float scaleWidth = ((float) width / w);
            float scaleHeight = ((float) height / h);
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
            return newbmp;
    }

    /**
     * 添加阴影
     * 
     * @param originalBitmap
     * 
     */
    public static Bitmap drawImageDropShadow(Bitmap originalBitmap,Context context) {

            BlurMaskFilter blurFilter = new BlurMaskFilter(1,
                            BlurMaskFilter.Blur.NORMAL);
            Paint shadowPaint = new Paint();
            shadowPaint.setAlpha(50);
             
     
            shadowPaint.setColor(Color.RED);
            shadowPaint.setMaskFilter(blurFilter);
             
            int[] offsetXY = new int[2];
            Bitmap shadowBitmap = originalBitmap
                            .extractAlpha(shadowPaint, offsetXY);

            Bitmap shadowImage32 = shadowBitmap.copy(Bitmap.Config.ARGB_8888, true);
            Canvas c = new Canvas(shadowImage32);
            c.drawBitmap(originalBitmap, offsetXY[0], offsetXY[1], null);

            return shadowImage32;
    }

    /**
     * 转换图片成圆形
     * 
     * @param bitmap
     *            传入Bitmap对象
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float roundPx;
            float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
            if (width <= height) {
                    roundPx = width / 2;

                    left = 0;
                    top = 0;
                    right = width;
                    bottom = width;

                    height = width;

                    dst_left = 0;
                    dst_top = 0;
                    dst_right = width;
                    dst_bottom = width;
            } else {
                    roundPx = height / 2;

                    float clip = (width - height) / 2;

                    left = clip;
                    right = width - clip;
                    top = 0;
                    bottom = height;
                    width = height;

                    dst_left = 0;
                    dst_top = 0;
                    dst_right = height;
                    dst_bottom = height;
            }

            Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final Paint paint = new Paint();
            final Rect src = new Rect((int) left, (int) top, (int) right,
                            (int) bottom);
            final Rect dst = new Rect((int) dst_left, (int) dst_top,
                            (int) dst_right, (int) dst_bottom);
            final RectF rectF = new RectF(dst);

            paint.setAntiAlias(true);// 设置画笔无锯齿

            canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas

            // 以下有两种方法画圆,drawRounRect和drawCircle
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。
            // canvas.drawCircle(roundPx, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452
            canvas.drawBitmap(bitmap, src, dst, paint); // 以Mode.SRC_IN模式合并bitmap和已经draw了的Circle

            return output;
    }

    /**
     * 圆角图片
     * 
     * @param bitmap
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                            bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(rect);
            final float roundPx = 12;

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);

            return output;

    }

    /***
     * 设置图片倒影
     * 
     * @param originalBitmap
     * @return
     */
    public static Bitmap createReflectedImage(Bitmap originalBitmap) {
            // 图片与倒影间隔距离
            final int reflectionGap = 4;

            // 图片的宽度
            int width = originalBitmap.getWidth();
            // 图片的高度
            int height = originalBitmap.getHeight();

            Matrix matrix = new Matrix();
            // 图片缩放，x轴变为原来的1倍，y轴为-1倍,实现图片的反转
            matrix.preScale(1, -1);
            // 创建反转后的图片Bitmap对象，图片高是原图的一半。
            Bitmap reflectionBitmap = Bitmap.createBitmap(originalBitmap, 0,
                            height / 2, width, height / 2, matrix, false);
            // 创建标准的Bitmap对象，宽和原图一致，高是原图的1.5倍。
            Bitmap withReflectionBitmap = Bitmap.createBitmap(width, (height
                            + height / 2 + reflectionGap), Config.ARGB_8888);

            // 构造函数传入Bitmap对象，为了在图片上画图
            Canvas canvas = new Canvas(withReflectionBitmap);
            // 画原始图片
            canvas.drawBitmap(originalBitmap, 0, 0, null);

            // 画间隔矩形
            Paint defaultPaint = new Paint();
            canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);

            // 画倒影图片
            canvas.drawBitmap(reflectionBitmap, 0, height + reflectionGap, null);

            // 实现倒影效果
            Paint paint = new Paint();
            LinearGradient shader = new LinearGradient(0,
                            originalBitmap.getHeight(), 0,
                            withReflectionBitmap.getHeight(), 0x70ffffff, 0x00ffffff,
                            TileMode.MIRROR);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));

            // 覆盖效果
            canvas.drawRect(0, height, width, withReflectionBitmap.getHeight(),
                            paint);

            return withReflectionBitmap;
    }
}


