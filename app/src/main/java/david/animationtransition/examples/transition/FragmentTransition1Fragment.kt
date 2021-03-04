package david.animationtransition.examples.transition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import david.animationtransition.R

class FragmentTransition1Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_fragment_transition, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentWithAnim1())
                .commit()

        view.setOnClickListener { performTransition() }
    }

    private fun performTransition() {
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, FragmentWithAnim2())
            addToBackStack(null)
            commit()
        }
    }
}