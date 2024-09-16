package uz.toshshahartransxizmat.toshbustravel.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import uz.toshshahartransxizmat.toshbustravel.ui.profile.component.ProfileCardItem
import uz.toshshahartransxizmat.toshbustravel.ui.profile.component.UserProfileCard
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal object ProfileTab: Tab {

    @Composable
    override fun Content() {
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

                UserProfileCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    userName = "Nikolayev Nikolay",
                    userPhone = "+998 90 800 80 50",
                    imageUrl = "drawable/profileIcon.png"
                )

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