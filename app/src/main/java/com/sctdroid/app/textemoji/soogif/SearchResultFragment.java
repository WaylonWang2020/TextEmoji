package com.sctdroid.app.textemoji.soogif;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.data.bean.Gif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lixindong on 5/16/17.
 */

public class SearchResultFragment extends Fragment {
    GifAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new GifAdapter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_result, container, false);

        initViews(root);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initViews(View root) {
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(mAdapter);
    }

    static class GifViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;

        private final ImageView item_image;

        public GifViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            item_image = (ImageView) itemView.findViewById(R.id.item_image);
        }
        public GifViewHolder(Context context, LayoutInflater inflater, ViewGroup parent) {
            this(context, inflater.inflate(R.layout.item_gif_gird, parent, false));
        }

        private Context getContext() {
            return mContext;
        }

        public void bind(Gif gif) {
            Glide.with(getContext())
                    .load(gif.url)
                    .into(item_image);
        }
    }

    static class GifAdapter extends RecyclerView.Adapter<GifViewHolder> {
        private final Context mContext;
        private List<Gif> mData = new ArrayList<>();

        public GifAdapter(Context context) {
            super();
            mContext = context;
        }

        private Context getContext() {
            return mContext;
        }

        @Override
        public GifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GifViewHolder(getContext(), LayoutInflater.from(getContext()), parent);
        }

        @Override
        public void onBindViewHolder(GifViewHolder holder, int position) {
            holder.bind(getItem(position));
        }

        private Gif getItem(int position) {
            return position < getItemCount() ? mData.get(position) : Gif.NULL;
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void updateData(List<Gif> data) {
            mData.clear();
            if (!Collections.EMPTY_LIST.equals(data)) {
                mData.addAll(data);
            }
            notifyDataSetChanged();
        }
    }
}
