package com.example.meettheteam.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.meettheteam.R

object TeamMemberRepository {
    val teamMembers = listOf(
        TeamMember(
            imageRes = R.drawable.katy,
            linkedInImage = R.drawable.katylinkedin,
            nameRes = R.string.member1,
            emailRes = R.string.email1,
            jobRes = R.string.job1
        ),
        TeamMember(
            imageRes = R.drawable.jen,
            linkedInImage = R.drawable.jenlinkedin,
            nameRes = R.string.member2,
            emailRes = R.string.email2,
            jobRes = R.string.job1
        ),
        TeamMember(
            imageRes = R.drawable.rana,
            linkedInImage = null,
            nameRes = R.string.member3,
            emailRes = R.string.email3,
            jobRes = R.string.job1
        ),
        TeamMember(
            imageRes = R.drawable.tanya,
            linkedInImage = R.drawable.tanyalinkedin,
            nameRes = R.string.member4,
            emailRes = R.string.email4,
            jobRes = R.string.job1
        ),
        TeamMember(
            imageRes = R.drawable.ben,
            linkedInImage = null,
            nameRes = R.string.member5,
            emailRes = R.string.email5,
            jobRes = R.string.job2
        ),


    )
}