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
import androidx.compose.runtime.State
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
import uz.toshshahartransxizmat.toshbustravel.components.dialog.ErrorDialog
import uz.toshshahartransxizmat.toshbustravel.ui.profile.component.ProfileCardItem
import uz.toshshahartransxizmat.toshbustravel.ui.profile.component.UserProfileCard
import uz.toshshahartransxizmat.toshbustravel.ui.profile.state.ProfileState
import uz.toshshahartransxizmat.toshbustravel.util.Other
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class ProfileScreen(
    private val state: State<ProfileState>,
    private val loadGetClient: () -> Unit,
): Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        var showErrorDialog by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            loadGetClient.invoke()
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
                    iconUrl = "drawable/profileUserIcon.png",
                ){
                    navigator.push(ProfileDetailsScreen())
                }

                Spacer(modifier = Modifier.height(8.dp))

                ProfileCardItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    name = getStrings("language"),
                    iconUrl = "drawable/profileUserLan.png"
                ){

                }

                Spacer(modifier = Modifier.height(8.dp))

                ProfileCardItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    name = getStrings("log_out"),
                    iconUrl = "drawable/profileUserLogOut.png"
                ){

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
    }
}