package com.example.apnikheti

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.apnikheti.bottomnavigation.ContentScreen
import com.example.apnikheti.bottomnavigation.model.BottomNavigationItem
import com.example.apnikheti.model.AuthState
import com.example.apnikheti.viewModel.authViewMovel.AuthViewModel
import com.example.apnikheti.viewModel.locationViewModel.LocationViewModel


@Composable
fun HomeScreen(navController: NavController, authViewModel: AuthViewModel, locationViewModel: LocationViewModel) {
    val item = listOf(
        BottomNavigationItem(
            title = "dashboard",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "APMC",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.trend_up_svgrepo_com__1_),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.trend_up_svgrepo_com),
            hasNews = false,
            badgeCount = 4
        ),
        BottomNavigationItem(
            title = "Feed",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.conversation_fill_svgrepo_com),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.conversation_svgrepo_com),
            hasNews = false,
            badgeCount = 45
        ),
        BottomNavigationItem(
            title = "Shop",
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart,
            hasNews = true,
        ),
    )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                item.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                        },
                        label = {
                            var title = item.title
                            if (title == "dashboard") title = "Home"
                            Text(text = title)
                        },
                        icon = {
                            BadgedBox(badge = {
                                if (item.badgeCount != null) {
                                    Badge {
                                        Text(text = item.badgeCount.toString())
                                    }
                                } else if (item.hasNews) {
                                    Badge()
                                }
                            }) {
                                Icon(
                                    imageVector = if (selectedItemIndex == index) {
                                        item.selectedIcon
                                    } else {
                                        item.unselectedIcon
                                    },
                                    contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }
        }){ innerPadding->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedItem = selectedItemIndex,
            navController = navController,
            authViewModel = authViewModel,
            locationViewModel = locationViewModel
        )
    }
}



