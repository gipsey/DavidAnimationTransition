package david.animationtransition.anim.property.dynamic

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.fragment.app.Fragment
import david.animationtransition.R

class DynamicFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_dynamic, container, false)

    override fun onResume() {
        super.onResume()

        requireView().post {
            val subjectView = requireView().findViewById<View>(R.id.subject_view)
            val targetView = requireView().findViewById<View>(R.id.target_view)

            animateWithSpring(requireView(), subjectView, targetView)
        }
    }

    private fun animateWithObjectAnimator(parentView: View, subjectView: View) {
        val bottomRight = Pair(parentView.width - subjectView.width.toFloat(), parentView.height - subjectView.height.toFloat())
        var isAnimationRunning = false

        parentView.setOnClickListener {
            subjectView
                .animate()
                .translationX(bottomRight.first)
                .translationY(if (isAnimationRunning) 0f else bottomRight.second)
                .setDuration(3000)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        isAnimationRunning = true
                    }

                    override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                        isAnimationRunning = false
                    }
                })
                .start()
        }
    }

    private fun animateWithSpring(parentView: View, subjectView: View, targetView: View) {
        val animationX = SpringAnimation(subjectView, DynamicAnimation.TRANSLATION_X)
        val animationY = SpringAnimation(subjectView, DynamicAnimation.TRANSLATION_Y)
        listOf(animationX, animationY).forEach {
            it.setStartVelocity(100f)
            it.spring = SpringForce().apply {
                dampingRatio = SpringForce.DAMPING_RATIO_NO_BOUNCY
                stiffness = 1f
            }
        }

        val bottomRight = Pair(parentView.width - subjectView.width.toFloat(), parentView.height - subjectView.height.toFloat())

        parentView.setOnClickListener {
            var targetX: Float = bottomRight.first
            var targetY: Float = bottomRight.second

            val targetViewParams = targetView.layoutParams as FrameLayout.LayoutParams

            if (animationX.isRunning && animationY.isRunning) {
                if (targetViewParams.gravity == Gravity.BOTTOM or Gravity.END) {
                    targetView.layoutParams = targetViewParams.apply {
                        gravity = Gravity.TOP or Gravity.END
                    }
                    targetY = 0f
                } else {
                    targetView.layoutParams = targetViewParams.apply {
                        gravity = Gravity.BOTTOM or Gravity.END
                    }
                    targetY = bottomRight.second
                }
            }

            animationX.animateToFinalPosition(targetX)
            animationY.animateToFinalPosition(targetY)
        }
    }
}
