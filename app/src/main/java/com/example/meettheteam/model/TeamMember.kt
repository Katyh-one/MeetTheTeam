package com.example.meettheteam.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TeamMember(
    @DrawableRes val imageRes: Int,
    @DrawableRes val linkedInImage: Int?,
    @StringRes val nameRes: Int,
    @StringRes val emailRes: Int,
    @StringRes val jobRes: Int
)
