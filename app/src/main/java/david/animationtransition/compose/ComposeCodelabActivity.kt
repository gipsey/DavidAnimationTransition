package david.animationtransition.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import david.animationtransition.compose.ui.theme.DavidAnimationTransitionTheme

class ComposeCodelabActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Main() }
    }
}

@Preview(showBackground = true)
@Composable
fun Main() =
    DavidAnimationTransitionTheme {
        Surface(color = MaterialTheme.colors.background) {
            Greeting("Android")
        }
    }

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}