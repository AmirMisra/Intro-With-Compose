package com.misra.jetintrosl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.misra.jetintrosl.ui.theme.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetIntroSlTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    // color = MaterialTheme.colors.background
                ) {
                    val items = arrayListOf<OnBoardingData>()

                    items.add(
                        OnBoardingData(
                            R.drawable.ic_launcher_foreground,
                            backgroundColor = Color(0xff008ac3),
                            mainColor = Color(0xff00b5ea),
                            mainText = "First",
                            subText = "subFirst subFirst subFirst subFirst subFirst " +
                                    "subFirst subFirst subFirst subFirst subFirst" +
                                    "subFirst subFirst subFirst subFirst subFirst"
                        )
                    )
                    items.add(
                        OnBoardingData(
                            R.drawable.ic_launcher_foreground,
                            backgroundColor = Color(0xffe4af19),
                            mainColor = ColorYellow,
                            mainText = "First",
                            subText = "subFirst subFirst subFirst subFirst subFirst " +
                                    "subFirst subFirst subFirst subFirst subFirst" +
                                    "subFirst subFirst subFirst subFirst subFirst"
                        )
                    )
                    items.add(
                        OnBoardingData(
                            R.drawable.ic_launcher_foreground,
                            backgroundColor = Color(0xff96e172),
                            mainColor = ColorGreen,
                            mainText = "First",
                            subText = "subFirst subFirst subFirst subFirst subFirst " +
                                    "subFirst subFirst subFirst subFirst subFirst" +
                                    "subFirst subFirst subFirst subFirst subFirst"
                        )
                    )

                    val pagerState = RememberagerState(
                        pageCount = items.size,
                        initalOfScreenLimit = 2,
                        infinitLoop = false,
                        initialPage = 0
                    )

                    OnBoarderinPager(
                        item = items,
                        pagerState = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun PagerIndicator(items: List<OnBoardingData>, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        repeat(items.size) {
            Indicator(
                isSelected = it == currentPage,
                color = items[it].mainColor
            )
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean, color: Color) {
    val width = animateDAsState(targetValue = if (isSelected) 40.dp else 10.dp)

    Box(
        modifier = Modifier.padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) color else Color.Gray.copy(alpha = 0.5f))
    ) {

    }
}

fun animateDAsState(targetValue: Dp): Any {

}

@Composable
fun OnBoarderinPager(
    item: List<OnBoardingData>,
    pagerState: PagerState
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(item[page].backgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        painter = painterResource(id = item[page].image),
                        contentDescription = "img",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
                backgroundColor = Color.White,
                elevation = 0.dp,
                shape = bottomCardShape.large
            ) {
                Box {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        PagerIndicator(items = item, currentPage = PagerState.currentPage)

                        Text(
                            text = item[pagerState.currentPage].mainText,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp, 30.dp),
                            color = item[pagerState.currentPage].mainColor,
//                            FontFamily = poppins,
                            TextAlign = TextAlign.Right,
                            fontsize = 20.sp,
                            fontWeith = FontWeight.Bold
                        )
                        Text(
                            text = item[pagerState.currentPage].mainText,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, start = 30.dp, end = 20.dp),
                            color = Color.Gray,
//                            FontFamily = poppins,
                            TextAlign = TextAlign.Center,
                            fontsize = 20.sp,
                            fontWeith = FontWeight.ExtraLight
                        )
                    }// End column

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(30.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (pagerState.currentPage != 2) {
                                TextButton(onClick = { /*TODO*/ }) {
                                    Text(
                                        text = "Skip Now",
                                        color = Color(0xFF292D32),
                                        fontFamily = poppins,
                                        textAlign = TextAlign.Right,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }

                                OutlinedButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_launcher_background),
                                        contentDescription = "icon",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }//End if(pagerStat)

                            else {
                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = item[pagerState.currentPage].mainColor
                                    ),
                                    contentPadding = PaddingValues(vertical = 12.dp),
                                    elevation = ButtonDefaults.elevation(0.dp),
                                    onClick = { /*TODO*/ },

                                    ) {
                                    Text(
                                        text = "Get Started",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
