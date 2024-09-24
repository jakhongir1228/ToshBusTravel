package uz.toshshahartransxizmat.toshbustravel.ui.amount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.ui.amount.component.OrderTypeSelector
import uz.toshshahartransxizmat.toshbustravel.util.getStrings
import uz.toshshahartransxizmat.toshbustravel.ui.amount.component.AdvancedTimePicker
import uz.toshshahartransxizmat.toshbustravel.ui.amount.component.DateInput
import uz.toshshahartransxizmat.toshbustravel.ui.amount.component.TimeInput

internal class SeeAmountScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var isCity by remember { mutableStateOf(false) }

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
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
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
                    ){ startDate->
                        println("startDate-->$startDate")
                    }

                    TimeInput(
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(start = 2.dp, end = 2.dp),
                        title = getStrings("start_time")
                    ){ startTime->
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
                    ){ endDate->
                        println("endDate-->$endDate")
                    }

                    TimeInput(
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(start = 2.dp, end = 2.dp),
                        title = getStrings("end_time")
                    ){ endTime->
                        println("endTime----->$endTime")
                    }
                }
            }
        }
    }
}