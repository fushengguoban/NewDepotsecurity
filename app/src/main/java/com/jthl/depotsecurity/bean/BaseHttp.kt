package com.jthl.depotsecurity.bean

/**
 * @author wanglei
 * @date 2023/11/13 16:15
 * @Description:
 */
data class BaseRet<T>(
    var retcode: String = "",
    var retmsg: String = "",
    var data: T?,
    var total: String? = null
)
