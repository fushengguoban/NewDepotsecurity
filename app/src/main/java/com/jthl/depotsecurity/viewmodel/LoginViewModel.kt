package com.jthl.depotsecurity.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.jthl.depotsecurity.core.Constance.SUCCESS
import com.jthl.depotsecurity.core.MMKVUtils
import com.jthl.depotsecurity.core.MmkvAllName
import com.jthl.depotsecurity.ext.DataRequest
import com.jthl.depotsecurity.network.now.NetworkApi
import com.jthl.depotsecurity.network.now.apiService
import com.jthl.depotsecurity.network.requestNoCheck
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


/**
 * @author wanglei
 * @date 2023/11/13 11:07
 * @Description:
 */
class LoginViewModel : BaseViewModel() {
    var viewSates by mutableStateOf(LoginViewState())
        private set
    private val _viewEvents = Channel<LoginViewEvent>(Channel.BUFFERED)
    val viewEvents = _viewEvents.receiveAsFlow()

    fun dispatch(action: LoginViewAction) {
        when (action) {
            is LoginViewAction.Login -> login()
            is LoginViewAction.ClearAccount -> clearAccount()
            is LoginViewAction.ClearPassword -> clearPassword()
            is LoginViewAction.UpdateAccount -> updateAccount(action.account)
            is LoginViewAction.UpdatePassword -> updatePassword(action.password)
        }
    }

    private fun login() {
        requestNoCheck({
            apiService.login(
                DataRequest.LoginRequest(
                    viewSates.account,
                    viewSates.password
                )
            )
        }, {
            Log.e("aaaa", "这是什么：${it.retcode}--------${it.data}")
            if (it.retcode.toString() == SUCCESS) {
                NetworkApi.headerName = "token"
                NetworkApi.headerValue = it.data.token
                MMKVUtils.mmkv.encode(MmkvAllName.TOKEN, it.data.token)
                viewModelScope.launch {
                    _viewEvents.send(LoginViewEvent.PopBack)
                }
            }
        }, {
            viewModelScope.launch {
                _viewEvents.send(LoginViewEvent.PopBack)
            }
        })
    }

    private fun clearAccount() {
        viewSates = viewSates.copy(account = "")
    }

    private fun clearPassword() {
        viewSates = viewSates.copy(password = "")
    }

    private fun updateAccount(account: String) {
        viewSates = viewSates.copy(account = account)
    }

    private fun updatePassword(password: String) {
        viewSates = viewSates.copy(password = password)
    }
}


sealed class LoginViewEvent {
    object PopBack : LoginViewEvent()
    data class ErrorMessage(val message: String) : LoginViewEvent()
}

data class LoginViewState(
    var account: String = "",
    val password: String = "",
    val isLogged: Boolean = false
)

sealed class LoginViewAction {
    object Login : LoginViewAction()
    object ClearAccount : LoginViewAction()
    object ClearPassword : LoginViewAction()
    data class UpdateAccount(val account: String) : LoginViewAction()
    data class UpdatePassword(val password: String) : LoginViewAction()
}