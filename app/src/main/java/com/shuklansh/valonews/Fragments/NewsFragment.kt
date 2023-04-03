package com.shuklansh.valonews.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shuklansh.valonews.MainActivityVlrNews
import com.shuklansh.valonews.Model.NewsModel.Data
import com.shuklansh.valonews.Model.NewsModel.NewsModel
import com.shuklansh.valonews.Model.NewsModel.Segment
import com.shuklansh.valonews.R
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {


    @SuppressLint("CoroutineCreationDuringComposition", "InvalidColorHexValue")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                val coltext = Color.White
                val shadecol = Color(0xFF0E0000)
                val col = Color.Black.copy(alpha = 0.94f)
                var dataFetched by remember {
                    mutableStateOf(false)
                }
                var scope = rememberCoroutineScope()
                var newsList by remember {
                    mutableStateOf(
                        NewsModel(
                            Data(
                                listOf<Segment>(
                                    Segment("", "", "", "", "")
                                ), 0
                            )
                        )
                    )
                }
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    scope.launch {
                        newsList = MainActivityVlrNews().getNews()
                        dataFetched = true
                    }
                    if (dataFetched) {
                        Log.d("news", newsList.data.segments[0].toString())
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(col)
                        ) {
//                            Text(text = newsList.data.segments[0].title , fontSize = 32.sp , color = Color.White)
//                            Spacer(modifier = Modifier.height(20.dp))
//
//                            Text(text = newsList.data.segments[0].author, fontSize = 12.sp , color = Color.White)
//                            Spacer(modifier = Modifier.height(20.dp))
//
//                            Text(text = newsList.data.segments[0].date, fontSize = 12.sp , color = Color.White )
//                            Spacer(modifier = Modifier.height(20.dp))
//
//                            Text(text = newsList.data.segments[0].description , fontSize = 12.sp , color = Color.White)
//                            Spacer(modifier = Modifier.height(20.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(items = newsList.data.segments) {
                                    Box(
                                        modifier = Modifier
                                            .padding(12.dp)
                                            .clip(
                                                RoundedCornerShape(50f)
                                            )
                                            .background(Color.Black)
                                    ) {
                                        AsyncImage(contentScale = ContentScale.FillBounds,
                                            modifier = Modifier.blur(16.dp)
                                                .fillParentMaxWidth()
                                                .fillParentMaxHeight(0.5f)
                                                .drawWithContent {
                                                    drawContent()
                                                    drawRect(
                                                        Brush.verticalGradient(
                                                            listOf(
                                                                Color.Transparent,
                                                                shadecol
                                                            ),
                                                            -700f,
                                                            660f
                                                        )
                                                    )
                                                }.clip(RoundedCornerShape(50f)),
                                            model = "https://seeklogo.com/images/V/valorant-logo-FAB2CA0E55-seeklogo.com.png",
                                            contentDescription = "valogo"
                                        )
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(22.dp)
                                        ) {

                                            Text(
                                                text = it.title,
                                                fontSize = 28.sp,
                                                color = coltext,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.height(20.dp))

                                            Text(
                                                text = it.author,
                                                fontSize = 12.sp,
                                                color = coltext
                                            )
                                            Spacer(modifier = Modifier.height(20.dp))

                                            Text(
                                                text = it.date,
                                                fontSize = 12.sp,
                                                color = coltext
                                            )
                                            Spacer(modifier = Modifier.height(20.dp))

                                            Text(
                                                text = it.description,
                                                fontSize = 18.sp,
                                                color = coltext.copy(alpha=0.62f)
                                            )
                                            Spacer(modifier = Modifier.height(20.dp))

                                        }

                                    }
                                }
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(col),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(  color = Color(0xFFFF0000))

                        }
                    }


                }
            }
        }
    }


}