package david.animationtransition.anim.property

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import david.animationtransition.R

class PropertyViewPropertyAnimatorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_red_ball, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.setOnClickListener {
            animate(view.findViewById(R.id.subject_view))
        }
    }

    private fun animate(view: View) {
        view.animate()
            .translationX(500f)
            .translationY(500f)
            .setDuration(2000)
            .start()
    }
}
