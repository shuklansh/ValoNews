package com.shuklansh.valonews.ApiRepo

import com.shuklansh.valonews.Model.MatchResultsModel.MatchResultsModel
import com.shuklansh.valonews.Model.NewsModel.NewsModel
import com.shuklansh.valonews.Model.RankingRegionModel.RankkingModel
import com.shuklansh.valonews.Model.UpcomingMatchModel.UpcomingMatchModel
import com.shuklansh.valonews.Model.VlrStatsModel.VlrStatsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface VlrApi {

    @GET("/news")
    suspend fun getNews(): NewsModel

    @GET("match/results")
    suspend fun getMatchResults(): MatchResultsModel

    @GET("/stats")
    suspend fun getVlrStats(
        @Query("region") region: String,
        @Query("timespan") timespan: Int
    ): VlrStatsModel

    @GET("/rankings/na")
    suspend fun getRankingsRegion(
        //@Query("region") region: String = "na"
    ): RankkingModel

    @GET("/rankings/eu")
    suspend fun EUgetRankingsRegion(
        //@Query("region") region: String = "na"
    ): RankkingModel

    @GET("/match/upcoming")
    suspend fun getUpcomingMatch(): UpcomingMatchModel


}
