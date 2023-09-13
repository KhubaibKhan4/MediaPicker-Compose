package com.codespacepro.mediapicker

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.codespacepro.mediapicker.ui.theme.MediaPickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaPickerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    SingleMediaPicker()

                    MultiMediaPicker()
                }
            }
        }
    }
}

@Composable
fun SingleMediaPicker() {
    var singleImagePicker by remember {
        mutableStateOf<Uri?>(null)
    }

    var imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            singleImagePicker = it
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Multi Media Picker",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = {
                imagePickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(0.75f),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "",
                    tint = Color.White
                )
                Text(text = "Pick Images")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage(
            model = singleImagePicker, contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(300.dp)
                .padding(20.dp)
        )

    }
}

@Composable
fun MultiMediaPicker() {
    var imagesPicker by remember {
        mutableStateOf<List<Uri?>>(emptyList())
    }
    var imagesLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 2),
        onResult = {
            imagesPicker = it
        }
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Multi Media Picker",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = {
                imagesLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(0.75f),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "",
                    tint = Color.White
                )
                Text(text = "Pick Images")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(imagesPicker) {
                AsyncImage(
                    model = it,
                    contentDescription = "",
                    modifier = Modifier.padding(20.dp)
                        .clip(shape = RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.FillBounds,
                    transform = AsyncImagePainter.DefaultTransform,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MediaPickerTheme {

    }
}