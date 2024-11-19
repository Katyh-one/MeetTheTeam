package com.example.meettheteam

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.meettheteam.model.TeamMember


/**
 * Composable that displays a list item containing a team member icon and their information.
 *
 * @param team member contains the data that populates the list item
 * @param modifier modifiers to set to this composable
 */
@Composable
fun TeamMemberItem(
    teamMember: TeamMember,
    modifier: Modifier = Modifier
) {
//    observes change to the state of expanded - initial value set to false
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer)

    Card (modifier = modifier){
        Column (
            modifier = Modifier
//                animating list item height in the app
                .animateContentSize(
                    animationSpec = spring(
//                        no bounce to the animation
                        dampingRatio = Spring.DampingRatioNoBouncy,
//                        makes the spring stiffer
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                MemberIcon(teamMember.imageRes)
                MemberInformation(teamMember.nameRes, teamMember.jobRes)
//            using the weight of 1f it fills the space in the row to allow
//            for the button to move to the edge
                Spacer(modifier = Modifier.weight(1f))
                TeamMemberItemButton(
                    expanded = expanded,
//                true if clicked and false if not clicked
                    onClick = { expanded = !expanded }
                )
            }
//            member info only shows if the expanded is set to true
            if(expanded) {
                MemberAbout(
                        teamMember.emailRes, teamMember.linkedInImage, teamMember.nameRes, modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            top = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_medium),
                            bottom = dimensionResource(R.dimen.padding_medium)
                        )
                    )

            }
        }
    }
}


/**
 * Composable that sets the arrows depending on onclick activity
 * expanded boolean drives which arrow shows
 */
@Composable
private fun TeamMemberItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
//    has a minimum touch target size set already for accessibility needs
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
//            downward arrow displays for expandmore and upward arrow for expandless
//            changes based on state change of expanded
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary

        )
    }
}

/**
 * Composable that displays a photo of team member.
 *
 * @param memberIcon is the resource ID for the image of the team member
 * @param modifier modifiers to set to this composable
 */
@Composable
fun MemberIcon(
    @DrawableRes memberIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
//            clip "clips" the image into shape
            .clip(MaterialTheme.shapes.small),
//        image attribute that crops the image to fit
        contentScale = ContentScale.Crop,
        painter = painterResource(memberIcon),

        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.

        contentDescription = null
    )
}

/**
 * Composable that displays a members's name and job title.
 *
 * @param memberName is the resource ID for the string of the members's name
 * @param jobtitle is the resource ID for the string of the members's job title
 * @param modifier modifiers to set to this composable
 */
@Composable
fun MemberInformation(
    @StringRes memberName: Int,
    @StringRes jobTitle: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(memberName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(jobTitle),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
/**
 * Composable to create the app bar that sits at the top of the page
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeetTheTeamTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                        .padding(
                            dimensionResource(id = R.dimen.padding_small)
                        ).clip(MaterialTheme.shapes.small),
                    painter = painterResource(R.drawable.skygo),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

/**
 * information displayed in the dropdown when the arrow is clicked
 * * @param memberEmail is the resource ID for the string of the members's email address
 *  * @param linkedin is the resource ID for the image of the members's linkedin image
 *  @param memberName is the resource ID for the string of the member's name
 *  * @param modifier modifiers to set to this composable
 */
@Composable
fun MemberAbout(
    @StringRes memberEmail: Int,
    @DrawableRes linkedIn: Int?,
    @StringRes memberName: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Contact:",
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(memberEmail),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.weight(1f))
//        linkedin image is only displayed if not null
        linkedIn?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = "QR code for access to ${stringResource(memberName)} linkedIn profile"
            )
        }

    }
}


/**
 *  information about the project team
 */
@Composable
fun AboutTheTeam(
        modifier: Modifier = Modifier
    ) {
        Card {
            Column(modifier = modifier) {
                Text(
                    text = "About the team",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "We are four software engineer graduates from the September cohort. We have come via Get into Tech which we completed in April this year.\n We are currently working on our educational project. We will be creating the Android mobile Sky Go homepage. ",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
