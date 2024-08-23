package uz.toshshahartransxizmat.toshbustravel.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.toshshahartransxizmat.toshbustravel.components.navigator.BottomItem
import uz.toshshahartransxizmat.toshbustravel.ui.orders.OrdersTab
import uz.toshshahartransxizmat.toshbustravel.ui.profile.ProfileTab

internal class HomeScreen: Screen {

    @Composable
    override fun Content() {

        TabNavigator(HomeTab) {
            Scaffold (
                bottomBar = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(82.dp)
                            .background(Color.White, shape = RoundedCornerShape(32.dp))
                            .padding(8.dp)
                    ) {
                        NavigationBar(
                            containerColor = Color.White,
                            contentColor = Color.Gray,
                            tonalElevation = 0.dp,
                            modifier = Modifier.background(Color.White)
                        ) {
                            BottomItem(HomeTab)
                            BottomItem(OrdersTab)
                            BottomItem(ProfileTab)
                        }
                    }
                }
            ){
                CurrentTab()
            }
        }
    }
}
