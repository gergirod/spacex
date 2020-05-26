package ger.girod.spacex.domain.repository.util

import ger.girod.spacex.domain.util.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> executeNetworkRequest(
    dispatcher: CoroutineDispatcher, isInternetAvailable: Boolean,
    request: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        if (isInternetAvailable) {
            try {
                ResultWrapper.Success(request.invoke())
            } catch (e: Exception) {
                ResultWrapper.Error(e.message)
            }
        } else {
            ResultWrapper.Error("no internet connection")
        }
    }
}