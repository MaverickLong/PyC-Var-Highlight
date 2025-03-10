package com.github.mavericklong.pycvarhighlight.listeners

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.event.EditorFactoryEvent
import com.intellij.openapi.editor.event.EditorFactoryListener


class VariableEditorListener : EditorFactoryListener {
    override fun editorCreated(event: EditorFactoryEvent) {
        val editor: Editor = event.editor
        editor.caretModel.addCaretListener(VariableCaretListener())
    }
}