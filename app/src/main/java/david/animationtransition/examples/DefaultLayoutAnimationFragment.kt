package david.animationtransition.examples

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import david.animationtransition.R

class DefaultLayoutAnimationFragment : Fragment() {

    private var clicks = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_default_layout_animation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val root = (view as ViewGroup)
        val childView1 = aView()
        val childView2 = aView(Gravity.BOTTOM, R.drawable.oval_blue)

        root.layoutTransition =
            LayoutTransition().apply {
                setDuration(2000L)
            }

        view.setOnClickListener {
            when (++clicks) {
                1 -> root.addView(childView1)
                2 -> root.addView(childView2)
                3 -> root.removeView(childView1)
                4 -> root.removeView(childView2)
                else -> clicks = 0
            }
        }
    }

    private fun aView(gravity: Int = Gravity.START, drawable: Int = R.drawable.oval_red) =
        View(requireContext()).apply {
            layoutParams = FrameLayout.LayoutParams(dpToPx(120), dpToPx(120), gravity).apply {
                topMargin = dpToPx(50)
                bottomMargin = dpToPx(50)
                marginStart = dpToPx(50)
                marginEnd = dpToPx(50)
            }

            background = resources.getDrawable(drawable, null)
        }

    /**
     * 160 dpi screen => 1dp = 1px
     * 320 dpi screen => 1dp = 2px - factor = 2
     *
     * px = factor * dp
     */
    private fun dpToPx(dp: Int) =
        (resources.displayMetrics.density * dp).toInt()
}
