package david.animationtransition.anim.property

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import david.animationtransition.R
import kotlin.math.sin

class PropertyPasswordFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_password, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.password_layout_1).setOnClickListener {
            animate(it)
        }

        view.findViewById<View>(R.id.password_layout_2).setOnClickListener {
            animate2(it)
        }
    }

    private fun animate(view: View) =
        with(ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 80f)) {
            repeatCount = 3
            repeatMode = ValueAnimator.REVERSE
            duration = 66
            start()
        }

    private fun animate2(view: View) {
        val shakeMaxWidth = 40
        val shakeTimes = 4

        with(ValueAnimator.ofFloat(0f, 1f)) {
            duration = 400
            val initialPositionX = view.x

            addUpdateListener {
                val progress = it.animatedValue as Float
                view.x = initialPositionX + sin(shakeTimes * Math.PI * progress).toFloat() * shakeMaxWidth / 2
            }

            start()
        }
    }
}
