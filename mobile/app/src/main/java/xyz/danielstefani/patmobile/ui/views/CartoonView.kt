package xyz.danielstefani.patmobile.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import xyz.danielstefani.patmobile.ui.theme.Purple80
import xyz.danielstefani.patmobile.ui.viewmodels.CartoonViewModel

@Composable
fun CartoonView(
    controller: NavController,
    model: CartoonViewModel = CartoonViewModel.get()
) {
    val cartoon = model.currentCartoon.value()

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            FormField(key = "Name", value = cartoon.name!!)
            FormField(key = "Age", value = cartoon.age!!.toString())
            FormField(key = "Gender", value = cartoon.gender!!)
            FormField(key = "Show", value = cartoon.show!!)
            FormField(key = "Description", value = cartoon.description!!)
            FormField(key = "Color", value = cartoon.color!!)
            FormField(key = "Likes", value = cartoon.likes!!.toString())
            Divider(
                modifier = Modifier
                    .padding(10.dp)
                    .width(300.dp)
            )
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Save")
            }
        }
    }
}


@Composable
fun FormField(
    key: String,
    value: String
) {
    Column(
        Modifier.padding(bottom = 20.dp)
    ) {
        Text(
            text = key,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 5.dp)
        )
        TextField(
            placeholder = { Text(key) },
            value = value,
            onValueChange = { newValue ->

            },
            textStyle = TextStyle(
                fontSize = 17.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 2.dp,
                    color = Purple80,
                    shape = RoundedCornerShape(20.dp)
                )
                .width(300.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default
        )
    }
}