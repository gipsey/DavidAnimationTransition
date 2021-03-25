package david.animationtransition.anim.property

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import david.animationtransition.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PropertyCrossFadeFragment : Fragment() {

    private lateinit var contentView: View
    private lateinit var loadingView: View
    private val duration = 2000L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_crossfade, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        contentView = view.findViewById(R.id.content)
        loadingView = view.findViewById(R.id.loading_spinner)
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            delay(1000)
            animate()
        }
    }

    private fun animate() {
        contentView.alpha = 0f
        contentView.visibility = View.VISIBLE

        contentView
            .animate()
            .alpha(1f)
            .setDuration(duration)
            .start()

        loadingView
            .animate()
            .alpha(0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    loadingView.visibility = View.GONE
                }
            })
            .setDuration(duration)
            .start()
    }
}
