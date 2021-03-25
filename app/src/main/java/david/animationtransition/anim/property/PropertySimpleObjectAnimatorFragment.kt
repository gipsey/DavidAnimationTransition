package david.animationtransition.anim.property

import android.animation.*
import android.os.Bundle
import android.util.Property
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import david.animationtransition.R

class PropertySimpleObjectAnimatorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_property_simple, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.move_view).setOnClickListener { animateMove(it) }
        view.findViewById<View>(R.id.fade_view).setOnClickListener { animateFade(it) }
        view.findViewById<View>(R.id.scale_view).setOnClickListener { animateScaleFade(it) }
        view.findViewById<View>(R.id.rotate_view).setOnClickListener { animateRotate(it) }
        view.findViewById<View>(R.id.rotate_x_view).setOnClickListener { animateRotateX(it) }
        view.findViewById<View>(R.id.color_view).setOnClickListener { animateColor(it) }
        view.findViewById<TextView>(R.id.text_view).setOnClickListener { animateText(it as TextView) }
    }

    private fun animateMove(view: View) =
        with(
            ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 500f),
                PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 500f)
            )
        ) {
            duration = 2000
            start()
        }

    private fun animateMove2(view: View) =
        AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 500f),
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 500f)
            )
            duration = 2000
            start()
        }

    private fun animateFade(view: View) =
        with(ObjectAnimator.ofFloat(view, View.ALPHA, 0f)) {
            duration = 2000
            start()
        }

    private fun animateScaleFade(view: View) =
        with(
            ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat(View.SCALE_X, 3f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 3f)
            )
        ) {
            duration = 2000
            start()
        }

    private fun animateRotate(view: View) =
        // rotation around the pivot point
        with(
            ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat(View.ROTATION, 360f)
            )
        ) {
            duration = 2000
            start()
        }

    private fun animateRotateX(view: View) =
        // rotation around the horizontal axis
        with(
            ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat(View.ROTATION_X, 180f)
            )
        ) {
            duration = 2000
            start()
        }

    private fun animateColorBad(view: View) =
        with(ObjectAnimator.ofInt(view, "backgroundColor", 0xFF0099CC.toInt(), 0xFFFF0000.toInt())) {
            duration = 2000
            start()
        }

    private fun animateColor(view: View) =
        with(ObjectAnimator.ofArgb(view, "backgroundColor", 0xFF0099CC.toInt(), 0xFFFF0000.toInt())) {
            duration = 2000
            start()
        }

    private fun animateText(textView: TextView) =
        with(
            ObjectAnimator.ofObject(
                textView,
                Property.of(TextView::class.java, CharSequence::class.java, "text"),
                object : TypeConverter<Int, CharSequence>(Int::class.java, CharSequence::class.java) {
                    override fun convert(value: Int?): CharSequence = value.toString()

                },
                TypeEvaluator<Int> { fraction, startValue, endValue ->
                    (startValue + (endValue - startValue) * fraction).toInt()
                },
                0, 100
            )
        ) {
            duration = 2000
            start()
        }
}
