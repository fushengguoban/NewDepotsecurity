package com.jthl.depotsecurity.page

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jthl.depotsecurity.R
import com.jthl.depotsecurity.core.Constance.cardOpenPos
import com.jthl.depotsecurity.core.Constance.posAsset
import com.jthl.depotsecurity.core.Constance.posBerth
import com.jthl.depotsecurity.core.Constance.posBusWash
import com.jthl.depotsecurity.core.Constance.posPatrol
import com.jthl.depotsecurity.core.Constance.posSecurityCheck
import com.jthl.depotsecurity.ui.theme.Color_77869e
import com.jthl.depotsecurity.ui.theme.Color_f4f5f6
import com.jthl.depotsecurity.viewmodel.HomeViewModel


/**
 * @author wanglei
 * @date 2023/11/13 16:02
 * @Description:
 * home界面
 */

@Composable
fun HomePage(
    navController: NavController, homeViewModel: HomeViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        homeViewModel.permissionTree()
    }
    val pagingData = homeViewModel.yourData.collectAsState(initial = emptyList())

    Log.e("aaaa", "被谁重组了！")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color_f4f5f6)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 19.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (pagingData.value.isNotEmpty()) {
                items(pagingData.value.size) { index ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//
                        Image(
                            painter = painterResource(id = chooseImage(pagingData.value[index].name)),
                            contentDescription = null, modifier = Modifier
                                .padding(top = 40.dp)
                                .size(48.dp, 48.dp)
                        )
                        Text(
                            text = chooseText(pagingData.value[index].name),
                            color = Color_77869e,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(top = 17.dp, bottom = 40.dp)
                        )

                    }

                }
            }

        }
    }
}


private fun chooseImage(mType: String) = when (mType) {
    posBerth -> {
        R.mipmap.berth_icon
    }

    posSecurityCheck -> {
        R.mipmap.check_icon
    }

    posPatrol -> {
        R.mipmap.watchman
    }

    posBusWash -> {
        R.mipmap.icon_car
    }

    posAsset -> {
        R.mipmap.icon_security
    }

    cardOpenPos -> {
        R.mipmap.icon_card
    }

    else -> {
        R.mipmap.berth_icon
    }
}


private fun chooseText(type: String): String {
    when (type) {
        posBerth -> {
            return "泊位"
        }

        posSecurityCheck -> {
            return "安检"
        }

        posPatrol -> {
            return "巡更"
        }

        posBusWash -> {
            return "洗车"
        }

        posAsset -> {
            return "安全巡查"
        }

        cardOpenPos -> {
            return "开卡管理"
        }
    }
    return ""
}


