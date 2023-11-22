package com.jthl.depotsecurity.page

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jthl.depotsecurity.viewmodel.LoginViewAction
import com.jthl.depotsecurity.viewmodel.LoginViewEvent
import com.jthl.depotsecurity.viewmodel.LoginViewModel

/**
 * @author wanglei
 * @date 2023/11/7 21:54
 * @Description:
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginStart(
    navController: NavController,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val viewStates = viewModel.viewSates
    LaunchedEffect(Unit) {
        viewModel.viewEvents.collect {
            if (it is LoginViewEvent.PopBack) {
                navController.popBackStack("LoginStart", true)
                navController.navigate("HomePage")
            } else if (it is LoginViewEvent.ErrorMessage) {

            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(start = 27.dp, top = 105.dp, end = 27.dp),
    ) {
        TextField(
            value = viewStates.account,
            onValueChange = { viewModel.dispatch(LoginViewAction.UpdateAccount(it)) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "账号") },
            textStyle = TextStyle(fontSize = 28.sp),
            placeholder = {
                Text(
                    text = "请输入账号",
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.Black
                )
            }
        )

        TextField(
            value = viewStates.password,
            onValueChange = { viewModel.dispatch(LoginViewAction.UpdatePassword(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            label = { Text(text = "密码") },
            textStyle = TextStyle(fontSize = 28.sp),
            placeholder = {
                Text(
                    text = "请输入密码",
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.Black
                )
            }

        )

        Button(
            onClick = {
                if (viewStates.account.isEmpty() || viewStates.password.isEmpty()) {
                    return@Button
                } else {
                    viewModel.dispatch(LoginViewAction.Login)
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 34.dp)
        ) {
            Text(text = "登录", style = TextStyle(fontSize = 20.sp))
        }
    }
}

@Composable
fun checkPage(navController: NavController) = HomePage(navController = navController)