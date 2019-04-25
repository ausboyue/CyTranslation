package cn.icheny.ideaplugin.translation.util;

import org.jetbrains.annotations.Nullable;

/**
 * <pre>
 *     @author : www.icheny.cn
 *     @e-mail : ausboyue@gmail.com
 *     @time   : 2019.04.25
 *     @desc   :文本工具
 *     @version: 1.0.0
 * </pre>
 */
public final class TextUtils {
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }
}
