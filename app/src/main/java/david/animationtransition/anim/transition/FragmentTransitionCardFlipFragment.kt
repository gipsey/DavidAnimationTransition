package david.animationtransition.anim.transition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import david.animationtransition.R

class FragmentTransitionCardFlipFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_card_flip, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.setOnClickListener {
            flip()
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FrontSideFragment())
            .commit()
    }

    private fun flip() {
        if (childFragmentManager.backStackEntryCount != 0) {
            childFragmentManager.popBackStack()
        } else {
            childFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.animator.card_flip_right_in,
                    R.animator.card_flip_right_out,
                    R.animator.card_flip_left_in,
                    R.animator.card_flip_left_out
                )
                .replace(R.id.fragment_container, BackSideFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    class FrontSideFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_card_flip_front_side, container, false)
    }

    class BackSideFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_card_flip_back_side, container, false)
    }
}
