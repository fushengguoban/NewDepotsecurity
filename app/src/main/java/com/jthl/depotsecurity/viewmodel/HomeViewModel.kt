package com.jthl.depotsecurity.viewmodel

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jthl.depotsecurity.bean.PermissionTreeData
import com.jthl.depotsecurity.network.now.apiService
import com.jthl.depotsecurity.network.requestNoCheck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

/**
 * @author wanglei
 * @date 2023/11/20 11:07
 * @Description:
 */
class HomeViewModel : BaseViewModel() {
    private val _yourData = MutableStateFlow<List<PermissionTreeData>>(emptyList())
    val yourData: StateFlow<List<PermissionTreeData>> = _yourData

    fun permissionTree() {
        requestNoCheck({
            apiService.permissionTree()
        }, { data ->
            _yourData.value = data.data
        }, {

        })
    }
}
