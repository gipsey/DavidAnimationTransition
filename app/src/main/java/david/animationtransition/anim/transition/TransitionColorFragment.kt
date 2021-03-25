package david.animationtransition.anim.transition

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.*
import david.animationtransition.R
import timber.log.Timber

class TransitionColorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_transition_color, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sceneRoot = view.findViewById<ViewGroup>(R.id.scene_root)
        val sceneA = Scene.getSceneForLayout(sceneRoot, R.layout.transition_color_scene_a, view.context)
        val sceneB = Scene.getSceneForLayout(sceneRoot, R.layout.transition_color_scene_b, view.context)

        val transitionSet = TransitionSet()
            .addTransition(ChangeColorTransition())
            .addTransition(ChangeBounds())
            .addTransition(Fade(Fade.IN))
            .addTransition(Fade(Fade.OUT))
            .setDuration(4000)
            .setOrdering(TransitionSet.ORDERING_TOGETHER)

        var isOnSceneA = true

        view.setOnClickListener {
            if (isOnSceneA)
                TransitionManager.go(sceneB, transitionSet)
            else
                TransitionManager.go(sceneA, transitionSet)

            isOnSceneA = !isOnSceneA
        }
    }

    private class ChangeColorTransition : Transition() {

        override fun captureStartValues(transitionValues: TransitionValues) = capture(transitionValues)

        override fun captureEndValues(transitionValues: TransitionValues) = capture(transitionValues)

        override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
            val startValue = startValues?.values?.get(BACKGROUND_COLOR) as? Int
            val endValue = endValues?.values?.get(BACKGROUND_COLOR) as? Int

            return if (startValue != null && endValue != null) {
                Timber.d("animator - ${startValues.view.idName()} ${endValues.view.idName()} - $startValue $endValue")
                ObjectAnimator.ofArgb(endValues.view, "backgroundColor", startValue, endValue)
            } else {
                Timber.d("no animator - ${startValues?.view?.idName()} $startValue ${endValues?.view?.idName()} $endValue")
                null
            }
        }

        private fun capture(transitionValues: TransitionValues) {
            val background = transitionValues.view.background
            Timber.d("capture - ${transitionValues.view.idName()} - background = $background")

            if (background is ColorDrawable) {
                transitionValues.values[BACKGROUND_COLOR] = background.color
            }
        }

        private fun View.idName() = resources.getResourceEntryName(id)

        companion object {
            private const val BACKGROUND_COLOR = "ChangeColorTransition.backgroundColor"
        }
    }

}
