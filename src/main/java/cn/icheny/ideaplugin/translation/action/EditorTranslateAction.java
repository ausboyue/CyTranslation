package cn.icheny.ideaplugin.translation.action;

import cn.icheny.ideaplugin.translation.net.HttpUtils;
import cn.icheny.ideaplugin.translation.util.ActionUtils;
import cn.icheny.ideaplugin.translation.util.DictionaryHelper;
import cn.icheny.ideaplugin.translation.util.TextUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;

import java.awt.*;


/**
 * <pre>
 *     @author : www.icheny.cn
 *     @e-mail : ausboyue@gmail.com
 *     @time   : 2019.04.25
 *     @desc   : 翻译动作（由用户唤起）
 *     @version: 1.0.0
 * </pre>
 */
public class EditorTranslateAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (editor == null) {
            return;
        }
        final String text = ActionUtils.selectText(e);
        if (TextUtils.isEmpty(text)) {
            showPopup(editor, "没有可以翻译的关键词...");
            return;
        }
        HttpUtils.doAsyncRequest(DictionaryHelper.createYoudaoUrl(text), request -> {
            showPopup(editor, DictionaryHelper.fomatByYoudao(request).toString());
        });
        //获取 application 级别的 PropertiesComponent
//        PropertiesComponent pc = PropertiesComponent.getInstance();

//        HttpUtils.doAsyncRequest(DictionaryHelper.createBaiduUrl(text), request -> {
//            showPopup(editor, DictionaryHelper.fomatByBaidu(request).toString());
//        });
//
//        HttpUtils.doAsyncRequest(DictionaryHelper.createCibaUrl(text), request -> {
//            showPopup(editor, DictionaryHelper.fomatByCiba(request).toString());
//        });

    }

    private void showPopup(final Editor editor, final String result) {
        // 回归主线程
        ApplicationManager.getApplication().invokeLater(() -> {
            final JBPopupFactory factory = JBPopupFactory.getInstance();
            factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(123, 168, 123), new Color(80, 118, 80)), null)
                    .setFadeoutTime(5000)
                    .createBalloon()
                    .show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
        });
    }
}
