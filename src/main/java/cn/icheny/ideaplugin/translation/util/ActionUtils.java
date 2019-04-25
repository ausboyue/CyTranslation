package cn.icheny.ideaplugin.translation.util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import org.jetbrains.annotations.NotNull;


/**
 * <pre>
 *     @author : www.icheny.cn
 *     @e-mail : ausboyue@gmail.com
 *     @time   : 2019.04.25
 *     @desc   :对AnActionEvent操作的工具类
 *     @version: 1.0.0
 * </pre>
 */
public class ActionUtils {
    /**
     * 获取光标选中的文本
     *
     * @param e
     * @return
     */
    public final static String selectText(@NotNull AnActionEvent e) {
        final Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (editor == null) {
            return null;
        }
        final SelectionModel model = editor.getSelectionModel();
        if (model == null) {
            return null;
        }
        final String text = model.getSelectedText();
        return text;
    }
}
