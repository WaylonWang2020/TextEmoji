package com.sctdroid.app.wallpapertodo.jokes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sctdroid.app.wallpapertodo.Constants;
import com.sctdroid.app.wallpapertodo.R;
import com.sctdroid.app.wallpapertodo.data.bean.Joke;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixindong on 4/13/17.
 */

public class JokeFragment extends Fragment implements JokesContract.View {
    private JokesContract.Presenter mPresenter;
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ContentAdapter mContentAdapter;

    public static JokeFragment newInstance(String s){
        JokeFragment jokeFragment = new JokeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        jokeFragment.setArguments(bundle);
        return jokeFragment;
    }

    @Override
    public void showJokes(List<Joke> jokes) {
        mRefreshLayout.setRefreshing(false);
        mContentAdapter.updateData(jokes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_content, container, false);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mContentAdapter = new ContentAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mContentAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadJokes(false);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(JokesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static abstract class ViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        public ViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
        }

        protected abstract void bind(Joke bean);

        protected Context getContext() {
            return mContext;
        }
    }

    static class DefaultViewHolder extends ViewHolder {
        TextView item_title;
        TextView item_time;
        TextView item_content;
        ImageView item_image;

        public DefaultViewHolder(Context context, LayoutInflater inflater, ViewGroup parent) {
            super(context, inflater.inflate(R.layout.joke_item, parent, false));

            item_title = (TextView) itemView.findViewById(R.id.item_title);
            item_time = (TextView) itemView.findViewById(R.id.item_time);
            item_content = (TextView) itemView.findViewById(R.id.item_content);
            item_image = (ImageView) itemView.findViewById(R.id.item_image);
        }

        @Override
        protected void bind(Joke joke) {
            item_title.setText(joke.getTitle());
            item_content.setText(joke.getText());
            item_time.setText(joke.getCreateTime());
            Glide.with(getContext())
                    .load(joke.getImage())
                    .into(item_image);
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        private final Context mContext;
        private List<Joke> mData = new ArrayList<>();

        public ContentAdapter(Context context) {
            super();
            mContext = context;
        }
        public Context getContext() {
            return mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DefaultViewHolder(getContext(), LayoutInflater.from(getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(getItem(position));
        }

        private Joke getItem(int position) {
            return mData.size() > position ? mData.get(position) : Joke.NULL;
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void updateData(List<Joke> data) {
            mData.clear();
            if (data != null) {
                mData.addAll(data);
            }
            notifyDataSetChanged();
        }
    }
}