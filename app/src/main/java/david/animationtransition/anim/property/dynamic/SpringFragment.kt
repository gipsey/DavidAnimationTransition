package david.animationtransition.anim.property.dynamic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.fragment.app.Fragment
import david.animationtransition.R
import timber.log.Timber

class SpringFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_spring, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        view.setOnClickListener {
            animate(view.findViewById(R.id.spring_view), view.findViewById(R.id.subject_view))
        }

    private fun animate(springView: View, massView: View) {
        val endValue = (massView.parent as View).height / 2f - massView.height / 2f
        Timber.d("center=$endValue")

        SpringAnimation(massView, DynamicAnimation.TRANSLATION_Y, endValue).apply {
            addUpdateListener { _, value, velocity ->
                Timber.d("update - value = $value velocity = $velocity")

                val layoutParams = FrameLayout.LayoutParams(springView.layoutParams as FrameLayout.LayoutParams)
                layoutParams.height = value.toInt()
                springView.layoutParams = layoutParams
            }
            addEndListener { _, canceled, value, velocity ->
                Timber.d("end - canceled = $canceled value = $value velocity = $velocity")
            }
            setStartValue(0f)
            setStartVelocity(10f)
//            setMinValue(0f)
//            setMaxValue(center)
            // if damping force is low then the bouncy is high
            spring.dampingRatio = .1f // springForce.DAMPING_RATIO_HIGH_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
            start()
//        animateToFinalPosition(center)
        }

    }
}
