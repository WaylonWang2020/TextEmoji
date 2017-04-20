package com.sctdroid.app.wallpapertodo.emoji;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sctdroid.app.wallpapertodo.R;
import com.sctdroid.app.wallpapertodo.data.bean.ChatItem;
import com.sctdroid.app.wallpapertodo.views.TextEmoji;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixindong on 4/18/17.
 */

public class EmojiFragment extends Fragment implements EmojiContract.View, BaseEmojiViewHolder.EventDelegate {
    private ContentAdapter mAdapter;
    private EmojiContract.Presenter mPresenter;
    private TextInputEditText mTextInputEditText;
    private RecyclerView mRecyclerView;
    private int mFontSize;
    private boolean mWithShadow;

    @Override
    public void setPresenter(EmojiContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static EmojiFragment newInstance() {
        return new EmojiFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init adapter here
        mAdapter = new ContentAdapter(getActivity(), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_emoji, container, false);

        // do initial things here
        initHeadBar(root);
        initRecyclerView(root);
        initEvent(root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void initEvent(final View root) {
        final TextView sendButton = (TextView) root.findViewById(R.id.send_button);
        final ImageView switchButton = (ImageView) root.findViewById(R.id.switch_button);
        final CardView options = (CardView) root.findViewById(R.id.options);
        mTextInputEditText = (TextInputEditText) root.findViewById(R.id.text_input);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = mTextInputEditText.getText().toString();
                mPresenter.processInput(inputText, mFontSize, mWithShadow);
            }
        });
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (options.getVisibility() != View.VISIBLE) {
                    imm.hideSoftInputFromWindow(mTextInputEditText.getWindowToken(), 0);
                    options.setVisibility(View.VISIBLE);
                    mTextInputEditText.clearFocus();
                } else {
                    mTextInputEditText.requestFocus();
                    imm.showSoftInput(mTextInputEditText, InputMethodManager.SHOW_FORCED);
                    options.setVisibility(View.GONE);
                }
            }
        });
        mTextInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (options.getVisibility() == View.VISIBLE) {
                        options.setVisibility(View.GONE);
                    }
                }
                scrollChatToBottom();
            }
        });
        mTextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.getTrimmedLength(s) == 0) {
                    // hide send button, show switch button
                    sendButton.setVisibility(View.INVISIBLE);
                    switchButton.setVisibility(View.VISIBLE);
                } else {
                    // hide switch button, show send button
                    sendButton.setVisibility(View.VISIBLE);
                    switchButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mTextInputEditText.clearFocus();
                options.setVisibility(View.GONE);
                return false;
            }
        });

        SeekBar seekBar = (SeekBar) root.findViewById(R.id.font_size);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // if not select any item
                    // save to member
                mFontSize = progress == 0 ? 1 : progress;
                // else
                    // save to chat data
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SwitchCompat switchCompat = (SwitchCompat) root.findViewById(R.id.shadow_switch);
        mWithShadow = switchCompat.isChecked();
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mWithShadow = isChecked;
            }
        });

    }

    private void initRecyclerView(View root) {
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initHeadBar(View root) {
        TextView title = (TextView) root.findViewById(R.id.title);
        ImageView left_option = (ImageView) root.findViewById(R.id.left_option);
        ImageView right_option = (ImageView) root.findViewById(R.id.right_option);
        title.setText(R.string.string_emoji);
        left_option.setVisibility(View.GONE);
    }

    private void scrollChatToBottom() {
        mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    /**
     *
     * @param view
     * @param data
     * @return
     */
    @Override
    public boolean onContentLongClicked(@NonNull View view,@NonNull Object data) {
        if (view instanceof TextEmoji && data instanceof ChatItem) {
            TextEmoji emojiView = (TextEmoji) view;
            ChatItem item = (ChatItem) data;
            Bitmap bitmap = emojiView.getBitmap();
            String filename = item.content + System.currentTimeMillis() + ".png";
            Uri uri = mPresenter.saveBitmap(bitmap, filename);
            shareImage(uri);
            return true;
        } else {
            return false;
        }
    }

    private void shareImage(Uri uri) {
        Intent sendIntent = new Intent();
//                    ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
//                    sendIntent.setComponent(comp);
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("image/*");
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        getContext().startActivity(sendIntent);
    }

    /**
     * show Chats
     * @param data chat data
     */
    @Override
    public void showChats(List<ChatItem> data) {
        mAdapter.updateData(data);
        scrollChatToBottom();
    }

    @Override
    public void showEmptyText() {
        Toast.makeText(getActivity(), R.string.empty_text, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void clearEditText() {
        mTextInputEditText.getText().clear();
    }

    /**
     * Classes for RecyclerView
     */
    static class DefaultViewHolder extends BaseEmojiViewHolder {
        private final TextView item_content;
        private final ImageView item_avatar;
        private final TextEmoji item_text_emoji;

        public DefaultViewHolder(Context context, LayoutInflater inflater, ViewGroup parent) {
            super(context, inflater.inflate(R.layout.chat_item, parent, false));
            item_content = (TextView) itemView.findViewById(R.id.item_content);
            item_avatar = (ImageView) itemView.findViewById(R.id.item_avatar);
            item_text_emoji = (TextEmoji) itemView.findViewById(R.id.text_emoji);
        }

        @Override
        protected void bind(@NonNull final ChatItem item) {
            if (ChatItem.NULL.equals(item)) {
                return;
            }
            item_content.setText(item.content);
            if (item.textSize > 0) {
                item_content.setTextSize(TypedValue.COMPLEX_UNIT_SP, item.textSize);
            }

            item_text_emoji.setText(item);

            item_text_emoji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDelegate != null) {
                        mDelegate.onContentLongClicked(v, item);
                    } else {
                    }
                }
            });

            item_text_emoji.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mDelegate != null) {
                        return mDelegate.onContentLongClicked(v, item);
                    } else {
                        return false;
                    }
                }
            });
/*
            Glide.with(getContext())
                    .load(item.avatarResId)
                    .into(item_avatar);
*/
        }
    }

    static class ContentAdapter extends RecyclerView.Adapter<BaseEmojiViewHolder> {
        private final Context mContext;
        private List<ChatItem> mData = new ArrayList<>();
        private final BaseEmojiViewHolder.EventDelegate mDelegate;

        public ContentAdapter(Context context, BaseEmojiViewHolder.EventDelegate delegate) {
            super();
            mContext = context;
            mDelegate = delegate;
        }
        public Context getContext() {
            return mContext;
        }

        @Override
        public BaseEmojiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DefaultViewHolder(getContext(), LayoutInflater.from(getContext()), parent);
        }

        @Override
        public void onBindViewHolder(BaseEmojiViewHolder holder, int position) {
            holder.bind(getItem(position));
            holder.setEventDelegate(mDelegate);
        }

        private ChatItem getItem(int position) {
            return getItemCount() > position ? mData.get(position) : ChatItem.NULL;
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void updateData(List<ChatItem> list) {
            mData.clear();
            mData.addAll(list);
            notifyDataSetChanged();
        }

        public void appendData(ChatItem item) {
            if (!ChatItem.NULL.equals(item)) {
                mData.add(item);
                notifyDataSetChanged();
            }
        }

        public void appendData(List<ChatItem> list) {
            if (list != null && list.size() > 0) {
                mData.addAll(list);
                notifyDataSetChanged();
            }
        }
    }
}
