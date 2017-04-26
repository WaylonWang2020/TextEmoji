package com.sctdroid.app.textemoji.utils;

import android.text.TextUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 过滤Emoji表情
 * Created by tutu on 2016/5/31.
 */
public class EmojiUtils {

    private static Set<String> mFilterSet = null;

    private static void addUnicodeRangeToSet(Set<String> set, int start, int end) {
        if (set == null) {
            return;
        }
        if (start > end) {
            return;
        }


        for (int i = start; i <= end; i++) {
            mFilterSet.add(new String(new int[]{i}, 0, 1));
        }
    }

    private static void addUnicodeRangeToSet(Set<String> set, int code) {
        if (set == null) {
            return;
        }
        mFilterSet.add(new String(new int[]{code}, 0, 1));
    }

    private static final String mRegrex;

    private static final Pattern mPattern;

    static {
        mFilterSet = new HashSet<>();

        // See http://apps.timwhitlock.info/emoji/tables/unicode

        // 1. Emoticons ( 1F601 - 1F64F )
        addUnicodeRangeToSet(mFilterSet, 0x1F601, 0X1F64F);

        // 2. Dingbats ( 2702 - 27B0 )
        addUnicodeRangeToSet(mFilterSet, 0x2702, 0X27B0);

        // 3. Transport and map symbols ( 1F680 - 1F6C0 )
        addUnicodeRangeToSet(mFilterSet, 0X1F680, 0X1F6C0);

        // 4. Enclosed characters ( 24C2 - 1F251 )
        addUnicodeRangeToSet(mFilterSet, 0X24C2);
        addUnicodeRangeToSet(mFilterSet, 0X1F170, 0X1F251);

        // 6a. Additional emoticons ( 1F600 - 1F636 )
        addUnicodeRangeToSet(mFilterSet, 0X1F600, 0X1F636);

        // 6b. Additional transport and map symbols ( 1F681 - 1F6C5 )
        addUnicodeRangeToSet(mFilterSet, 0X1F681, 0X1F6C5);

        // 6c. Other additional symbols ( 1F30D - 1F567 )
        addUnicodeRangeToSet(mFilterSet, 0X1F30D, 0X1F567);

        // 5. Uncategorized
        addUnicodeRangeToSet(mFilterSet, 0X1F004);
        addUnicodeRangeToSet(mFilterSet, 0X1F0CF);
        // 与6c. Other additional symbols ( 1F30D - 1F567 )重复
        // 去掉重复部分虽然不去掉HashSet也不会重复，原范围（0X1F300 - 0X1F5FF）
        addUnicodeRangeToSet(mFilterSet, 0X1F300, 0X1F30D);
        addUnicodeRangeToSet(mFilterSet, 0X1F5FB, 0X1F5FF);
        addUnicodeRangeToSet(mFilterSet, 0X00A9);
        addUnicodeRangeToSet(mFilterSet, 0X00AE);
        addUnicodeRangeToSet(mFilterSet, 0X0023);
        //阿拉伯数字0-9，配合0X20E3使用
        //addUnicodeRangeToSet(mFilterSet, 0X0030, 0X0039);
        // 过滤掉203C开始后的2XXX 段落
        //addUnicodeRangeToSet(mFilterSet, 0X203C, 0X24C2);
        addUnicodeRangeToSet(mFilterSet, 0X203C);
        addUnicodeRangeToSet(mFilterSet, 0X2049);
        //严格验证的话需要判断前面是否是数字
        //Android上显示和数字分开可以不判断
        addUnicodeRangeToSet(mFilterSet, 0X20E3);
        addUnicodeRangeToSet(mFilterSet, 0X2122);
        addUnicodeRangeToSet(mFilterSet, 0X2139);
        addUnicodeRangeToSet(mFilterSet, 0X2194, 0X2199);
        addUnicodeRangeToSet(mFilterSet, 0X21A9, 0X21AA);
        addUnicodeRangeToSet(mFilterSet, 0X231A, 0X231B);
        addUnicodeRangeToSet(mFilterSet, 0X23E9, 0X23EC);
        addUnicodeRangeToSet(mFilterSet, 0X23F0);
        addUnicodeRangeToSet(mFilterSet, 0X23F3);
        addUnicodeRangeToSet(mFilterSet, 0X25AA, 0X25AB);
        addUnicodeRangeToSet(mFilterSet, 0X25FB, 0X25FE);
        //TODO： 26XX 太杂全部过滤
        addUnicodeRangeToSet(mFilterSet, 0X2600, 0X26FE);
        addUnicodeRangeToSet(mFilterSet, 0X2934, 0X2935);
        addUnicodeRangeToSet(mFilterSet, 0X2B05, 0X2B07);
        addUnicodeRangeToSet(mFilterSet, 0X2B1B, 0X2B1C);
        addUnicodeRangeToSet(mFilterSet, 0X2B50);
        addUnicodeRangeToSet(mFilterSet, 0X2B55);
        addUnicodeRangeToSet(mFilterSet, 0X3030);
        addUnicodeRangeToSet(mFilterSet, 0X303D);
        addUnicodeRangeToSet(mFilterSet, 0X3297);
        addUnicodeRangeToSet(mFilterSet, 0X3299);

        mRegrex = TextUtils.join("|", mFilterSet);
        mPattern = Pattern.compile(mRegrex, Pattern.CASE_INSENSITIVE);
    }

    public static boolean isEmoji(String ch) {
        return mFilterSet.contains(ch);
    }

    public static boolean hasEmoji(String text) {
        return !TextUtils.isEmpty(text) && mPattern.matcher(text).find();
    }

    public static int emojiCount(String text) {
        Matcher matcher = mPattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
