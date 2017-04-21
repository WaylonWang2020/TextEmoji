package com.sctdroid.app.textemoji.developer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.utils.Constants;

/**
 * Created by lixindong on 4/21/17.
 */

public class DeveloperFragment extends Fragment implements View.OnClickListener {
    public static DeveloperFragment newInstance() {
        return new DeveloperFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_developer, container, false);

        // do initial things here
        initHeadBar(root);
        initEvent(root);

        return root;
    }

    private void initEvent(View root) {
        View weibo_container = root.findViewById(R.id.weibo_container);
        View github_container = root.findViewById(R.id.github_container);
        View open_source_container = root.findViewById(R.id.open_source_container);

        weibo_container.setOnClickListener(this);
        github_container.setOnClickListener(this);
        open_source_container.setOnClickListener(this);
    }

    private void initHeadBar(View root) {
        TextView title = (TextView) root.findViewById(R.id.title);
        ImageView left_option = (ImageView) root.findViewById(R.id.left_option);
        ImageView right_option = (ImageView) root.findViewById(R.id.right_option);
        title.setText(R.string.string_developer);
        left_option.setVisibility(View.GONE);
        right_option.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        String url = "";
        switch (v.getId()) {
            case R.id.weibo_container:
                url = Constants.URL_WEIBO;
                break;
            case R.id.github_container:
                url = Constants.URL_GITHUB;
                break;
            case R.id.open_source_container:
                url = Constants.URL_OPEN_SOURCE;
                break;
            default:
                return;
        }
        openInBrowser(url);
    }

    private void openInBrowser(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        startActivity(Intent.createChooser(intent, "请选择浏览器"));
    }
}
