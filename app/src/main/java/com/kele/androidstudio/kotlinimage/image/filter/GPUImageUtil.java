package com.kele.androidstudio.kotlinimage.image.filter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageAddBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageAlphaBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageBilateralFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageBoxBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageBulgeDistortionFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageCGAColorspaceFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageChromaKeyBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorBalanceFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorDodgeBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorMatrixFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDifferenceBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDilationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDirectionalSobelEdgeDetectionFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDissolveBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageEmbossFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageExclusionBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageExposureFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFalseColorFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGammaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGaussianBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGlassSphereFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHazeFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHighlightShadowFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHueBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHueFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageKuwaharaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLaplacianFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLevelsFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLightenBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageMonochromeFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageNonMaximumSuppressionFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageOpacityFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageOverlayBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImagePixelationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageRGBDilationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSketchFilter;


public class GPUImageUtil {

    private static GPUImageFilter filter;
    private static int count;

    /**
     * 获取过滤器
     *
     * @param GPUFlag
     * @return 滤镜类型
     */
    public static GPUImageFilter getFilter(int GPUFlag) {
        switch (GPUFlag) {
            case 1:
                filter = new GPUImageGrayscaleFilter();
                break;
            case 2:
                filter = new GPUImageAddBlendFilter();
                break;
            case 3:
                filter = new GPUImageAlphaBlendFilter();
                break;
            case 4:
                filter = new GPUImageBilateralFilter();
                break;
            case 5:
                filter = new GPUImageBoxBlurFilter();
                break;
            case 6:
                filter = new GPUImageBrightnessFilter();
                break;
            case 7:
                filter = new GPUImageBulgeDistortionFilter();
                break;
            case 8:
                filter = new GPUImageCGAColorspaceFilter();
                break;
            case 9:
                filter = new GPUImageChromaKeyBlendFilter();
                break;
            case 10:
                filter = new GPUImageColorBalanceFilter();
                break;
            case 11:
                filter = new GPUImageColorBlendFilter();
                break;
            case 12:
                filter = new GPUImageEmbossFilter();
                break;
            case 13:
                filter = new GPUImageColorDodgeBlendFilter();
                break;
            case 14:
                filter = new GPUImageColorInvertFilter();
                break;
            case 15:
                filter = new GPUImageColorMatrixFilter();
                break;
            case 16:
                filter = new GPUImageContrastFilter();
                break;
            case 17:
                filter = new GPUImageExclusionBlendFilter();
                break;
            case 18:
                filter = new GPUImageExposureFilter();
                break;
            case 19:
                filter = new GPUImageDifferenceBlendFilter();
                break;
            case 20:
                filter = new GPUImageDilationFilter();
                break;
            case 21:
                filter = new GPUImageDirectionalSobelEdgeDetectionFilter();
                break;
            case 22:
                filter = new GPUImageDissolveBlendFilter();
                break;
            case 23:
                filter = new GPUImageOverlayBlendFilter();
                break;
            case 24:
                filter = new GPUImageFalseColorFilter();
                break;
            case 25:
                filter = new GPUImageGammaFilter();
                break;
            case 26:
                filter = new GPUImageGaussianBlurFilter();
                break;
            case 27:
                filter = new GPUImageGlassSphereFilter();
                break;
            case 28:
                filter = new GPUImageOpacityFilter();
                break;
            case 29:
                filter = new GPUImagePixelationFilter();
                break;
            case 30:
                filter = new GPUImageHazeFilter();
                break;
            case 31:
                filter = new GPUImageHighlightShadowFilter();
                break;
            case 32:
                filter = new GPUImageHueBlendFilter();
                break;
            case 33:
                filter = new GPUImageHueFilter();
                break;
            case 34:
                filter = new GPUImageKuwaharaFilter();
                break;
            case 35:
                filter = new GPUImageLaplacianFilter();
                break;
            case 36:
                filter = new GPUImageLevelsFilter();
                break;
            case 37:
                filter = new GPUImageLightenBlendFilter();
                break;
            case 38:
                filter = new GPUImageNonMaximumSuppressionFilter();
                break;
            case 39:
                filter = new GPUImageMonochromeFilter();
                break;
            case 40:
                filter = new GPUImageRGBDilationFilter();
                break;
            case 41:
                filter = new GPUImageSketchFilter();
                break;
            case 42:
                filter = new GPUImageSaturationFilter(count);
                break;
        }
        return filter;
    }

    /**
     * 从Assets文件夹中获取图片
     *
     * @param context    上下文
     * @param gpuImage   GPUImage对象
     * @param FilterFlag 滤镜类型
     * @return
     */
    public static Bitmap getGPUImageFromAssets(Context context, GPUImage gpuImage, int FilterFlag) {
        AssetManager as = context.getAssets();
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            is = as.open("link.jpg");
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            Log.e("GPUImage", "Error");
        }

        // 使用GPUImage处理图像
        gpuImage = new GPUImage(context);
        gpuImage.setImage(bitmap);
        gpuImage.setFilter(getFilter(FilterFlag));
        bitmap = gpuImage.getBitmapWithFilterApplied();
        return bitmap;
    }

    /**
     * 从网络中获取图片
     *
     * @param url
     * @return
     */
    public static Bitmap getGPUImageFromURL(String url) {
        Bitmap bitmap = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bitmap = BitmapFactory.decodeStream(bis);
            // 关闭流
            bis.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 调整饱和度等指数
     *
     * @param curCount
     */
    public static void changeSaturation(int curCount) {
        GPUImageUtil.count = curCount;
    }


}
