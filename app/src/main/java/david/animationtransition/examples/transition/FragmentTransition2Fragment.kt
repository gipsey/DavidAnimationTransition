package david.animationtransition.examples.transition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet
import david.animationtransition.R

class FragmentTransition2Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_fragment_transition, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentSimple1())
                .commit()

        view.setOnClickListener { performTransition() }
    }

    private fun performTransition() {
        val currentFragment = childFragmentManager.findFragmentById(R.id.fragment_container)!!
        val nextFragment = FragmentSimple2()

        // 1. Exit for Previous Fragment
        currentFragment.exitTransition =
            Fade().apply {
                duration = FADE_DEFAULT_TIME
            }

        // 2. Shared Elements Transition
        nextFragment.sharedElementEnterTransition =
            TransitionSet().apply {
                addTransition(TransitionInflater.from(context).inflateTransition(android.R.transition.move))
                duration = MOVE_DEFAULT_TIME
//        startDelay = FADE_DEFAULT_TIME
            }

        // 3. Enter Transition for New Fragment
        nextFragment.enterTransition =
            Fade().apply {
//                startDelay = MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME
                duration = FADE_DEFAULT_TIME
            }

        childFragmentManager.beginTransaction().apply {
            val logo = currentFragment.requireView().findViewById<View>(R.id.fragment1_logo)

            addSharedElement(logo, logo.transitionName)
            replace(R.id.fragment_container, nextFragment)
            addToBackStack(null)
            commit()
        }
    }

    companion object {
        private const val MOVE_DEFAULT_TIME: Long = 1000
        private const val FADE_DEFAULT_TIME: Long = 1000
    }
}