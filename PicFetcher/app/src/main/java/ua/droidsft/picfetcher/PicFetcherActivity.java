package ua.droidsft.picfetcher;

import android.support.v4.app.Fragment;

/**
 * The Activity for holding PicFetcherFragment.
 * Created by Vlad on 07.05.2016.
 */
public class PicFetcherActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PicFetcherFragment.newInstance();
    }
}
