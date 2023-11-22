package com.jthl.depotsecurity.bean

/**
 * @author wanglei
 * @date 2023/11/20 11:16
 * @Description:
 */
data class PermissionTreeData(
    val children: List<ChildrenData>,
    val description: String,
    val id: Int,
    val name: String,
    val owned: Boolean,
    val type: String
)

data class ChildrenData(
    val id: Int,
    val name: String,
    val description: String,
    val type: String,
    val children: List<Any>,
    val owned: Boolean
)