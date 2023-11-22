package com.jthl.depotsecurity.page

import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

/**
 * @author wanglei
 * @date 2023/11/7 21:56
 * @Description:
 */
@Composable
fun CardStart(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "我是刷卡界面")
    }
}