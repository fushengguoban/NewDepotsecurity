package com.jthl.depotsecurity.page

import androidx.annotation.ContentView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jthl.depotsecurity.ext.checkColorRed
import kotlinx.coroutines.launch

/**
 * @author wanglei
 * @date 2023/11/7 17:51
 * @Description: 登录界面
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun StartPage(navController: NavController) {
    val titles = listOf<String>("账号登录", "刷卡登录")
    val state = rememberPagerState(
        pageCount = titles.size,
        initialPage = 0,  //初始页吗
        infiniteLoop = true, //是否循环效果
        initialOffscreenLimit = 1 //预加载页数
    )
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        TabRow(
            selectedTabIndex = state.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
        ) {
            titles.forEachIndexed { index, title ->
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                state.scrollToPage(index, 0f)
                            }
                        }
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        color = checkColorRed(state.currentPage == index),
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .align(Alignment.Center),

                        )
                }
            }
        }
        HorizontalPager(state = state, modifier = Modifier.fillMaxSize()) { pagePosition ->
            if (state.currentPage == 0) {
                LoginStart(navController = navController)
            } else {
                CardStart(navController = navController)
            }

        }
    }
}