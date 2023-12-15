package com.example.daracademyadmin.view.screens.navigationScreens.addFormation.bottomSheet

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.alphaspace.screens.common.textFields.AlphaTextField
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.R
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customBlack9
import com.example.daracademyadmin.ui.theme.customWhite0
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun BottomSheet(
    show : Boolean,
    onDismiss : ()->Unit = {},
    hashtagList : List<String> = emptyList(),
    onAddHashtagClick : (String)->Unit = {},
    modifier: Modifier = Modifier
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val view = LocalView.current


    LaunchedEffect(key1 = keyboardController ){
        keyboardController?.hide()
    }


    if (show){

        var bottomSheetState = rememberModalBottomSheetState()
        var coroutineScope   = rememberCoroutineScope()

//        val rootView = wind


        var hashtag by remember {
            mutableStateOf("")
        }


        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { coroutineScope .launch { onDismiss() }},
            containerColor = customWhite0,
            modifier = modifier
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                AlphaTextField(
                    text = hashtag,
                    onValueChange = {txt->
                        hashtag = txt.filter {char-> char.equals("_") or char.isLetterOrDigit() }
                    },
                    textFieldStyle = NormalTextStyles.TextStyleSZ6,
                    hint = "enter a hashtag",
                    hintStyle = NormalTextStyles.TextStyleSZ6,
                    borderStroke = BorderStroke(
                        width = 2.dp,
                        color = color1,
                    ),
                    cursorColor = color1,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier
                        .height(45.dp)
                        .weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                val context = LocalContext.current
                val d = LocalDensity.current
                val it = WindowInsets.ime.getTop(d)
                val ib = WindowInsets.ime.getBottom(d)
                val ir = WindowInsets.ime.getLeft(d , LayoutDirection.Ltr)
                val il = WindowInsets.ime.getRight(d , LayoutDirection.Ltr)

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(45.dp)
                        .clickable {
                            onAddHashtagClick(hashtag)
                            Toast.makeText(context , "top : $it \n bottom : $ib" , Toast.LENGTH_LONG).show()
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.send_icon),
                        contentDescription =null ,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(35.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(1.dp)
                    .padding(start = 20.dp, end = 20.dp)
                    .background(customBlack9)
                    .align(Alignment.CenterHorizontally)
            )





        }
    }

}

@Preview
@Composable
fun BottomSheet_preview() {
    BottomSheet(show = true)
}