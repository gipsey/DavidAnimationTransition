package david.animationtransition.anim.drawable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.animatedVectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import david.animationtransition.R

@OptIn(ExperimentalComposeUiApi::class)
class AVDHandwritingComposeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        ComposeView(requireContext())
            .apply {
                setContent { Main() }
            }

    @Preview
    @Composable
    private fun Main() {
        val animate = mutableStateOf(false)

        Surface(
            Modifier.clickable { animate.value = !animate.value }
        ) {
            AnimatedVector(animate.value)
        }
    }

    @Composable
    private fun AnimatedVector(animate: Boolean) {
        Image(
            painter = animatedVectorResource(R.drawable.avd_handwriting_android).painterFor(animate),
            contentDescription = null,
            contentScale = ContentScale.None,
        )
    }
}