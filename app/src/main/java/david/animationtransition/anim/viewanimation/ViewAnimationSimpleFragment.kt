package david.animationtransition.anim.viewanimation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import david.animationtransition.R

class ViewAnimationSimpleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_red_ball, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.setOnClickListener {
            animate(view.findViewById(R.id.subject_view))
        }
    }

    private fun animate(view: View) {
        view.startAnimation(
            TranslateAnimation(0f, 500f, 0f, 500f).apply {
                duration = 2000
            }
        )
    }
}
