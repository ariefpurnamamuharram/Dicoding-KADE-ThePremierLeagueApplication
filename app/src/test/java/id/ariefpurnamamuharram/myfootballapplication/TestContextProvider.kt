package id.ariefpurnamamuharram.myfootballapplication

import id.ariefpurnamamuharram.myfootballapplication.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider : CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}