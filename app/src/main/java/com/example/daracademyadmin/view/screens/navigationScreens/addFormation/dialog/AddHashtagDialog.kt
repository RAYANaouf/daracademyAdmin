package com.example.daracademyadmin.view.screens.navigationScreens.addFormation.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alphaspace.screens.common.textFields.AlphaTextField
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.R
import com.example.daracademyadmin.view.material.AlphaHashtag.AlphaHashtag
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite4
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddHashtagDialog(
    state : MaterialDialogState,
    hashtags : List<String> = emptyList(),
    onAddHashtag : (String)->Unit = {}
) {


    var hashtag by remember {
        mutableStateOf("")
    }

    MaterialDialog(
        dialogState = state,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = customWhite0,
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hashtag_icon) ,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(35.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Add Hashtag",
                    style = NormalTextStyles.TextStyleSZ4.copy(color = color1 , fontFamily = firaSansFamily)
                )
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(50.dp)
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                AlphaTextField(
                    text = hashtag,
                    onValueChange = {txt->
                        hashtag = txt.filter {chars-> chars.isLetter() or chars.equals("_")  }
                    },
                    textFieldStyle = NormalTextStyles.TextStyleSZ6 ,
                    hint = "Hashtag",
                    hintStyle = NormalTextStyles.TextStyleSZ6,
                    cursorColor = color1,
                    modifier = Modifier
                        .height(45.dp)
                        .weight(1f)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.send_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            onAddHashtag(hashtag)
                            hashtag = ""
                        }
                )

            }
            
            
            Spacer(modifier = Modifier
                .padding(top = 12.dp, bottom = 12.dp, start = 20.dp, end = 20.dp)
                .background(customWhite4)
                .height(1.dp)
                .fillMaxWidth(1f))


            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .height(80.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(start = 12.dp , end = 12.dp)
            ) {
                hashtags.forEach {
                    AlphaHashtag(txt = it , style = NormalTextStyles.TextStyleSZ9 , width = 2.dp , modifier = Modifier.padding(top = 4.dp , bottom = 4.dp))
                }
            }



            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 12.dp, top = 12.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(90.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color1)
                        .clickable {
                            state.hide()
                        }
                ){
                    Text(
                        text = "Done",
                        style = NormalTextStyles.TextStyleSZ8.copy(color = customWhite0 , fontFamily = firaSansFamily)
                    )
                }
            }


        }

    }

}


@Preview
@Composable
fun AddHashtagDialog_preview() {
    AddHashtagDialog(state = rememberMaterialDialogState())
}