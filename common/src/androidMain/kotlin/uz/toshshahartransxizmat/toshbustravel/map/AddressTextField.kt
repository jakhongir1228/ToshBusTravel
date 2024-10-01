package uz.toshshahartransxizmat.toshbustravel.map

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import org.jetbrains.compose.resources.ExperimentalResourceApi
import uz.toshshahartransxizmat.toshbustravel.R
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonType.GhostOutline.borderColor


@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddressTextField(
    hint: String,
    addressText: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorText: String?
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { intent ->
                val place = Autocomplete.getPlaceFromIntent(intent)
                onTextChange(place.address ?: "")
            }
        } else if (result.resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(context, "Autocomplete canceled", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS)
                val intent = Autocomplete
                    .IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                    .build(context)
                launcher.launch(intent)
            }
    ) {
        // Embedded TextField from original code with modifications
        OutlinedTextField(
            value = addressText,
            onValueChange = onTextChange,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Gray,
                    fontSize = 10.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, borderColor),
                    shape = RoundedCornerShape(16.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            textStyle = MaterialTheme.typography.bodySmall,
            enabled = false
           /* trailingIcon = {
                Icon(
                    painter = org.jetbrains.compose.resources.painterResource(res = "drawable/cancel.png"),
                    contentDescription = "Clear Text",
                    modifier = Modifier.size(20.dp)
                )
            },*/

        )
    }

    if (!errorText.isNullOrEmpty()) {
        Text(
            text = errorText,
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 40.dp, top = 4.dp)
        )
    }
}
