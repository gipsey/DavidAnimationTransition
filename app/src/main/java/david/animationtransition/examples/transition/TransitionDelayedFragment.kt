package david.animationtransition.examples.transition

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager
import david.animationtransition.R

class TransitionDelayedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_transition_junior, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sceneRoot = view.findViewById<ViewGroup>(R.id.scene_root)

        val view1 = view.findViewById<View>(R.id.view1)
        val view2 = view.findViewById<View>(R.id.view2)
        val view_a_3 = view.findViewById<View>(R.id.view_a_3)

        val junior1Transition = TransitionInflater.from(view.context).inflateTransition(R.transition.junior_1)
        var isDestinationState = false

        view.setOnClickListener {
            TransitionManager.beginDelayedTransition(sceneRoot, junior1Transition)

            val layoutParamsView1 = view1.layoutParams as FrameLayout.LayoutParams
            val layoutParamsView2 = view2.layoutParams as FrameLayout.LayoutParams

            isDestinationState =
                if (isDestinationState) {
                    layoutParamsView1.gravity = Gravity.START or Gravity.TOP
                    layoutParamsView2.gravity = Gravity.END or Gravity.TOP
                    view_a_3.isGone = false
                    false
                } else {
                    layoutParamsView1.gravity = Gravity.END or Gravity.BOTTOM
                    layoutParamsView2.gravity = Gravity.START or Gravity.BOTTOM
                    view_a_3.isGone = true
                    true
                }

            view1.layoutParams = layoutParamsView1
            view2.layoutParams = layoutParamsView2
        }
    }

}
