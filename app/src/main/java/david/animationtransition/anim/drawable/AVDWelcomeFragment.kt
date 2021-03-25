package david.animationtransition.anim.drawable

import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.animatedVectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import david.animationtransition.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AVDWelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        ComposeView(requireContext())
            .apply {
                setContent {
                    Main()
                }
            }

    @Preview
    @Composable
    fun Main() {
//        Image(
//            painter = animatedVectorResource(R.drawable.avd_welcome).painterFor(false),
//            contentDescription = null,
//            contentScale = ContentScale.None,
//        )
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            delay(10000)

            (requireView().findViewById<ImageView>(R.id.animated_image).drawable as Animatable2).apply {
                registerAnimationCallback(object : Animatable2.AnimationCallback() {
                    override fun onAnimationEnd(drawable: Drawable?) = start()
                })

                start()
            }
        }
    }
}