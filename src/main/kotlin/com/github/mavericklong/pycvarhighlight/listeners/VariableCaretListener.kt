package com.github.mavericklong.pycvarhighlight.listeners

import com.github.mavericklong.pycvarhighlight.statusBar.VariableTypeStatusBarWidget
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.event.CaretEvent
import com.intellij.openapi.editor.event.CaretListener
import com.intellij.openapi.wm.WindowManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.util.PsiTreeUtil
import com.jetbrains.python.psi.PyTypedElement
import com.jetbrains.python.psi.types.TypeEvalContext


class VariableCaretListener : CaretListener {

    private val log = Logger.getInstance(this::class.java)

    override fun caretPositionChanged(event: CaretEvent) {
        val editor = event.editor
        val project = editor.project ?: return

        val document = editor.document

        // Commit the document to ensure that PSI is in sync with the document
        ApplicationManager.getApplication().invokeLater {
            val psiDocumentManager = PsiDocumentManager.getInstance(project)
            psiDocumentManager.commitDocument(document)
            // Retrieve the PsiFile from the document
            val psiFile = psiDocumentManager.getPsiFile(document) ?: return@invokeLater
            // Get the caret's offset position in the document
            val offset = event.caret!!.offset

            // Find the PSI element at the current caret offset
            val element = psiFile.findElementAt(offset)
            if (element != null) {
                log.info("Found element: " + element.text)

                // Instead of checking the element directly, get its parent of type PyTypedElement
                val typedElement = PsiTreeUtil.getParentOfType(
                    element,
                    PyTypedElement::class.java
                )

                if (typedElement != null) {
                    val context = TypeEvalContext.userInitiated(project, psiFile)
                    val type = context.getType(typedElement)

                    if (type != null) {
                        log.info("Element type: " + type.name)
                        val widget = WindowManager.getInstance().getStatusBar(project)
                            .getWidget("VariableTypeStatusBarWidget") as VariableTypeStatusBarWidget
                        type.name?.let { widget.setText(it) }
                    } else {
                        log.info("Element type could not be determined.")
                    }
                } else {
                    log.info("Element is not a PyTypedElement.")
                }
            }
        }
    }
}
