package com.sctdroid.app.wallpapertodo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.show.api.ShowApiRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixindong on 4/13/17.
 */

public class HomeFragment extends Fragment {
    public static HomeFragment newInstance(String s){
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_content, container, false);

        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        final ContentAdapter adapter = new ContentAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String appid="35563";
                        String secret="10e933fd427b4eb58fe9b1f02963733e";
                        final String res=new ShowApiRequest("http://route.showapi.com/341-2", appid, secret)
                                .addTextPara("time", "")
                                .addTextPara("page", "")
                                .addTextPara("maxResult", "")
                                .post();

                        final List<Joke> result = new JokeParser().parse(res);

                        System.out.println(res);
                        //把返回内容通过handler对象更新到界面
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                refreshLayout.setRefreshing(false);
                                adapter.updateData(result);
                            }
                        }, 1000);
                    }
                }).start();
            }
        });
        return view;
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
            item_title.setText(joke.title);
            item_content.setText(joke.text);
            item_time.setText(joke.create_time);
            Glide.with(getContext())
                    .load(joke.image)
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