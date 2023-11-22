package com.jthl.depotsecurity.network

import com.jthl.depotsecurity.bean.BaseRet
import com.jthl.depotsecurity.bean.LoginBean
import com.jthl.depotsecurity.bean.PermissionTreeData
import com.jthl.depotsecurity.core.Constance.BaseUrl
import com.jthl.depotsecurity.ext.DataRequest
import com.jthl.depotsecurity.network.now.ApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author wanglei
 * @date 2023/11/10 15:08
 * @Description:
 */
interface ApiService {

    companion object {
        val SERVER_URL = BaseUrl
    }

    @POST("login/admin")
    suspend fun login(@Body body: DataRequest.LoginRequest): ApiResponse<LoginBean>

    @POST("user/permission/tree/pos")
    suspend fun permissionTree(): ApiResponse<MutableList<PermissionTreeData>>
}
