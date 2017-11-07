package be.pxl.stefvrijens.pokebattle.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by stefv on 06-Nov-17.
 */

public class ImageService {
//    public static Drawable getImageFromWeb(String url, Context context) {
//        try {
////            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
////            connection.setRequestProperty("User-agent","Mozilla/4.0");
////            connection.connect();
////            InputStream is = connection.getInputStream();
////            return new BitmapDrawable(context.getResources(), BitmapFactory.decodeStream(is));
//            System.out.println("Test: url: " + url);
//            InputStream is = (InputStream) new URL(url).getContent();
//            Drawable d = Drawable.createFromStream(is, "src");
//            return d;
//        } catch (Exception ex) {
//            System.err.println("Imageservice error: " + ex.getMessage());
//            return null;
//        }
//    }

    private Bitmap result;

    public void getImageFromWeb(String url, Context context, final ImageView imageView) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                result = Bitmap.createScaledBitmap(response, 100, 100, false);
                imageView.setImageBitmap(result);
            }
        }, 0,0, null, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.err.println("Imageservice error: " + error.getMessage());
            }
        });
        requestQueue.add(imageRequest);
    }

}
