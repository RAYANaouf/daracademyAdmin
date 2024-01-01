package com.example.daracademy.view.screens.chatScreen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.daracademy.view.material.lottie.LottieAnimation_loading
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.model.variables.josefinSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customBlack0
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite4
import com.example.daracademyadmin.ui.theme.customWhite5
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel

@Composable
fun ChatBoxsScreen(
    navController  : NavController,
    viewModel      : DaracademyAdminViewModel,
    modifier       : Modifier = Modifier
) {

    val context = LocalContext.current


    Column(
        modifier = modifier
            .fillMaxSize()
    ) {


        if (false){
            LottieAnimation_loading()
        }
        else{
            LazyColumn(
                contentPadding = PaddingValues(top = 8.dp , start = 16.dp ,  end = 16.dp),
                modifier = Modifier
                    .weight(1f)
            ) {

                items(viewModel.repo.chatBoxs){
                    Item(
                        messageBox = it,
                        modifier = Modifier
                            .clickable {
                                navController.navigate("${Screens.ChatScreen().root}/${it.userId}/${it.productId}/${it.name}")
                            }
                            .padding(top = 8.dp, bottom = 8.dp)

                    )

                    Spacer(modifier = Modifier.height(16.dp))

                }

            }

        }


    }


}


@Composable
fun Item(
    messageBox : MessageBox,
    modifier: Modifier = Modifier
) {
    
    Surface(
        color = customWhite0,
        shadowElevation = 1.dp,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            2.dp,
            color = customWhite4
        )
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
                    .padding(top = 16.dp, bottom = 16.dp)
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
                    style = NormalTextStyles.TextStyleSZ4.copy(color = color1 , fontFamily = firaSansFamily , fontWeight = FontWeight.SemiBold ),
                    modifier = Modifier
                )

                Text(
                    text = messageBox.lastMessage,
                    style = NormalTextStyles.TextStyleSZ7.copy(color = customWhite5 , fontFamily = josefinSansFamily , fontWeight = FontWeight.SemiBold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }


            Spacer(modifier = Modifier.width(26.dp))
        }
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