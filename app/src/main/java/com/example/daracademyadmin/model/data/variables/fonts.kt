package com.example.daracademyadmin.model.data.variables

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.daracademyadmin.R

val firaSansFamily = FontFamily(
    Font(R.font.playpen_sans_light       , FontWeight.Light),
    Font(R.font.playpen_sans_bold        , FontWeight.Bold),
    Font(R.font.playpen_sans_extra_light , FontWeight.ExtraLight),
    Font(R.font.playpen_sans_extra_bold  , FontWeight.ExtraBold),
    Font(R.font.playpen_sans_medium      , FontWeight.Medium),
    Font(R.font.playpen_sans_thin        , FontWeight.Thin),
    Font(R.font.playpen_sans_semi_bold   , FontWeight.SemiBold),
    Font(R.font.playpen_sans_regular     , FontWeight.Normal)
)

val josefinSansFamily = FontFamily(
    Font(R.font.josefin_sans_bold               , FontWeight.Bold                         ),
    Font(R.font.josefin_sans_semi_bold          , FontWeight.SemiBold                     ),
    Font(R.font.josefin_sans_light              , FontWeight.Light                        ),
    Font(R.font.josefin_sans_extra_light        , FontWeight.ExtraLight                   ),
    Font(R.font.josefin_sans_medium             , FontWeight.Medium                       ),
    Font(R.font.josefin_sans_thin               , FontWeight.Thin                         ),
    Font(R.font.josefin_sans_regular            , FontWeight.Normal                       ),


    //italic ones
    Font(R.font.josefin_sans_bold_italic        , FontWeight.Bold       , FontStyle.Italic),
    Font(R.font.josefin_sans_semi_bold_italic   , FontWeight.SemiBold   , FontStyle.Italic),
    Font(R.font.josefin_sans_light_italic       , FontWeight.Light      , FontStyle.Italic),
    Font(R.font.josefin_sans_extra_light_italic , FontWeight.ExtraLight , FontStyle.Italic),
    Font(R.font.josefin_sans_italic             , FontWeight.Normal     , FontStyle.Italic),
    Font(R.font.josefin_sans_medium_italic      , FontWeight.Medium     , FontStyle.Italic),
    Font(R.font.josefin_sans_thin_italic        , FontWeight.Thin       , FontStyle.Italic)
)