package david.animationtransition.examples.lottie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import david.animationtransition.R
import timber.log.Timber

class LottieDrawableFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_lottie_drawable, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lottieDrawable = LottieDrawable().apply {
            repeatMode = LottieDrawable.RESTART
            repeatCount = LottieDrawable.INFINITE

            LottieCompositionFactory.fromUrl(view.context, "https://assets5.lottiefiles.com/packages/lf20_wv4mTG.json")
                .addFailureListener { throwable -> Timber.w(throwable, "anim failed loading") }
                .addListener { composition -> setComposition(composition) }
        }

        view.findViewById<ImageView>(R.id.image_view).setImageDrawable(lottieDrawable)

        lottieDrawable.playAnimation()
    }
}
