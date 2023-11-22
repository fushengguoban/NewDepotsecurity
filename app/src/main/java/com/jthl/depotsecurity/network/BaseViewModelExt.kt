package com.jthl.depotsecurity.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jthl.depotsecurity.viewmodel.BaseViewModel
import kotlinx.coroutines.*

/**
 * @author wanglei
 * @date 2022/3/8 10:19
 * @Description: BaseViewModel请求协程封装
 */


/**
 * 显示页面状态。这里有个技巧，成功回调在第一个，其后两个带默认值的回调可省
 * @receiver BaseVmActivity<*>
 * @param resultState ResultState<T>              接口返回值
 * @param onSuccess Function1<T, Unit>            成功回调
 * @param onError Function1<AppException, Unit>?  失败回调
 * @param onLoading Function0<Unit>?              加载中
 */
//fun <T> BaseVmActivity<*>.parseState(
//    resultState: ResultState<T>,
//    onSuccess: (T) -> Unit,
//    onError: ((AppException) -> Unit)? = null,
//    onLoading: (() -> Unit)? = null
//) {
//    when (resultState) {
//        is ResultState.Loading -> {
//            //这里打开loading加载--缺少
//            onLoading?.run { this }
//        }
//        is ResultState.Success -> {
//            //这里关闭loading加载 --缺少
//            onSuccess(resultState.data)
//        }
//        is ResultState.Error -> {
//            //这里关闭loading加载 --缺少
//            onError?.run { this(resultState.error) }
//        }
//    }
//}


/**
 * 显示页面状态，这里有个技巧，成功回调在第一个，其后两个带默认值的回调可省
 * @receiver BaseVmFragment<*>
 * @param resultState ResultState<T>                                   接口返回值
 * @param onSuccess Function1<T, Unit>                                 成功回调
 * @param onError Function1<AppException, Unit>?                       失败回调
 * @param onLoading Function1<[@kotlin.ParameterName] String, Unit>?   加载中
 */
//fun <T> BaseVmFragment<*>.parseState(
//    resultState: ResultState<T>,
//    onSuccess: (T) -> Unit,
//    onError: ((AppException) -> Unit)? = null,
//    onLoading: ((message: String) -> Unit)? = null
//) {
//    when (resultState) {
//        is ResultState.Loading -> {
//            if (onLoading == null) {
//                //
//            } else {
//                onLoading.invoke(resultState.loadingMessage)
//            }
//        }
//        is ResultState.Success -> {
//            onSuccess(resultState.data)
//        }
//        is ResultState.Error -> {
//            onError?.run { this(resultState.error) }
//        }
//    }
//}


/**
 * 不校验请求结果返回数据是否是成功
 * @receiver BaseViewModel
 * @param block SuspendFunction0<BaseResponse<T>>      请求体方法
 * @param resultState MutableLiveData<ResultState<T>>  请求回调的ResultState数据
 * @param isShowDialog Boolean                         是否显示加载框
 * @param loadingMessage String                        加载框显示内容
 * @return Job
 */
fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    resultState: MutableLiveData<ResultState<T>>,
    isShowDialog: Boolean = false,
    loadingMessage: String = "请求网络中..."
): Job {
    return viewModelScope.launch {
        kotlin.runCatching {
            if (isShowDialog) resultState.value = ResultState.onAppLoading(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            resultState.parseResult(it)
        }.onFailure {
            Log.e("jthl", "${it.message}")
            //打印错误栈信息
            it.printStackTrace()
            resultState.parseException(it)
        }
    }
}


/**
 * 不校验请求结果数据是否成功
 * @receiver BaseViewModel
 * @param block SuspendFunction0<T>                       请求体方法
 * @param resultState MutableLiveData<ResultState<T>>     请求回调的ResultState数据
 * @param isShowDialog Boolean                            是否显示加载框
 * @param loadingMessage String                           加载框提示内容
 * @return Job
 */
fun <T> BaseViewModel.requestNoCheck(
    block: suspend () -> T,
    resultState: MutableLiveData<ResultState<T>>,
    isShowDialog: Boolean = false,
    loadingMessage: String = "请求网络中..."
): Job {
    return viewModelScope.launch {
        kotlin.runCatching {
            if (isShowDialog) resultState.value = ResultState.onAppLoading(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            resultState.parseResult(it)
        }.onFailure {
            Log.e("jthl", "${it.message}")
            //打印错误信息栈
            it.printStackTrace()
            resultState.parseException(it)
        }
    }
}

/**
 * 过滤服务器结果，失败抛异常
 * @receiver BaseViewModel
 * @param block SuspendFunction0<BaseResponse<T>>  请求体方法，必须要用suspend关键字修饰
 * @param success Function1<T, Unit>               成功回调
 * @param error Function1<AppException, Unit>      失败回调 可不传
 * @param isShowDialog Boolean                     是否显示加载框
 * @param loadingMessage String                    加载框提示内容
 * @return Job
// */
fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "请求网络中..."
): Job {
    //如果需要弹窗，通知Activity/Fragment 弹窗
    return viewModelScope.launch {
        kotlin.runCatching {
            //请求体
            block()
        }.onSuccess {
            //网络请求成功，关闭弹窗
            kotlin.runCatching {
                //校验请求结果码是否正确，不正确会抛出异常走下面的onFailure
                executeResponse(it) { t -> success(t) }
            }.onFailure { e ->
                //打印错误信息
                Log.e("jthl", "${e.message}")
                //打印错误信息
                e.printStackTrace()
                //失败回调
                error(ExceptionHandle.handleException(e))
            }
        }.onFailure {
            //打印错误消息
            Log.e("jthl", "${it.message}")
            //打印错误栈消息
            it.printStackTrace()
            //失败回调
            error(ExceptionHandle.handleException(it))
        }
    }
}


fun <T> BaseViewModel.requestNoCheck(
    block: suspend () -> T,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "请求网络中..."
): Job {
    //如果需要弹窗 通知Activity/fragment弹窗
    return viewModelScope.launch {
        kotlin.runCatching {
            //请求体
            block()
        }.onSuccess {
            //成功回调
            success(it)
        }.onFailure {
            //打印错误信息
            Log.e("jthl", "${it.message}")
            it.printStackTrace()
            error(ExceptionHandle.handleException(it))
        }
    }
}

/**
 * 请求结果过滤，判断请求服务器请求结果是否成功，不成功则会抛出异常
 * @param response BaseResponse<T>
 * @param success [@kotlin.ExtensionFunctionType] SuspendFunction2<CoroutineScope, T, Unit>
 */
suspend fun <T> executeResponse(
    response: BaseResponse<T>,
    success: suspend CoroutineScope.(T) -> Unit
) {
    coroutineScope {
        when {
            response.isSuccess() -> {
                success(response.getResponseData())
            }
            else -> {
                throw AppException(
                    response.getResponseCode(),
                    response.getResponseMsg(),
                    response.getResponseMsg()
                )
            }
        }
    }
}

/**
 * 调用协程
 * @receiver BaseViewModel
 * @param block Function0<T>                 执行耗时操作任务
 * @param success Function1<T, Unit>         成功回调
 * @param error Function1<Throwable, Unit>   失败回调，可不给
 */
fun <T> BaseViewModel.launch(
    block: () -> T,
    success: (T) -> Unit,
    error: (Throwable) -> Unit = {}
) {
    viewModelScope.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                block()
            }
        }.onSuccess {
            success(it)
        }.onFailure {
            error(it)
        }
    }
}
