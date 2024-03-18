package com.example.compouseuiapplication.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compouseuiapplication.R
import com.example.compouseuiapplication.data.Message

object MessageCard {

    @Composable
    fun MessageCard1(name: String) {
        Text(text = "Hello $name!")
    }

    @Composable
    fun MessageCard2(msg: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
            )

            //Modifier 只有 padding 沒有 margin，如開發中要使用margin時，改使用Spacer
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = msg.author)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = msg.body)
            }
        }
    }

    @Composable
    fun MessageCard3(msg: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
            )

            //Modifier 只有 padding 沒有 margin，如開發中要使用margin時，改使用Spacer
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = msg.body,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}