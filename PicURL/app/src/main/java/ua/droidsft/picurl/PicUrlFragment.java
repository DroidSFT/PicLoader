package ua.droidsft.picurl;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The main Fragment class.
 * Created by Vlad on 07.05.2016.
 */
public class PicUrlFragment extends Fragment {
    private EditText mEditText;

    public static PicUrlFragment newInstance() {
        return new PicUrlFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pic_url, container, false);

        mEditText = (EditText) v.findViewById(R.id.url_edit_text);

        // Handle IME enter action.
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    loadPic();
                    return true;
                } else {
                    return false;
                }
            }
        });

        Button loadButton = (Button) v.findViewById(R.id.btn_load);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPic();
            }
        });

        Button testButton = (Button) v.findViewById(R.id.btn_test);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(getString(R.string.test_url));
            }
        });

        return v;
    }

    // Perform basic URL check and start PicFetcher app (if available).
    private void loadPic() {
        String url = mEditText.getText().toString();
        if (!URLUtil.isValidUrl(url)) {
            showToast(getString(R.string.invalid_url));
            return;
        }

        Intent intent = new Intent(Constants.ACTION_LOAD);
        intent.putExtra(Constants.EXTRAS_URL, url);

        ActivityInfo info = intent.resolveActivityInfo(getActivity().getPackageManager(), 0);
        if (info != null && info.exported) {
            startActivity(intent);
        } else {
            showToast(getString(R.string.no_fetcher_app));
        }
    }

    private void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

}
