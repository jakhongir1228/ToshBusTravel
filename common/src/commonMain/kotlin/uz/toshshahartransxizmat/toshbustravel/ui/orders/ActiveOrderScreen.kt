package uz.toshshahartransxizmat.toshbustravel.ui.orders

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.components.dialog.ErrorDialog
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.theme.gray700
import uz.toshshahartransxizmat.toshbustravel.ui.orders.viewModel.ActiveOrderViewModel
import uz.toshshahartransxizmat.toshbustravel.ui.payment.PaymentScreen
import uz.toshshahartransxizmat.toshbustravel.util.getStrings


internal class ActiveOrderScreen(
    private val amount: Long
): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberKoinInject<ActiveOrderViewModel>()
        val state = viewModel.state.collectAsState()
        var showErrorDialog by remember { mutableStateOf(true) }

        val transition = rememberInfiniteTransition()
        val coroutineScope = rememberCoroutineScope()

        var timer by remember { mutableStateOf(5) }

        LaunchedEffect(key1 = Unit) {
            while (true) {
                delay(1000L)
                if (timer > 0) {
                    timer--
                } else {
                    viewModel.loadActiveOrder()
                    timer = 5
                }
            }
        }

        val scale by transition.animateFloat(
            initialValue = 0.5f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(800, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                PageHeader(
                    type = PageHeaderType.Heading(text = ""),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    onNavigationClick = { navigator.pop() }
                )

                Box(
                    modifier = Modifier
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .graphicsLayer {
                                        scaleX = scale
                                        scaleY = scale
                                    }
                                    .background(blueA220)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .graphicsLayer {
                                        scaleX = scale
                                        scaleY = scale
                                    }
                                    .background(blueA220)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(text = getStrings("processing"), fontSize = 24.sp, color = Color.Black)
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = getStrings("check_status"), fontSize = 16.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "0:${if (timer < 10) "0$timer" else "$timer"}",
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { viewModel.loadActiveOrder() },
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Refresh",
                                tint = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            text = getStrings("the_request_will_be_processed_in_a_few_minutes"),
                            fontSize = 16.sp,
                            color = gray700,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            if (state.value.error.isNotBlank()) {
                ErrorDialog(
                    errorMessage = state.value.error,
                    showDialog = showErrorDialog,
                    onDismiss = { showErrorDialog = false }
                )
            }
            if (state.value.isLoaded){
                navigator.push(
                    PaymentScreen(
                        orderId = state.value.activeOrder.id,
                        amount = amount
                    )
                )
            }
        }
    }
}