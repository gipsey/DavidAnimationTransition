package david.animationtransition.anim.lottie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import david.animationtransition.R
import timber.log.Timber

class LottieProgramaticallyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_lottie_programatically, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<LottieAnimationView>(R.id.lottie_view).apply {
            repeatMode = LottieDrawable.RESTART
            repeatCount = LottieDrawable.INFINITE

            val result = LottieCompositionFactory.fromRawResSync(view.context, R.raw.lottie_hands_blue)
            val successResult = result.value
            val errorResult = result.exception

            if (successResult != null) {
                setComposition(successResult)
                playAnimation()
            } else {
                Timber.w(errorResult, "anim failed loading")
            }
        }
    }
}
