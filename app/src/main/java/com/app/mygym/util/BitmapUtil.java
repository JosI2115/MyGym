package com.app.mygym.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.VectorDrawable;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import java.io.ByteArrayOutputStream;

public class BitmapUtil {
    /**
     * Representa una imagen por deault para el perfil
     */
    private static final String LOG_TAG = "BIT_MAP_UTIL";
    private Activity activity;


    public BitmapUtil(Activity activity) {
        this.activity = activity;
    }

    /**
     * Otiene el Bitmap de una imagen en String base 64.
     *
     * @param image Imagen en String
     * @return Bitmap Retorna el bit map de Imagen
     */
    public static Bitmap getBitmapImage(String image) {
        byte[] decodedString = getDecodedString(image);
        if (decodedString == null || decodedString.length == 0) {
            return null;
        } else {
            return decodeByteArrayToBitmap(decodedString);
        }
    }

    public static String bitmapToBase64JPEG(Bitmap imageBitmap) {
        //if(imageBitmap == null) return "";
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 20, outStream);
        byte[] byteArray = outStream.toByteArray();
        return deleteWhiteSpaces(Base64.encodeToString(byteArray, Base64.DEFAULT));
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static Bitmap decodeByteArrayToBitmap(byte[] decodedString) {
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static Bitmap decode(final Activity activity, int drawable_source){
        return BitmapFactory.decodeResource(activity.getResources(), drawable_source);
    }

    public static Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap compress(Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos);
        return to4BytesPerPixelBitmap(bitmap);
    }

    public static Bitmap to4BytesPerPixelBitmap(Bitmap input){
        final Bitmap bitmap = Bitmap.createBitmap(input.getWidth(), input.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(input, 0, 0, null);
        return bitmap;
    }

    /**
     * Valida el tamaño del Bitmap generado a partir del String codificado.
     *
     * @param originalBitmap Bitmap para Validar su autenticidad.
     * @return El tamaño valido.
     */
    private static int validRadio(Bitmap originalBitmap) {
        return originalBitmap.getWidth() > originalBitmap.getHeight() ?
                originalBitmap.getWidth() : originalBitmap.getHeight();
    }


    /**
     * Decodidifica el String  a  byte de la Imagen(String codificado).
     *
     * @param imageString String de Imagen codificado.
     * @return byte [] Retorna el String decodificado en Byte.
     **/
    public static byte[] getDecodedString(String imageString) {
        try {
            return Base64.decode(imageString, Base64.DEFAULT);
        } catch (Exception ex) {
            String errorInfo = "Error en la Estructura de  Base64.decode de Imagen Codificado. " + imageString;
            Log.e(LOG_TAG, errorInfo, ex);
            return null;
        }
    }

    /**
     * Obtiene Bitmap Circular de imagen en String
     *
     * @param image Imagen en String
     * @return Retorna el Bitmap de la Imagen.
     */
    public static Bitmap getCircularPhoto(String image) {
        return getCircularBitmap(getBitmapImage(image));
    }

    /**
     * Retorna un Bitmap en forma Circular, usado para representar imagen de Perfil de Usuario.
     *
     * @return Bitmap
     */
    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap output;
        int radio = validRadio(bitmap);
        output = Bitmap.createBitmap(radio, radio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        float r;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = bitmap.getHeight() / 2;
        } else {
            r = bitmap.getWidth() / 2;
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    /**
     * Obtiene la ancho de la pantalla del dispositivo en Pixels
     * Ej.
     * DeviceDimensionsHelper.getDisplayWidth(context) => (display width in pixels)
     *
     * @param context Contexto para obtener recursos
     * @return pixel Retorna el ancho de pantalla en pixel.
     */
    public static int getDisplayWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * Obtiene la altura de la pantalla del dispositivo en Pixels
     * Ej.
     * DeviceDimensionsHelper.getDisplayHeight(context) => (display height in pixels)
     *
     * @param context Contexto para obtener recursos
     * @return pixel Retorna la altura de pantalla en pixel.
     */
    public static int getDisplayHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     * Realiza el proceso de conversion de Density-independent Pixels(DP) a Pixeles
     * Ej.
     * DeviceDimensionsHelper.convertDpToPixel(25f, context) => (25dp converted to pixels)
     *
     * @param dp      recibe Density-independent Pixels(DP)
     * @param context Contexto para obtener recursos
     * @return pixel Retorna en pixel.
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    /**
     * Realiza el proceso de conversion de Pixeles a DP
     * Ej.
     * DeviceDimensionsHelper.convertPixelsToDp(25f, context) => (25px converted to dp)
     *
     * @param pixel   Pixel
     * @param context Contexto para obtener recursos
     * @return dp Retor la la dimesion.
     */
    public static float convertPixelsToDp(float pixel, Context context) {
        Resources r = context.getResources();
        DisplayMetrics metrics = r.getDisplayMetrics();
        float dp = pixel / (metrics.densityDpi / 160f);
        return dp;
    }


    /**
     * Realiza el procesamiento de Imagen a Escala
     *
     * @param bitmap Recibe el Bitmap original.
     * @return Bitmap Retorna la Imagen a Escala.
     */
    public static Bitmap getScaleImage(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float xScale = ((float) 200) / width;
        float yScale = ((float) 200) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static String convert(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        //HttpUtil.log(encodedResource);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP);
    }

    private static String deleteWhiteSpaces(String text){
        text = text.replaceAll("\\s+", "");
        text = text.replaceAll("\\n", "");
        text = text.replaceAll("\\r", "");
        return text;
    }

}