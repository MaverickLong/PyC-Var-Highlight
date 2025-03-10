package com.github.mavericklong.pycvarhighlight

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class VariableHighlightPluginTest : BasePlatformTestCase() {
    override fun getTestDataPath() = "src/test/testData/rename"
}
