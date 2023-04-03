package com.shuklansh.valonews.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shuklansh.valonews.MainActivityVlrNews
import com.shuklansh.valonews.Model.MatchResultsModel.Data
import com.shuklansh.valonews.Model.MatchResultsModel.MatchResultsModel
import com.shuklansh.valonews.Model.MatchResultsModel.Segment
import com.shuklansh.valonews.R
import kotlinx.coroutines.launch
import java.lang.Integer.parseInt


class MatchResultsFragment : Fragment() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                val alltext = Color.White
                val wincol = Color(0xFF65DFBB)
                val losecol = Color(0xFFFF2133)
                val unicodes = mapOf("flag_US" to 0x1F1FA)
                var scope = rememberCoroutineScope()
                var fetchedMatchResults by remember { mutableStateOf(false) }
                var listOfMatchResults by remember {
                    mutableStateOf(
                        MatchResultsModel(
                            Data(
                                listOf(
                                    Segment(
                                        "", "", "", "", "", "", "", "", "", "", ""
                                    )
                                ), 0
                            )
                        )
                    )
                }
                scope.launch {
                    listOfMatchResults = MainActivityVlrNews().getMatchResults()
                    fetchedMatchResults = true
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF272727))
                ) {
                    if(fetchedMatchResults){
                    LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp) ,verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(items = listOfMatchResults.data.segments) {
                            Column(
                                modifier = Modifier.fillParentMaxWidth().clip(RoundedCornerShape(100f)).background(Color.Black).padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            ) {
                                Spacer(modifier = Modifier.height(12.dp))
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        color=alltext,
                                        text = it.tournament_name,
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center, maxLines = 2
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Top
                                    ) {
                                        Spacer(modifier = Modifier.height(22.dp))
                                        Text(
                                            text = it.team1,
                                            color = coloftextOne(it.score1,it.score2),
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Center, maxLines = 1
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text(
                                            text = it.score1,
                                            fontSize = 28.sp,
                                            color = coloftextOne(it.score1, it.score2),
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text(text = it.flag1, fontSize = 18.sp,color=alltext)
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Top
                                    ) {
                                        Spacer(modifier = Modifier.height(22.dp))
                                        Text(
                                            color = coloftextTwo(it.score1, it.score2),
                                            text = it.team2,
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Center, maxLines = 1
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text(
                                            text = it.score2,
                                            fontSize = 28.sp,
                                            color = coloftextTwo(it.score1, it.score2),
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text(text = it.flag2, fontSize = 18.sp,color=alltext,)
                                        Spacer(modifier = Modifier.height(12.dp))
                                    }
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(text = it.round_info,color=alltext,)

                                Spacer(modifier = Modifier.height(12.dp))
                                Text(text = it.time_completed,color=alltext,)

                                Spacer(modifier = Modifier.height(12.dp))
                                AsyncImage(
                                    model = it.tournament_icon,
                                    contentDescription = "tourney icon"
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }

                        }
                    }}
                    else{
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black),
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

fun coloftextOne(score1: String, score2: String): Color {
    return if (score1.toInt() > score2.toInt()) {
        Color(0xFF65DFBB)
    } else {
        Color(0xFFFF2133)
    }
}


fun coloftextTwo(score1: String, score2: String): Color {
    return if (score2.toInt() > score1.toInt()) {
        Color(0xFF65DFBB)
    } else {
        Color(0xFFFF2133)
    }
}