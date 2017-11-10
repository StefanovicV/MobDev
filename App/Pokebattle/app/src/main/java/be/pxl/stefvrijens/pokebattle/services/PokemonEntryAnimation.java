package be.pxl.stefvrijens.pokebattle.services;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

/**
 * Created by Medewerker on 10/11/2017.
 */

public class PokemonEntryAnimation extends Animation {
    private ImageView imageView;
    private int wantedSize;

    public PokemonEntryAnimation(ImageView iv, int wantedAlpha) {
        imageView = iv;
        this.wantedSize = wantedAlpha;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = imageView.getImageAlpha() + (wantedSize - imageView.getImageAlpha()) * interpolatedTime;
        imageView.setImageAlpha((int)value);
    }
}
