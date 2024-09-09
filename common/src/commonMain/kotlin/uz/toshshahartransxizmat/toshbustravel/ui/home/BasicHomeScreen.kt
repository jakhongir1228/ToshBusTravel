package uz.toshshahartransxizmat.toshbustravel.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.toshshahartransxizmat.toshbustravel.theme.blue650
import uz.toshshahartransxizmat.toshbustravel.ui.home.component.ContentList
import uz.toshshahartransxizmat.toshbustravel.ui.home.component.Loading
import uz.toshshahartransxizmat.toshbustravel.ui.home.component.TransportItem
import uz.toshshahartransxizmat.toshbustravel.ui.home.state.HomeState
import uz.toshshahartransxizmat.toshbustravel.ui.home.topBar.TopBarItem

@Composable
internal fun BasicHomeScreen(
    state: State<HomeState>,
    loadTransports: (Int, Boolean) -> Unit
){
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(TopBarItem.All, TopBarItem.Bus, TopBarItem.MiniBus)
    val navigator = LocalNavigator.currentOrThrow
    val listState = rememberLazyListState()

    val shouldLoadMore = remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1
        }
    }

    TabRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
        selectedTabIndex = selectedTabIndex,
        indicator = { },
        divider = { }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, topBarItem ->
                Tab(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        loadTransports(selectedTabIndex, false) // Boshidan yuklash
                    },
                    selectedContentColor = Color.White
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                            .background(
                                color = if (selectedTabIndex == index) blue650 else Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(),
                            text = topBarItem.label,
                            color = if (selectedTabIndex == index) Color.White else blue650,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }

    if (state.value.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = state.value.error, fontSize = 25.sp)
        }
    }

    if (state.value.isLoading) {
        Loading()
    }

    if (state.value.isLoaded) {
        LaunchedEffect(shouldLoadMore.value) {
            if (shouldLoadMore.value) {
                loadTransports(selectedTabIndex, true)
            }
        }

        ContentList(
            list = state.value.success[selectedTabIndex],
            onClick = { transport ->
                // Transport obyektini ko'rsatish uchun o'rnatilgan onClick
            },
            listState = listState
        )
    }
}