package ua.droidsft.picfetcher;

import android.content.Context;
import com.squareup.picasso.Picasso;


/**
 * Provider of Picasso instance.
 * Created by Vlad on 22.04.2016.
 */
public final class PicassoProvider {
    private static PicassoProvider sPicassoProvider;
    private static Picasso sPicasso;

    public static PicassoProvider get(Context context) {
        if (sPicassoProvider == null) {
            sPicassoProvider = new PicassoProvider(context);
        }
        return sPicassoProvider;
    }

    private PicassoProvider(Context context) {
        Context mContext = context.getApplicationContext();
        sPicasso = new Picasso.Builder(mContext)
                .build();
        Picasso.setSingletonInstance(sPicasso);
//        sPicasso.setIndicatorsEnabled(true);
    }

    public Picasso getPicasso() {
        return sPicasso;
    }
}
