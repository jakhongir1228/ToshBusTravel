package uz.toshshahartransxizmat.toshbustravel.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.ui.profile.component.ProfileCardItem
import uz.toshshahartransxizmat.toshbustravel.ui.profile.component.UserProfileCard
import uz.toshshahartransxizmat.toshbustravel.ui.profile.viewModel.ProfileViewModel
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal object ProfileTab: Tab {

    @Composable
    override fun Content() {
        val viewModel = rememberKoinInject<ProfileViewModel>()
        val state = viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.loadGetClient()
        }

        Scaffold {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(bottom = 72.dp)
            ){
                Text(
                    text = getStrings("profile"),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 24.dp, end = 16.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (state.value.isLoaded){
                    UserProfileCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        userName = state.value.success.fullName,
                        userPhone = "+${state.value.success.phoneNumber}",
                        imagePath = "drawable/profileIcon.png"
                    )
                }
                else {
                    UserProfileCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        userName = "",
                        userPhone = "",
                        imagePath = "drawable/profileIcon.png"
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                ProfileCardItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    name = getStrings("edit_profile"),
                    iconUrl = "drawable/profileUserIcon.png"
                )

                Spacer(modifier = Modifier.height(8.dp))

                ProfileCardItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    name = getStrings("language"),
                    iconUrl = "drawable/profileUserLan.png"
                )

                Spacer(modifier = Modifier.height(8.dp))

                ProfileCardItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    name = getStrings("log_out"),
                    iconUrl = "drawable/profileUserLogOut.png"
                )
            }
        }
        if (state.value.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.value.error, fontSize = 25.sp)
            }
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    override val options: TabOptions

        @Composable
        get() {
            val icon= painterResource("drawable/profileIcon.png")

            return TabOptions(
                index = 2u,
                title = "",
                icon = icon
            )
        }
}