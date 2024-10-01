package uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import uz.toshshahartransxizmat.toshbustravel.theme.grayA220


@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun SlideTransports(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { 4 }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            Image(
                painter = painterResource(getImageResIdForPage(page)),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(4) { index ->
                val color = if (pagerState.currentPage == index) grayA220 else Color.White
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (pagerState.currentPage == index) 16.dp else 12.dp)
                        .background(color, shape = CircleShape)
                )
            }
        }
    }
}

@Composable
fun getImageResIdForPage(page: Int): String {
    return when (page) {
        0 -> "drawable/slideJac01.png"
        1 -> "drawable/slideJac02.png"
        2 -> "drawable/slideJac03.png"
        3 -> "drawable/slideJac04.png"
        else -> "drawable/slideJac01.png"
    }
}
