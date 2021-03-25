package david.animationtransition.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import david.animationtransition.R
import david.animationtransition.compose.ui.theme.DavidAnimationTransitionTheme

class ComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DavidAnimationTransitionTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Preview
@Composable
fun Greeting() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.sample1),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(16.dp))

        Text(
            "A day wandering through the sandhills " +
                    "in Shark Fin Cove, and a few of the " +
                    "sights I saw",
            style = MaterialTheme.typography.h4,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            "Davenport, California",
            style = MaterialTheme.typography.body1
        )
        Text(
            "December 2018",
            style = MaterialTheme.typography.body1
        )
    }
}