package com.jeonjisung.dietday.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jeonjisung.dietday.ui.theme.mainColor
import com.jeonjisung.dietday.viewmodel.DateViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainApp() {
    val navHostController = rememberNavController()
    val dateViewModel = DateViewModel()

    Scaffold(
        bottomBar = {
            BottomNavigation(navHostController)
        }
    ) {
        Box(Modifier.padding(it)) {
            MainNavGraph(dateViewModel, navHostController)
        }
    }
}

@Composable
fun MainScreen(viewModel: DateViewModel, navController: NavController) {
    var items by remember { mutableStateOf(listOf(1)) }
    val listState = rememberLazyListState() // LazyColumn의 스크롤 상태 저장
    val coroutineScope = rememberCoroutineScope() // CoroutineScope 사용

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // 리스트 항목 추가
                    items = items + (items.size + 1)
                    coroutineScope.launch {
                        listState.animateScrollToItem(items.size - 1) // 마지막 항목으로 스크롤
                    }
                },
                containerColor = mainColor,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
            }
        },
        floatingActionButtonPosition = FabPosition.End // FloatingActionButton을 우측 하단에 고정
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it), // Scaffold 패딩을 설정
            state = listState
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp) // 좌우 padding 적용
                        .height(160.dp)
                        .background(Color.White)
                        .border(
                            BorderStroke(8.dp, mainColor), // mainColor 대신 임시로 Gray 사용
                            shape = RoundedCornerShape(16.dp)
                        )
                )
            }
        }
    }

//    val dateInput by viewModel.dateInput
//    val dDayResult by viewModel.dDayResult

    // Test Code
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        OutlinedTextField(
//            value = dateInput,
//            onValueChange = { viewModel.onDateInputChanged(it) },
//            label = { Text("Enter Date (yyyy-MM-dd)") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = { viewModel.calculateDDay() }) {
//            Text("Calculate D-Day")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(
//            text = dDayResult,
//            style = MaterialTheme.typography.headlineMedium
//        )
//    }
}


@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.Main,
        BottomNavItem.Imsi1,
        BottomNavItem.Imsi2,
    )

    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF3F414E)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp),
                        tint = if (currentRoute == item.screenRoute) {
                            mainColor
                        } else Color.Gray
                    )
                },
                label = {
                    Text(
                        item.title,
                        fontSize = 17.sp,
                        color = if (currentRoute == item.screenRoute) mainColor else Color.Gray,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                },
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
