package com.shuklansh.valonews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.shuklansh.valonews.ApiRepo.ApiHelper
import com.shuklansh.valonews.ApiRepo.VlrApi
import com.shuklansh.valonews.Fragments.HomeFragment
import com.shuklansh.valonews.Fragments.NewsFragment
import com.shuklansh.valonews.Model.MatchResultsModel.MatchResultsModel
import com.shuklansh.valonews.Model.NewsModel.NewsModel
import com.shuklansh.valonews.Model.RankingRegionModel.RankkingModel
import com.shuklansh.valonews.Model.UpcomingMatchModel.UpcomingMatchModel

class MainActivityVlrNews : AppCompatActivity() {

    var apicaller = ApiHelper.getInstance().create(VlrApi::class.java)

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_vlr_news)
        //supportFragmentManager.beginTransaction().replace(R.id.FragmentContainerView,HomeFragment()).commit()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.FragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController


    }

    suspend fun getNews() : NewsModel{
        var newsResponse = apicaller.getNews()
        return newsResponse
    }

    suspend fun getMatchResults() : MatchResultsModel{
        var matchresults = apicaller.getMatchResults()
        return matchresults
    }

    suspend fun getUpcomingMatch() : UpcomingMatchModel{
        return apicaller.getUpcomingMatch()
    }

    suspend fun getRankings() : RankkingModel{
        return apicaller.getRankingsRegion()
    }

    suspend fun EUgetRankings() : RankkingModel{
        return apicaller.EUgetRankingsRegion()
    }


}