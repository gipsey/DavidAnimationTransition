package david.animationtransition.examples.drawable

import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import david.animationtransition.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AVDWelcomeFragment : Fragment() {

    private lateinit var imageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_avd_center, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageView = view.findViewById(R.id.animated_image)
        imageView.setImageResource(R.drawable.avd_welcome)
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            delay(1000)
            animate()
        }
    }

    private fun animate() {
        (imageView.drawable as Animatable2).apply {
            registerAnimationCallback(object : Animatable2.AnimationCallback() {
                override fun onAnimationEnd(drawable: Drawable?) = start()
            })

            start()
        }
    }
}
