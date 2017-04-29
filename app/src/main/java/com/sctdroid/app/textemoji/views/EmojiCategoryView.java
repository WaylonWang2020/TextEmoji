package com.sctdroid.app.textemoji.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.data.bean.Emoji;
import com.sctdroid.app.textemoji.data.bean.EmojiCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lixindong on 4/28/17.
 */

public class EmojiCategoryView extends FrameLayout {
    public EmojiCategoryView(Context context) {
        this(context, null);
    }

    public EmojiCategoryView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiCategoryView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private ContentAdapter mAdapter;
    void init() {
        inflate(getContext(), R.layout.recycler_view, this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter = new ContentAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
    }

    public void bind(EmojiCategory category) {
        mAdapter.updateData(category.data);
    }

    public void setOnItemClickListener(ContentAdapter.OnItemClickListener listener) {
        mAdapter.setOnItemClickListener(listener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private final TextView item_emoji;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            item_emoji = (TextView) itemView.findViewById(R.id.item_emoji);
        }
        public ViewHolder(Context context, LayoutInflater inflater, ViewGroup parent) {
            this(context, inflater.inflate(R.layout.emoji_item, parent, false));
        }

        public void bind(Emoji item) {
            item_emoji.setText(item.emoji);
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        private final Context mContext;
        private List<Emoji> mData = new ArrayList<>();

        private OnItemClickListener mOnItemClickListener;

        public ContentAdapter(Context context) {
            super();
            mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mContext, LayoutInflater.from(mContext), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Emoji emoji = getItem(position);
            holder.bind(emoji);
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClicked(v, emoji);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public Emoji getItem(int position) {
            return position < mData.size() ? mData.get(position) : Emoji.NULL;
        }

        public void updateData(List<Emoji> data) {
            mData.clear();
            if (!Collections.EMPTY_LIST.equals(data)) {
                mData.addAll(data);
            }
            notifyDataSetChanged();
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            mOnItemClickListener = listener;
        }

        public interface OnItemClickListener {
            void onItemClicked(View view, Emoji emoji);
        }
    }
}
