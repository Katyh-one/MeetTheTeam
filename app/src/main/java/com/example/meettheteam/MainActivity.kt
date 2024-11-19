package com.example.meettheteam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.meettheteam.model.TeamMemberRepository.teamMembers
import com.example.meettheteam.ui.theme.MeetTheTeamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeetTheTeamTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    MeetTheTeamApp()
                }
                }
            }
        }
    }


@Composable
fun MeetTheTeamApp() {
//    scaffold supports contentWindowInsets param - where app can intersect with system ui
    Scaffold(
        topBar = {
            MeetTheTeamTopAppBar()

        }
    ){ it ->
        LazyColumn(contentPadding = it) {
            item {
                AboutTheTeam(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small))
                )
            }
            items(teamMembers) {
                TeamMemberItem(
                    teamMember = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MeetTheTeamTheme {
        MeetTheTeamApp()
    }
}