package david.animationtransition.examples.transition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.*
import david.animationtransition.R
import timber.log.Timber

class TransitionJuniorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_transition_junior, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sceneRoot = view.findViewById<ViewGroup>(R.id.scene_root)
        val currentScene = Scene.getCurrentScene(sceneRoot)

        val sceneDefault = Scene.getSceneForLayout(sceneRoot, R.layout.junior_scene_a, view.context)
        val sceneB = Scene.getSceneForLayout(sceneRoot, R.layout.junior_scene_b, view.context)

        val junior1Transition = TransitionInflater.from(view.context).inflateTransition(R.transition.junior_1)
        junior1Transition.addListener(object : TransitionListenerAdapter() {
            override fun onTransitionStart(transition: Transition) = Timber.d("onTransitionStart")
            override fun onTransitionEnd(transition: Transition) = Timber.d("onTransitionEnd")
        })

        var currentSceneLayout = R.layout.junior_scene_a

        view.setOnClickListener {
            currentSceneLayout =
                if (currentSceneLayout == R.layout.junior_scene_a) {
                    TransitionManager.go(sceneB, junior1Transition)

                    R.layout.junior_scene_b
                } else {
                    TransitionManager.go(sceneDefault, junior1Transition)
                    R.layout.junior_scene_a
                }
        }
    }

}
