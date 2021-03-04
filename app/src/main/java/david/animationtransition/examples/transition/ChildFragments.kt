package david.animationtransition.examples.transition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Explode
import androidx.transition.Slide
import david.animationtransition.R
import david.animationtransition.setLightStatusBarWithRequestApplyInsets
import david.animationtransition.showContentFullscreenWithDarkStatusBarIcons

class FragmentSimple1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_transition_fragment_1, container, false)
}

class FragmentSimple2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_transition_fragment_2, container, false)
}

class FragmentWithAnim1 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exitTransition =
            Explode().apply {
                duration = 1000
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_transition_fragment_1, container, false)

    override fun onResume() {
        super.onResume()
        setLightStatusBarWithRequestApplyInsets()
    }
}


class FragmentWithAnim2 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition =
            Slide().apply {
                duration = 1000
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_transition_fragment_2, container, false)

    override fun onStart() {
        super.onStart()
        showContentFullscreenWithDarkStatusBarIcons()
    }
}