package com.example.daracademy.view.screens.chatScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.dataClasses.MessageBox
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.ui.theme.customBlack0
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel

@Composable
fun ChatBoxsScreen(
    navController  : NavController,
    viewModel      : DaracademyAdminViewModel,
    modifier       : Modifier = Modifier
) {

    val context = LocalContext.current

    viewModel.getAllMessageBoxs(
        onSuccessCallBack = {boxs,size->

        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {



        LazyColumn(
            contentPadding = PaddingValues(top = 8.dp , end = 8.dp),
            modifier = Modifier
                .weight(1f)
        ) {

            items(viewModel.boxMessages){
                Item(
                    messageBox = it,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("${Screens.ChatScreen().root}/${it.id}")
                        }
                        .padding(top = 8.dp, bottom = 8.dp)

                )
            }

        }

    }


}


@Composable
fun Item(
    messageBox : MessageBox,
    modifier: Modifier = Modifier
) {
    
    Row(
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.width(16.dp))

        Image(
            painter = painterResource(id = R.drawable.student_icon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
//                .background(Color.Magenta)
                .padding(top = 16.dp)
                .size(45.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = messageBox.name ,
                style = NormalTextStyles.TextStyleSZ4.copy(color = customBlack0 , fontFamily = firaSansFamily , fontWeight = FontWeight.SemiBold ),
                modifier = Modifier
            )

            Text(
                text = messageBox.lastMessage,
                style = NormalTextStyles.TextStyleSZ9.copy(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }


        Spacer(modifier = Modifier.width(26.dp))
    }


    
}


@Preview
@Composable
fun ChatBoxsScreen_preview() {
    ChatBoxsScreen(
        navController = rememberNavController(),
        viewModel = viewModel()
    )
}