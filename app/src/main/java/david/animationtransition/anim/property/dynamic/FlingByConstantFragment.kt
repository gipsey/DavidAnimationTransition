package david.animationtransition.anim.property.dynamic

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.fragment.app.Fragment
import david.animationtransition.R

class FlingByConstantFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_fling, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        view.setOnClickListener {
            withFlingAnimation(view.findViewById(R.id.subject_view))
            withObjectAnimator(view.findViewById(R.id.subject_view_2))
        }

    fun withFlingAnimation(view: View) {
        val end = (view.parent as View).height.toFloat() - view.height

        FlingAnimation(view, DynamicAnimation.TRANSLATION_Y)
            .setStartVelocity(3000f)
            .setFriction(.5f)
            .setMinValue(view.translationY)
            .setMaxValue(end)
            .start()
    }

    fun withObjectAnimator(view: View) {
        val end = (view.parent as View).height.toFloat() - view.height

        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, end).apply {
            interpolator = DecelerateInterpolator()
            duration = 2_000
            start()
        }
    }
}
