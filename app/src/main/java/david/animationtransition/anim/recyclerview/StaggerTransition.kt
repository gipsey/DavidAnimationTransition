package david.animationtransition.anim.recyclerview

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import androidx.transition.Fade
import androidx.transition.SidePropagation
import androidx.transition.TransitionValues

class StaggerTransition : Fade(IN) {

    init {
        duration = DURATION

        interpolator = PathInterpolator(0f, 0f, 0.2f, 1f)
//        interpolator = AccelerateInterpolator()
//        interpolator = LinearInterpolator()

        propagation = SidePropagation().apply {
            setSide(Gravity.BOTTOM)
            // We want the stagger effect to take as long as the duration of a single item.
            // In other words, the last item starts to fade in around the time when the first item
            // finishes animating. The overall animation will take about twice the duration of one
            // item fading in.
            setPropagationSpeed(1f)
        }
    }

    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
        val view = endValues?.view ?: return null

        return AnimatorSet().apply {
            playTogether(
                listOf(
                    super.createAnimator(sceneRoot, startValues, endValues),
                    ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, view.measuredHeight.toFloat(), 0f)
                )
            )
        }
    }

    companion object {
        private const val DURATION = 400L
    }
}