package david.animationtransition.examples.property

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import androidx.fragment.app.Fragment
import david.animationtransition.R

class PropertyTutorialObjectAnimatorFragment : Fragment() {

    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    val propertyName = "translationY"
    val startValue = 0f
    val endValue = 500f
    val animDuration = 4000L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_property_tutorial, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textView1 = view.findViewById(R.id.text_view1)
        textView1.text = "property\n\t$propertyName\nstartValue\n\t$startValue\nendValue\n\t$endValue\nduration\n\t${animDuration} ms"
        textView2 = view.findViewById(R.id.text_view2)

        view.findViewById<View>(R.id.view).setOnClickListener { animateMove(it) }
    }

    @SuppressLint("SetTextI18n")
    private fun animateMove(view: View) {
        val propertyName = "translationY"
        val startValue = 0f
        val endValue = 500f
        val animDuration = 4000L

        with(ObjectAnimator.ofFloat(view, propertyName, startValue, endValue)) {
            addUpdateListener {
                textView2.text = "fraction\n\t${it.animatedFraction}\nvalue\n\t${it.animatedValue}"
            }
            duration = animDuration
            interpolator = AccelerateInterpolator()
            start()
        }
    }
}
