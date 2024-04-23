package com.example.compouseuiapplication.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compouseuiapplication.R
import com.example.compouseuiapplication.data.Message

@Composable
fun MessageCard1(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun MessageCard2(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(painter = painterResource(R.drawable.profile_picture),
              contentDescription = "Contact profile picture",
              modifier = Modifier
                  // Set image size to 40 dp
                  .size(40.dp)
                  // Clip image to be shaped as a circle
                  .clip(CircleShape))

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
        Image(painter = painterResource(R.drawable.profile_picture),
              contentDescription = "Contact profile picture",
              modifier = Modifier
                  // Set image size to 40 dp
                  .size(40.dp)
                  // Clip image to be shaped as a circle
                  .clip(CircleShape)
                  .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape))

        //Modifier 只有 padding 沒有 margin，如開發中要使用margin時，改使用Spacer
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = msg.author, color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body, color = MaterialTheme.colorScheme.secondary)
        }
    }
}

@Composable
fun MessageCard4(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(painter = painterResource(R.drawable.profile_picture),
              contentDescription = null,
              modifier = Modifier
                  .size(40.dp)
                  .clip(CircleShape)
                  .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape))
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(msg.bExpanded) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.surface,
            label = "",
        )

        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable {
            isExpanded = !isExpanded
            msg.bExpanded = isExpanded
        }) {
            Text(text = msg.author, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)) {
                Text(text = msg.body, modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                     maxLines = if (isExpanded) Int.MAX_VALUE else 1, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessageCard1() {
    MessageCard1("預覽文字")
}

@Preview
@Composable
fun PreviewMessageCard2() {
    MessageCard2(Message(author = "Android Studio",
                         body = "Android Studio 帶來許多專屬於 Jetpack Compose 的新功能，它支援採用程式碼優先方法，同時還能提高開發人員的工作效率，而不需要在設計介面或程式碼編輯器中二選一。"))
}

@Preview
@Composable
fun PreviewMessageCard3() {
    MessageCard3(Message(author = "Android Studio",
                         body = "Android Studio 帶來許多專屬於 Jetpack Compose 的新功能，它支援採用程式碼優先方法，同時還能提高開發人員的工作效率，而不需要在設計介面或程式碼編輯器中二選一。"))
}

@Preview
@Composable
fun PreviewMessageCard4() {
    MessageCard4(Message(author = "Android Studio",
                         body = "Android Studio 帶來許多專屬於 Jetpack Compose 的新功能，它支援採用程式碼優先方法，同時還能提高開發人員的工作效率，而不需要在設計介面或程式碼編輯器中二選一。"))
}

