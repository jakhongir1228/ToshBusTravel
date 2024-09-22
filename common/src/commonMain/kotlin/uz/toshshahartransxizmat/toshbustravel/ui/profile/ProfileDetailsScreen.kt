package uz.toshshahartransxizmat.toshbustravel.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.components.button.Button
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.Icon
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.IconValue
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.components.input.text.TextInput
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.UserProfileEntity
import uz.toshshahartransxizmat.toshbustravel.ui.profile.viewModel.ProfileViewModel
import uz.toshshahartransxizmat.toshbustravel.util.Other
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class ProfileDetailsScreen: Screen {

    init {
        Other.isBottomBarVisible = false
    }

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var firstName by remember { mutableStateOf("") }
        val isFirstNameValid = firstName.isNotEmpty()
        val viewModel = rememberKoinInject<ProfileViewModel>()
        val state = viewModel.state.collectAsState()

        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PageHeader(
                    type = PageHeaderType.Heading(text = getStrings("edit_profile")),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    onNavigationClick = {
                        navigator.pop()
                        Other.isBottomBarVisible = true
                    }
                )

                Box(contentAlignment = Alignment.BottomEnd) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFC1C1)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "B", fontSize = 40.sp, fontWeight = FontWeight.Bold)
                    }

                    Icon(
                        icon = IconValue("drawable/iconCamera.png"),
                        contentDescription = "Edit Image",
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.Black)
                            .padding(8.dp),
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                TextInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = TextValue(getStrings("full_name")),
                    keyboardOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions(),
                    placeholder = TextValue(getStrings("enter_full_name"))
                )

                Spacer(modifier = Modifier.weight(weight = 1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 56.dp),
                    text = TextValue(getStrings("continue")),
                    size = ButtonSize.Large,
                    enabled = isFirstNameValid,
                    loading = state.value.isLoading,
                    onClick = {
                        val r = UserProfileEntity(
                            fullName = firstName,
                            imgBase64 = "base64"
                        )
                        viewModel.loadUpdateClient(r)
                    }
                )

            }
            if (state.value.error.isNotBlank()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = state.value.error, fontSize = 25.sp)
                }
            }
            if (state.value.isLoaded){
                navigator.pop()
                Other.isBottomBarVisible = true
            }
        }
    }

}