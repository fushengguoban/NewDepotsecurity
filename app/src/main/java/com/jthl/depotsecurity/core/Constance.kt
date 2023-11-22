package com.jthl.depotsecurity.core

/**
 * @author wanglei
 * @date 2023/11/20 21:31
 * @Description:
 */
object Constance {
    var BaseUrl = "https://buswash.jintdev.com/buswash/"
    var token = ""
    const val SUCCESS = "0"            // 0-成功
    const val NEW_SUCCESS = "200"       //新版本的请求成功
    const val posBerth = "PosBerth"                              // 泊位权限标识
    const val posSecurityCheck = "PosSecurityCheck"              // 安检权限标识
    const val posPatrol = "PosPatrol"                            // 巡更权限标识
    const val posBusWash = "PosWash"                             // 洗车权限标识
    const val cardOpenPos = "cardOpenPos"                        // 开卡管理
    const val posAsset = "PosAsset"                              // 安全巡查标识
    const val driverManager = "driverManager"                    // 司机卡管理
    const val temporaryCardManager = "temporaryCardManager"      // 临时卡管理
    const val securityButtonManager = "securityButtonManager"    // 安保妞管理
    const val patrolButtonManager = "patrolButtonManager"        // 巡更钮管理
}