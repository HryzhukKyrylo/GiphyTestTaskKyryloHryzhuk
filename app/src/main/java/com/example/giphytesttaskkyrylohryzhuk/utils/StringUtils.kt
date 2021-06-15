package com.example.giphytesttaskkyrylohryzhuk.utils

import android.app.Application
import com.example.giphytesttaskkyrylohryzhuk.R

class StringUtils(val appContext: Application) {
    fun noInternetConnection() = appContext.getString(R.string.no_internet)
    fun somethingWentWrong() = appContext.getString(R.string.something_went_wrong)
    fun retry() = appContext.getString(R.string.retry)
    fun clickAgain() = appContext.getString(R.string.click_again)
}