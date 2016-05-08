package ua.droidsft.picfetcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * The main Fragment class.
 * Created by Vlad on 07.05.2016.
 */
public class PicFetcherFragment extends Fragment {
    private ImageView mImageView;

    public static PicFetcherFragment newInstance() {
        return new PicFetcherFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pic_fetcher, container, false);

        mImageView = (ImageView) v.findViewById(R.id.image);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getActivity().getIntent();
        // If launched from PicURL app, try to load image with Picasso,
        // otherwise show info toast.
        if (intent.getAction().equals(Constants.ACTION_LOAD)) {
            String url = intent.getStringExtra(Constants.EXTRAS_URL);
            if (url == null || url.equals("")) {
                showToast(getString(R.string.no_url));
                return;
            }
            PicassoProvider.get(getActivity()).getPicasso()
                    .load(url)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_loading_error)
                    .into(mImageView);
        } else {
            showToast(getString(R.string.start_hint));
        }
    }

    private void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }
}
