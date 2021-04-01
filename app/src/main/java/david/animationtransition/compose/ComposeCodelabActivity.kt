package david.animationtransition.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import david.animationtransition.compose.ui.theme.DavidAnimationTransitionTheme
import timber.log.Timber

class ComposeCodelabActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Main() }
    }
}

@Preview
@Composable
fun Main() {
    val clicks = remember { mutableStateOf(0) }

    DavidAnimationTransitionTheme {
        Surface(
            color = MaterialTheme.colors.primary,
            elevation = 12.dp
        ) {
            Column {
                Greeting("Android")
                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .height(10.dp)
                        .padding(4.dp)
                )

                Timber.d("recomposition - column")
                Counter(clicks.value) { value -> clicks.value = value }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Counter(clicks: Int, clickCallback: (Int) -> Unit) {
    Button(
        onClick = { clickCallback(clicks + 1) }
    ) {
        Timber.d("recomposition - button ?")
        Text("Clicks count is $clicks")
    }
}