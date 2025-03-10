package com.github.mavericklong.pycvarhighlight.statusBar

import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidget.TextPresentation
import java.awt.Component


class VariableTypeStatusBarWidget(private val statusBar: StatusBar) : StatusBarWidget, TextPresentation {

    private var text = ""

    override fun getPresentation(): StatusBarWidget.WidgetPresentation {
        return this
    }

    override fun getAlignment(): Float {
        return Component.CENTER_ALIGNMENT
    }

    override fun getText(): String {
       return text
    }

    override fun getTooltipText(): String {
        return "Displays the type of variable under caret"
    }

    override fun ID(): String {
        return "VariableTypeStatusBarWidget"
    }

    fun setText(text: String) {
        this.text = text
        statusBar.updateWidget(ID())
    }
}