package com.example.daracademyadmin.graphics.screens.navigationScreens.addPost

import android.graphics.Color.parseColor
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.graphics.material.AlphaTextFields.AlphaUnderLinedTextField
import com.example.daracademyadmin.graphics.material.alphaBottomBar.AlphaBottomBar
import com.example.daracademyadmin.model.viewModel.DaracademyAdminViewModel
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite2

@Composable
fun AddPostScreen(
    viewModel : DaracademyAdminViewModel,
    onNavigate : (Screens)->Unit = {},
    modifier: Modifier = Modifier
) {


    var images : List<Uri> by rememberSaveable {
        mutableStateOf(emptyList())
    }

    val launcher_images = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()){
        images = it
    }

    var title by rememberSaveable {
        mutableStateOf("")
    }
    var desc by rememberSaveable {
        mutableStateOf("")
    }

    var onLoad by rememberSaveable {
        mutableStateOf(false)
    }

    var context = LocalContext.current


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(parseColor("#F9F9F9")))
    ) {


        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 30.dp, end = 26.dp)
                    .height(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.daracademy),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                AlphaUnderLinedTextField(
                    text = title,
                    textFieldStyle = NormalTextStyles.TextStyleSZ6,
                    onValueChange = {_title->
                        title = _title
                    },
                    hint = "the Post's title",
                    maxLine = 1,
                    hintStyle = NormalTextStyles.TextStyleSZ6,
                    underLineColor = color1,
                    underLineWidth = 2.5f,
                    modifier = Modifier
                        .fillMaxHeight()
                )

            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .padding(start = 26.dp , end = 26.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(color1)
                )

                Spacer(modifier = Modifier.width(16.dp))

                AlphaUnderLinedTextField(
                    text = desc,
                    onValueChange = {_desc->
                        desc = _desc
                    },
                    textFieldStyle = NormalTextStyles.TextStyleSZ9,
                    hint = "here is the description of your post.",
                    hintStyle = NormalTextStyles.TextStyleSZ9,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = color1.copy(alpha = 0.5f),
                        strokeWidth = 2f,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f)
                    )
                }
                .background(customWhite0)
                .horizontalScroll(rememberScrollState())
        ) {
            
            Spacer(modifier = Modifier.width(12.dp))

            images.forEachIndexed { index, uri ->
                Image(
                    painter = rememberAsyncImagePainter(model = uri),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(150.dp)
                        .width(95.dp)
                        .padding(top = 10.dp, bottom = 10.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            width = 1.dp,
                            color = customWhite2,
                            shape = RoundedCornerShape(8.dp)
                        )


                )

                Spacer(modifier = Modifier.width(12.dp))
            }

        }

        AlphaBottomBar(
            onAddImageClick = {
                launcher_images.launch(
                    "image/*"
                )
            },
            onLoad = onLoad,
            onPostClick = {
                onLoad = true
                viewModel.addPost(
                    name    = title   ,
                    desc    = desc    ,
                    context = context ,
                    images  = images  ,
                    onSuccessCallBack = {
                        onLoad = false
                        onNavigate(Screens.HomeScreen())
                        Toast.makeText(context , "the post is added successfully" , Toast.LENGTH_LONG).show()
                    },
                    onFailureCallBack = {
                        onLoad = false
                    }
                )
            }
        )


    }

}


@Preview
@Composable
fun AddPostScreen_preview() {

    val context = LocalContext.current

    AddPostScreen(
        viewModel = viewModel(
        factory = object  : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DaracademyAdminViewModel::class.java))
                    return DaracademyAdminViewModel(context) as T
                throw IllegalArgumentException("can't create DaracademyAdminViewModel (MainActivity)")
            }
        }
    )
    )
}