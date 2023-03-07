package com.misra.jetintrosl

import androidx.compose.ui.graphics.Color
import com.misra.jetintrosl.ui.theme.ColorBlue

data class OnBoardingData(val image: Int,
                          val backgroundColor: Color = Color.Blue,
                          val mainColor: Color = ColorBlue,
                          val mainText: String,
                          val subText: String
                        )
