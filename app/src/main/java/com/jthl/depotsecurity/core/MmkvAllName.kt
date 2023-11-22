package com.jthl.depotsecurity.core

/**
 * @author wanglei
 * @date 2022/3/11 15:04
 * @Description:
 * 处理MMKV存储别名问题，方便后期查找，所有使用MmkvAllName 必须在这里实现注册
 */
object MmkvAllName {
    const val TOKEN = "token"                                  //设备token
    const val STATION_ID = "stationId"                         //设别标识，设备名称
    var SIM: String = "SIM"                                            //主板编号
    const val TIME = "TIME"                                    //设备运营时间
    const val OLDUPTRAFFIC = "OLDUPTRAFFIC"                    //两次网络请求之间的流量消耗
    const val PICTURE_URL = "PICTURE_URL"                      //推送下发的图片地址
    const val VIDEO_URL = "VIDEO_URL"                          //推送下发的视频地址
    const val NOTICE_CONTENT = "NOTICE_CONTENT"                //推送下发的通知内容
    const val MEDIA_FILE = "MEDIA_FILE"                        //资源文件-用来判断视频和图片哪一个是最后存储的
    const val STAND_BY_TIME = "standbyTime"                      //设备每天的运营时间。
}