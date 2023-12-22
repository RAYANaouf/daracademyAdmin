package com.example.daracademyadmin.view.screens.navigationScreens.stageScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.model.sealedClasses.phaseDesEtudes.PhaseDesEtudes
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color2
import com.example.daracademyadmin.ui.theme.color3
import com.example.daracademyadmin.ui.theme.customWhite0

@Composable
fun StageScreen(
    navController : NavController,
    modifier : Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {


        Spacer(modifier = Modifier.height(46.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    navController.navigate("${Screens.AnneesScreen().root}/${PhaseDesEtudes.Primaire().phase}"){
                        popUpTo(Screens.HomeScreen().root){
                            inclusive = true
                        }
                    }
                }
                .background(color1)
        ) {
            Text(
                text = "Primaire",
                style = NormalTextStyles.TextStyleSZ2.copy(color = customWhite0 , fontWeight = FontWeight.Bold , fontFamily = firaSansFamily)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    navController.navigate("${Screens.AnneesScreen().root}/${PhaseDesEtudes.CEM().phase}"){
                        popUpTo(Screens.HomeScreen().root){
                            inclusive = true
                        }
                    }
                }
                .background(color2)
        ) {
            Text(
                text = "C.E.M",
                style = NormalTextStyles.TextStyleSZ2.copy(color = customWhite0 , fontWeight = FontWeight.Bold , fontFamily = firaSansFamily)
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    navController.navigate("${Screens.AnneesScreen().root}/${PhaseDesEtudes.Lycee().phase}"){
                        popUpTo(Screens.HomeScreen().root){
                            inclusive = true
                        }
                    }
                }
                .background(color3)
        ) {
            Text(
                text = "Lucee",
                style = NormalTextStyles.TextStyleSZ2.copy(color = customWhite0 , fontWeight = FontWeight.Bold , fontFamily = firaSansFamily)
            )
        }


        Spacer(modifier = Modifier.height(46.dp))
    }

}


@Preview
@Composable
fun StageScreen_preview() {
    StageScreen(
        navController = rememberNavController()
        )
}