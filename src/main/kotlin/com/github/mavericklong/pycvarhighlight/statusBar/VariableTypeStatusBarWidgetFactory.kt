package com.github.mavericklong.pycvarhighlight.statusBar

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory
import com.intellij.openapi.wm.WindowManager


class VariableTypeStatusBarWidgetFactory : StatusBarWidgetFactory {

    override fun getDisplayName(): String {
        return "Variable Type"
    }

    override fun getId(): String {
        return "VariableTypeStatusBarWidget"
    }

    override fun createWidget(project: Project): StatusBarWidget {
        // Retrieve the StatusBar instance for the project.
        val statusBar: StatusBar = WindowManager.getInstance().getStatusBar(project)
        return VariableTypeStatusBarWidget(statusBar)
    }

    override fun disposeWidget(widget: StatusBarWidget) {
        widget.dispose()
    }
}