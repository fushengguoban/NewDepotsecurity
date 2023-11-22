package com.jthl.depotsecurity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jthl.depotsecurity.core.MMKVUtils
import com.jthl.depotsecurity.core.MmkvAllName
import com.jthl.depotsecurity.network.now.NetworkApi
import com.jthl.depotsecurity.page.CardStart
import com.jthl.depotsecurity.page.HomePage
import com.jthl.depotsecurity.page.LoginStart
import com.jthl.depotsecurity.page.StartPage
import com.jthl.depotsecurity.ui.theme.NewDepotsecurityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewDepotsecurityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    val token = MMKVUtils.mmkv.decodeString(MmkvAllName.TOKEN)
                    if (token != null && "" != token) {
                        NetworkApi.headerName = "token"
                        NetworkApi.headerValue = token
                        Navigation(true)
                    } else {
                        Navigation(false)
                    }

//                    StartPage()
                }
            }
        }
    }

    @Composable
    fun Navigation(isTrue: Boolean) {
        val mNavController = rememberNavController()
        if (isTrue) {
            NavHost(navController = mNavController, startDestination = "HomePage") {
                composable("HomePage") { HomePage(mNavController) }
            }
        } else {
            NavHost(navController = mNavController, startDestination = "StartPage") {
                composable("StartPage") { StartPage(mNavController) }
                composable("LoginStart") { LoginStart(mNavController) }
                composable("CardStart") { CardStart(mNavController) }
                composable("HomePage") {
                Log.e("aaaa","我不可能重组")
                    HomePage(mNavController) }
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewDepotsecurityTheme {
        Greeting("Android")
    }
}

