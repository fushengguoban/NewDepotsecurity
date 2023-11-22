package com.jthl.depotsecurity.network.now

import com.jthl.depotsecurity.network.BaseResponse


/**
 * @author wanglei
 * @date 2022/3/3 17:17
 * @Description: 服务器返回数据的基类
 * 如果你的项目中有基类，可以继承BaseResponse 请求时框架可以帮你脱壳，自动判断是否请求成功，怎么做：
 * 1.继承BaseResponse
 * 2.重写 isSuccess 方法，编写你的业务需求，根据自己的条件判断数据是否成功
 * 3.重写 getResponseCode、getResponseData、getResponseMsg方法，传入你的code data msg
 */
data class ApiResponse<T>(val retcode: Int, val retmsg: String, val data: T) :
    BaseResponse<T>() {
    //这里返回的 错误码为 0  就是代表请求成功，根据自己业务需求来变动
    override fun isSuccess() = retcode == 0

    override fun getResponseData() = data

    override fun getResponseCode() = retcode

    override fun getResponseMsg() = retmsg
}