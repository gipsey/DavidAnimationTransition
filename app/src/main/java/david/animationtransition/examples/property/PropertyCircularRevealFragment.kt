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
        val centerX = view.width / 2.0
        val centerY = view.height / 2.0
        val r = hypot(centerX, centerY)

        with(
            ViewAnimationUtils
                .createCircularReveal(view, centerX.toInt(), centerY.toInt(), 0f, r.toFloat())
        ) {
            duration = 2000
            start()
        }
    }

    private fun hide(subject: View) {
        val centerX = subject.width / 2.0
        val centerY = subject.height / 2.0

        val r = hypot(centerX, centerY)

        val animator = ViewAnimationUtils.createCircularReveal(subject, centerX.toInt(), centerY.toInt(), r.toFloat(), 0f)
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                subject.visibility = View.INVISIBLE
            }
        })
        animator.duration = 2000
        animator.start()
    }
}
