package com.example.daracademyadmin.view.screens.navigationScreens.addTeacher.components.informationFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alphaspace.screens.common.dropDownMenu.AlphaDropDownMenu
import com.example.alphaspace.screens.common.textFields.AlphaTextField
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.variables.josefinSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color3

@Composable
fun informationFields(
    name        : String = "the name" ,
    email       : String = "email",
    password    : String = "the password",
    numberType  : String = "07",
    number      : String = "",
    onChange : (type : String,txt : String)->Unit = {_,_->},
) {

    var expanded by remember{
        mutableStateOf(false)
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        AlphaTextField(
            text = name,
            textFieldStyle = NormalTextStyles.TextStyleSZ7,
            onValueChange = {
                onChange("name" , it)
            },
            hint = "Teacher name",
            hintStyle = NormalTextStyles.TextStyleSZ7,
            cursorColor = color1,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
    }

    Spacer(
        modifier = Modifier
            .height(24.dp)
    )


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        AlphaTextField(
            text = email,
            textFieldStyle = NormalTextStyles.TextStyleSZ7,
            onValueChange = {
                onChange("email" , it)
            },
            hint = "Teacher email",
            hintStyle = NormalTextStyles.TextStyleSZ7,
            cursorColor = color1,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
    }

    Spacer(
        modifier = Modifier
            .height(24.dp)
    )


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    expanded = true
                }
        ) {

            AlphaDropDownMenu(
                expanded = expanded,
                items = listOf("05","06","07"),
                onClick = {type-> onChange("numberType" , type)},
                onDismissRequest = { expanded = false })

            Text(
                text = numberType,
                style = NormalTextStyles.TextStyleSZ6.copy(color = color1 , fontFamily = josefinSansFamily),
                modifier = Modifier
                    .offset(x = 0.dp , y = (-3).dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        AlphaTextField(
            text = number,
            textFieldStyle = NormalTextStyles.TextStyleSZ7,
            onValueChange = {txt->
                onChange("number" , txt)
            },
            hint = "Phone number",
            hintStyle = NormalTextStyles.TextStyleSZ7,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone
            ),
            cursorColor = color1,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(color3)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.phone_fulfilled_icon),
                contentDescription = null,
                tint = color1,
                modifier = Modifier
                    .fillMaxSize(0.55f)
            )
        }

    }


    Spacer(
        modifier = Modifier
            .height(24.dp)
    )


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        AlphaTextField(
            text = password,
            textFieldStyle = NormalTextStyles.TextStyleSZ7,
            onValueChange = {
                onChange("password" , it)
            },
            hint = "Teacher password",
            hintStyle = NormalTextStyles.TextStyleSZ7,
            cursorColor = color1,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
    }

    Spacer(
        modifier = Modifier
            .height(55.dp)
    )

}


@Preview
@Composable
fun informationFields_preview() {
    informationFields()
}