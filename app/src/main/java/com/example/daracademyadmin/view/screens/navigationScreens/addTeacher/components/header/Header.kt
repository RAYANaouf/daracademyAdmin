package com.example.daracademyadmin.view.screens.navigationScreens.addTeacher.components.header

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.daracademyadmin.R
import com.example.daracademyadmin.ui.theme.color1

@Composable
fun Header(
    photo_uri     : Uri? = null,
    onImageChange : (Uri)->Unit = {}
) {

    var launcher_img = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()){uri->
        if (uri != null){
            onImageChange(uri)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
    ){
        Image(
            painter = rememberAsyncImagePainter(model = if (photo_uri == null || photo_uri == Uri.EMPTY ) R.drawable.teacher else photo_uri),
            contentDescription = null,
            contentScale = if (photo_uri==null) ContentScale.Inside else ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clickable {
                    launcher_img.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
                .clip(if (photo_uri == null) RoundedCornerShape(0.dp) else CircleShape)
                .border(
                    width = 1.5.dp,
                    color = if (photo_uri == null) Color.Transparent else color1,
                    shape = CircleShape
                )
        )
        Box(
            modifier = Modifier
                .size(90.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.camera_icon ),
                contentDescription = null,
                tint = color1,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun Header_preview() {
    Header()
}