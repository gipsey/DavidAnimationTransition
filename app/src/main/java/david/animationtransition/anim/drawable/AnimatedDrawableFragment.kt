package david.animationtransition.anim.drawable

import android.graphics.drawable.AnimationDrawable
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

class AnimatedDrawableFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_animated_drawable, container, false)

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            delay(1000)
            val imageView = requireView().findViewById<ImageView>(R.id.animated_image)
            (imageView.drawable as AnimationDrawable).start()
        }
    }
}
