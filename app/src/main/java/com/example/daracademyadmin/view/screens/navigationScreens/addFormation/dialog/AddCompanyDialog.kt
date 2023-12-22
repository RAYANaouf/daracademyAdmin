package com.example.daracademyadmin.view.screens.navigationScreens.addFormation.dialog

import android.graphics.Color.parseColor
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.alphaspace.screens.common.textFields.AlphaTextField
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.dataClasses.Company
import com.example.daracademyadmin.view.material.AlphaHashtag.AlphaHashtag
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite3
import com.example.daracademyadmin.ui.theme.customWhite4
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun AddCompanyDialog(
    state        : MaterialDialogState,
    onCompanyAdd : (Company)->Unit = {}
) {


    var image_uri : Uri? by remember{
        mutableStateOf(null)
    }

    var company_name : String by remember{
        mutableStateOf("")
    }

    val launcher_image = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent() ){uri->
        if(uri != null){
            image_uri = uri

        }
    }
    
    val context = LocalContext.current


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
                Text(
                    text = "Add Company",
                    style = NormalTextStyles.TextStyleSZ4.copy(color = color1 , fontFamily = firaSansFamily)
                )
            }


            Image(
                painter = if (image_uri == null) painterResource(id = R.drawable.company) else rememberAsyncImagePainter(model =image_uri) ,
                contentDescription = null,
                contentScale = if (image_uri == null)  ContentScale.Inside  else  ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color(parseColor("#e5e5e5")))
                    .padding(if (image_uri == null) 5.dp else 0.dp)
                    .clickable {
                        launcher_image.launch("image/*")
                    }
            )


            Spacer(modifier = Modifier.height(36.dp))


            AlphaTextField(
                text = company_name,
                onValueChange = {txt->
                    company_name = txt
                },
                textFieldStyle = NormalTextStyles.TextStyleSZ6 ,
                hint = "Company name",
                hintStyle = NormalTextStyles.TextStyleSZ6,
                cursorColor = color1,
                modifier = Modifier
                    .height(45.dp)
                    .width(220.dp)
            )


            Spacer(modifier = Modifier.height(36.dp))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = 12.dp , bottom = 12.dp , start = 16.dp, end = 16.dp)
                        .clickable {
                            state.hide()
                        }
                ) {
                    Text(
                        text = "Cancel",
                        style = NormalTextStyles.TextStyleSZ8.copy(color = customWhite3 , fontFamily = firaSansFamily)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = 12.dp , bottom = 12.dp , start = 16.dp, end = 16.dp)
                        .clickable {
                            if (image_uri== null || company_name == ""){
                                Toast.makeText(context , "select photo and name" , Toast.LENGTH_LONG).show()
                                return@clickable
                            }
                            onCompanyAdd(Company(name = company_name , img = image_uri.toString()))
                            state.hide()
                        }
                ) {
                    Text(
                        text = "Done",
                        style = NormalTextStyles.TextStyleSZ8.copy(color = color1 , fontFamily = firaSansFamily)
                    )
                }
            }

        }

    }

}


@Preview
@Composable
fun AddHashtagDialog_preview() {
    AddCompanyDialog(state = rememberMaterialDialogState())
}