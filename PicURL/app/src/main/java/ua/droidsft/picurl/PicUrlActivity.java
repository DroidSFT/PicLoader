package ua.droidsft.picurl;

import android.support.v4.app.Fragment;

/**
 * The Activity class for PicUrlFragment.
 * Created by Vlad on 07.05.2016.
 */
public class PicUrlActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PicUrlFragment.newInstance();
    }

}
