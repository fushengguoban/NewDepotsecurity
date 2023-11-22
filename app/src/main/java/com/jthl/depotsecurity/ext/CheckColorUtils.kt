package com.jthl.depotsecurity.ext

import androidx.compose.ui.graphics.Color

/**
 * @author wanglei
 * @date 2023/11/7 21:04
 * @Description:
 */

fun checkColorRed(isCheck: Boolean): Color {
    return if (isCheck) {
        Color.Red
    } else {
        Color.Black
    }
}