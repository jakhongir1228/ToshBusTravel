package uz.toshshahartransxizmat.toshbustravel.ui.amount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import uz.toshshahartransxizmat.toshbustravel.components.button.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.dialog.ErrorDialog
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CalculatorEntity
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.ui.amount.component.OrderTypeSelector
import uz.toshshahartransxizmat.toshbustravel.util.getStrings
import uz.toshshahartransxizmat.toshbustravel.ui.amount.component.DateInput
import uz.toshshahartransxizmat.toshbustravel.ui.amount.component.TimeInput
import uz.toshshahartransxizmat.toshbustravel.ui.amount.component.calculateTotalHours
import uz.toshshahartransxizmat.toshbustravel.ui.amount.dialog.AmountDialog
import uz.toshshahartransxizmat.toshbustravel.ui.amount.viewModel.AmountViewModel
import uz.toshshahartransxizmat.toshbustravel.util.Other

internal class SeeAmountScreen(vehicleId:Int,from:String,to:String,aLatitude:Double,aLongitude:Double,
                               bLatitude:Double,bLongitude:Double,cLatitude:Double,cLongitude:Double,distanceofPoints:Double?): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var isCity by remember { mutableStateOf(false) }
        var travelTime by remember { mutableStateOf(0) }
        var isDateValidate by remember { mutableStateOf(false) }
        var showAmountDialog by remember { mutableStateOf(false) }
        var showErrorDialog by remember { mutableStateOf(true) }
        val viewModel = rememberKoinInject<AmountViewModel>()
        val state = viewModel.state.collectAsState()

        var startDate by remember { mutableStateOf("") }
        var startTime by remember { mutableStateOf("") }
        var endDate by remember { mutableStateOf("") }
        var endTime by remember { mutableStateOf("") }

        Scaffold{
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                PageHeader(
                    type = PageHeaderType.Heading(text = getStrings("view_total_amount")),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    onNavigationClick = { navigator.pop() }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = getStrings("total_distance"),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "20 km",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = blueA220,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OrderTypeSelector(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    title = getStrings("select_order_type"),
                    onOptionSelected = { selectedIsCity ->
                        isCity = selectedIsCity
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    text = getStrings("enter_date_time_for_order"),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    DateInput(
                        modifier = Modifier
                            .weight(0.6f)
                            .padding(start = 2.dp, end = 8.dp),
                        title = getStrings("start_date")
                    ){ date->
                        startDate = date
                        println("startDate-->$startDate")
                    }

                    TimeInput(
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(start = 2.dp, end = 2.dp),
                        title = getStrings("start_time")
                    ){ time->
                        startTime = time
                        println("startTime----->$startTime")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    DateInput(
                        modifier = Modifier
                            .weight(0.6f)
                            .padding(start = 2.dp, end = 8.dp),
                        title = getStrings("end_date")
                    ){ date->
                        endDate = date
                        println("endDate-->$endDate")
                    }

                    TimeInput(
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(start = 2.dp, end = 2.dp),
                        title = getStrings("end_time")
                    ){ time->
                        endTime = time
                        println("endTime----->$endTime")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (startDate.isNotEmpty() && startTime.isNotEmpty() && endDate.isNotEmpty() && endTime.isNotEmpty()) {
                    val totalHours = calculateTotalHours(startDate, startTime, endDate, endTime)
                    travelTime = totalHours.toInt()
                    isDateValidate = true
                    Text(
                        text = getStrings("total_time")+" $totalHours soat",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = blueA220,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                if (showAmountDialog) {
                    AmountDialog(
                        amount = state.value.success.amount.toString(),
                        onConfirm = {
                            showAmountDialog = false
                            viewModel.onAmountDialogDismissed()
                        },
                        onCancel = {
                            showAmountDialog = false
                            viewModel.onAmountDialogDismissed()
                        }
                    )
                }

                Spacer(modifier = Modifier.weight(weight = 1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 56.dp),
                    text = TextValue(getStrings("continue")),
                    size = ButtonSize.Large,
                    enabled = isDateValidate,
                    loading = state.value.isLoading,
                    onClick = {
                        val calculatorEntity = CalculatorEntity(
                            busTypeId = 1,
                            isCity = isCity,
                            distance = 15,
                            travelTime = travelTime
                        )
                        println("call->>>>$calculatorEntity")
                        viewModel.loadCalculator(calculatorEntity)
                        showErrorDialog = true
                    }
                )

            }
            if (state.value.error.isNotBlank()) {
                ErrorDialog(
                    errorMessage = state.value.error,
                    showDialog = showErrorDialog,
                    onDismiss = { showErrorDialog = false }
                )
            }
            if (state.value.isLoaded){
                 showAmountDialog = true
                 viewModel.resetLoadedState()
            }
        }
    }
}