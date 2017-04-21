package com.sctdroid.app.textemoji.emoji;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sctdroid.app.textemoji.data.bean.ChatItem;

/**
 * Created by lixindong on 4/20/17.
 */

public abstract class BaseEmojiViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    protected EventDelegate mDelegate;

    public BaseEmojiViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
    }

    protected abstract void bind(ChatItem chat);

    protected Context getContext() {
        return mContext;
    }

    public interface EventDelegate {
        boolean onContentLongClicked(View view, Object data);
    }

    public void setEventDelegate(EventDelegate delegate) {
        mDelegate = delegate;
    }
}
