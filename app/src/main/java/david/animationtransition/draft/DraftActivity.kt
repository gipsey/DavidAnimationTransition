package david.animationtransition.draft

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import david.animationtransition.compose.ui.theme.DavidAnimationTransitionTheme

class DraftActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DavidAnimationTransitionTheme {
            }
        }
    }
}
