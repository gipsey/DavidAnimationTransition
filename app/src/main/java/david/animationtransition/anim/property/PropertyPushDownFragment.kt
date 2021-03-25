package david.animationtransition.anim.property

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import david.animationtransition.R

class PropertyPushDownFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_push_down, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.subject_view).setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> down(v)
                MotionEvent.ACTION_UP -> up(v)
            }

            true
        }
    }

    fun down(view: View) =
        view.animate().scaleX(0.9f).scaleY(0.95f)
            .setDuration(50).setInterpolator(AccelerateDecelerateInterpolator())
            .start()

    fun up(view: View) =
        view.animate().scaleX(1f).scaleY(1f)
            .setDuration(125).setInterpolator(AccelerateDecelerateInterpolator())
            .start()
}
