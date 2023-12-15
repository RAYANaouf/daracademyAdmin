package com.example.daracademyadmin.view.screens.fullScreen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.alphaspace.screens.common.textFields.AlphaTextField
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customWhite0

@Composable
fun SignInScreen(
    viewModel     : DaracademyAdminViewModel,
    modifier: Modifier = Modifier
) {


    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    var context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(customWhite0)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.daracademy),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AlphaTextField(
            text = email ,
            onValueChange = {
                email = it
            } ,
            textFieldStyle = NormalTextStyles.TextStyleSZ5,
            hint = "Email",
            hintStyle = NormalTextStyles.TextStyleSZ5,
            borderStroke = BorderStroke(
                width = 2.dp,
                color = color1
            ),
            cursorColor = color1,
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(55.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))

        AlphaTextField(
            text = password ,
            onValueChange = {
                password = it
            } ,
            textFieldStyle = NormalTextStyles.TextStyleSZ5,
            hint = "Password",
            hintStyle = NormalTextStyles.TextStyleSZ5,
            borderStroke = BorderStroke(
                width = 2.dp,
                color = color1
            ),
            cursorColor = color1,
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(55.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))

        Button(
            onClick = {
                if (email == "" || password ==""){
                    Toast.makeText(context , "enter the email and password" , Toast.LENGTH_LONG).show()
                    return@Button
                }


                viewModel.signIn(email , password ,
                    onSuccessCallBack = {
                        Toast.makeText(context , "Welcome in Daraacademy admin space" , Toast.LENGTH_LONG).show()
                        viewModel.setAppScreen(Screens.HomeScreen())
                    },
                    onFailureCallBack = {
                        Toast.makeText(context , "failed" , Toast.LENGTH_LONG).show()
                    }
                )

            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color1,
                contentColor   = customWhite0
            ),
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth(0.85f)
        ) {
            Text(
                text = "Done",
                style = NormalTextStyles.TextStyleSZ6.copy(color = customWhite0)
            )
        }
    }

}