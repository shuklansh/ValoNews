package com.shuklansh.valonews.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.findNavController
import coil.compose.AsyncImage
import com.shuklansh.valonews.MainActivityVlrNews
import com.shuklansh.valonews.Model.MatchResultsModel.Data
import com.shuklansh.valonews.Model.MatchResultsModel.Segment
import com.shuklansh.valonews.Model.RankingRegionModel.RankkingModel
import com.shuklansh.valonews.Model.UpcomingMatchModel.UpcomingMatchModel
import com.shuklansh.valonews.R
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class HomeFragment : Fragment() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                var distinctList = mutableListOf<String>()
                var rankingsRegion by remember {
                    mutableStateOf(
                        RankkingModel(
                            listOf(
                                com.shuklansh.valonews.Model.RankingRegionModel.Data(
                                    "", "", "", "", "", "", "", "", ""
                                )
                            ), 0
                        )
                    )
                }
                var fetchedRanking by remember { mutableStateOf(false) }
                var fetchedMatchResults by remember { mutableStateOf(false) }
                var listOfMatchResults by remember {
                    mutableStateOf(
                        UpcomingMatchModel(
                            Data(
                                listOf(
                                    Segment(
                                        "", "", "", "", "", "", "", "", "", "", ""
                                    )
                                ),
                                0
                            )
                        )
                    )
                }
                var EUlistOfMatchResults by remember {
                    mutableStateOf(
                        RankkingModel(
                            listOf(
                                com.shuklansh.valonews.Model.RankingRegionModel.Data(
                                    "", "", "", "", "", "", "", "", ""
                                )
                            ), 0
                        )
                    )
                }
                var eufetch by remember{
                    mutableStateOf(false)
                }
                val scopeHome = rememberCoroutineScope()
                scopeHome.launch {
                    listOfMatchResults = MainActivityVlrNews().getUpcomingMatch()
                    rankingsRegion = MainActivityVlrNews().getRankings()
                    EUlistOfMatchResults = MainActivityVlrNews().EUgetRankings()
                    fetchedMatchResults = true
                    fetchedRanking = true
                    eufetch = true
                }
                Scaffold(modifier = Modifier.fillMaxSize(), backgroundColor = Color(0xFF383838)) {

                    Column(modifier = Modifier.fillMaxSize()) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.23f)
                                .clip(RoundedCornerShape(bottomStart = 80f, bottomEnd = 80f))
                                .background(Color.Black),
                        ) {
                            for (i in listOfMatchResults.data.segments) {
                                if (i.tournament_icon !in distinctList && i.tournament_icon != "") {
                                    distinctList.add(i.tournament_icon)
                                }
                            }
                            if (fetchedMatchResults) {
                                Column(modifier = Modifier.fillMaxSize()) {
                                    Text(
                                        modifier = Modifier.padding(12.dp),
                                        text = "On going Tournaments",
                                        color = Color(0xFF00BCD4),
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 21.sp
                                    )
                                    //Spacer(modifier = Modifier.height(12.dp))
                                    LazyRow(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(2.dp),
                                        horizontalArrangement = Arrangement.SpaceAround
                                    ) {
                                        items(items = distinctList) {
                                            Column(
                                                Modifier
                                                    .fillMaxHeight()
                                                    .fillMaxWidth(),
                                                horizontalAlignment = Alignment.Start
                                            ) {
                                                AsyncImage(
                                                    modifier = Modifier
                                                        .height(100.dp)
                                                        .width(100.dp)
                                                        .padding(20.dp)
                                                        .clickable {
//                                                            Toast
//                                                                .makeText(
//                                                                    activity as Context,
//                                                                    it + " pressed",
//                                                                    Toast.LENGTH_SHORT
//                                                                )
//                                                                .show()
                                                            findNavController().navigate(
                                                                R.id.action_homeFragment_to_newsFragment)
                                                        },
                                                    model = it,
                                                    contentDescription = "trnicon"
                                                )
//                                        Text(
//                                            text = it,
//                                            color = Color.Red,
//                                            textAlign = TextAlign.Center,
//                                            maxLines = 2
//                                        )
                                            }

                                        }
                                    }

                                }
                            } else {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Black),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator(color = Color(0xFFFF0000))

                                }
                            }

                        }
                        //Spacer(modifier = Modifier.height(12.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp)
                                .clip(RoundedCornerShape(30f))
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black)
                            )
                            {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp)
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color(0xFFCECECE))
                                            .fillMaxHeight(0.5f)
                                            .clip(RoundedCornerShape(30f))
                                    ) {
                                        if (fetchedRanking) {
                                            Column(modifier = Modifier.fillMaxSize()) {
                                                Text(
                                                    modifier = Modifier.padding(12.dp),
                                                    text = "NA Rankings",
                                                    color = Color(0xFFD51E1E),
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 31.sp,
                                                )
                                                //Spacer(modifier = Modifier.height(12.dp))
                                                LazyRow(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(2.dp),
                                                    horizontalArrangement = Arrangement.SpaceAround
                                                ) {
                                                    items(items = rankingsRegion.data) {
                                                        Column(
                                                            Modifier
                                                                .fillMaxHeight()
                                                                .fillMaxWidth(),
                                                            horizontalAlignment = Alignment.Start
                                                        ) {
                                                            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                                                                AsyncImage(
                                                                    modifier = Modifier
                                                                        .height(140.dp)
                                                                        .width(150.dp)
                                                                        .padding(12.dp)
                                                                        .clickable {
//                                                                            Toast
//                                                                                .makeText(
//                                                                                    activity as Context,
//                                                                                    it.team + " pressed",
//                                                                                    Toast.LENGTH_SHORT
//                                                                                )
//                                                                                .show()
                                                                            findNavController().navigate(
                                                                                R.id.action_homeFragment_to_matchResultsFragment)
                                                                        },
                                                                    model = "https:"+it.logo,
                                                                    contentDescription = "trnicon"
                                                                )
                                                                Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                                                    Text(
                                                                        text = it.team,
                                                                        color = Color.Black,
                                                                        fontWeight = FontWeight.Bold,
                                                                        textAlign = TextAlign.End

                                                                    )
                                                                }
                                                            }
                                        Text(
                                            text = it.team,
                                            color = Color.Red,
                                            textAlign = TextAlign.Center,
                                            maxLines = 2
                                        )
                                                        }

                                                    }
                                                }

                                            }
                                        } else {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .background(Color(0xFFCECECE)),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                CircularProgressIndicator(color = Color(0xFFFF0000))

                                            }
                                        }

                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color(0xFFCECECE))
                                            .fillMaxHeight()
                                            .clip(RoundedCornerShape(30f))
                                    ) {
                                        if (eufetch) {
                                            Column(modifier = Modifier.fillMaxSize()) {
                                                Text(
                                                    modifier = Modifier.padding(12.dp),
                                                    text = "EU Rankings",
                                                    color = Color(0xFFD51E1E),
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 31.sp,
                                                )
                                                //Spacer(modifier = Modifier.height(12.dp))
                                                LazyRow(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(2.dp),
                                                    horizontalArrangement = Arrangement.SpaceAround
                                                ) {
                                                    items(items = EUlistOfMatchResults.data) {
                                                        Column(
                                                            Modifier
                                                                .fillMaxHeight()
                                                                .fillMaxWidth(),
                                                            horizontalAlignment = Alignment.Start
                                                        ) {
                                                            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                                                                AsyncImage(
                                                                    modifier = Modifier
                                                                        .height(140.dp)
                                                                        .width(150.dp)
                                                                        .padding(12.dp)
                                                                        .clickable {
//                                                                            Toast
//                                                                                .makeText(
//                                                                                    activity as Context,
//                                                                                    it.team + " pressed",
//                                                                                    Toast.LENGTH_SHORT
//                                                                                )
//                                                                                .show()
                                                                                   findNavController().navigate(
                                                                                       R.id.action_homeFragment_to_matchResultsFragment)
                                                                        },
                                                                    model = "https:"+it.logo,
                                                                    contentDescription = "trnicon"
                                                                )
                                                                Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                                                    Text(
                                                                        text = it.team,
                                                                        color = Color.Black,
                                                                        fontWeight = FontWeight.Bold,
                                                                        textAlign = TextAlign.End

                                                                    )
                                                                }
                                                            }
                                                            Text(
                                                                text = it.team,
                                                                color = Color.Red,
                                                                textAlign = TextAlign.Center,
                                                                maxLines = 2
                                                            )
                                                        }

                                                    }
                                                }

                                            }
                                        } else {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .background(Color(0xFFCECECE)),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                CircularProgressIndicator(color = Color(0xFFFF0000))

                                            }
                                        }

                                    }

                                }
                            }
                        }

                    }

                }
            }
        }
    }


}


//Button(onClick = {
//                            findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
//                        }) {
//                            Text("Show News")
//                        }
//                        Spacer(modifier = Modifier.height(20.dp))
//
//                        Button(onClick = {
//                            findNavController().navigate(R.id.action_homeFragment_to_matchResultsFragment)
//                        }) {
//                            Text("Show Match Results")
//                        }
