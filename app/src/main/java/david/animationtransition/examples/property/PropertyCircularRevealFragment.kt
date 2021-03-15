package david.animationtransition.examples.property

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import david.animationtransition.R
import kotlin.math.hypot

class PropertyCircularRevealFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_circular_reveal, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.setOnClickListener {
            animate(view.findViewById(R.id.image_view))
        }
    }

    private fun animate(subject: View) =
        if (subject.visibility == View.INVISIBLE)
            reveal(subject)
        else
            hide(subject)

    private fun reveal(view: View) {
        val centerX = view.width / 2f
        val centerY = view.height / 2f
        val startRadius = 0f
        val endRadius = hypot(centerX, centerY)

        ViewAnimationUtils.createCircularReveal(
            view,
            centerX.toInt(),
            centerY.toInt(),
            startRadius,
            endRadius
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    view.visibility = View.VISIBLE
                }
            })

            duration = 2000

            start()
        }
    }

    private fun hide(view: View) {
        val centerX = view.width / 2f
        val centerY = view.height / 2f
        val startRadius = hypot(centerX, centerY)
        val endRadius = 0f

        ViewAnimationUtils.createCircularReveal(
            view,
            centerX.toInt(),
            centerY.toInt(),
            startRadius,
            endRadius
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    view.visibility = View.INVISIBLE
                }
            })

            duration = 2000

            start()
        }
    }
}
