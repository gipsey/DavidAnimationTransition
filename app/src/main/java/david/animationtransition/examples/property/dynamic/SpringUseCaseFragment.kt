package david.animationtransition.examples.property.dynamic

import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.fragment.app.Fragment
import david.animationtransition.R
import timber.log.Timber

class SpringUseCaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_spring_use_case, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.post {
            val redView = view.findViewById<View>(R.id.red)
            val blueView = view.findViewById<View>(R.id.blue)
            val holeView = view.findViewById<View>(R.id.hole)

            setUpMoving(redView)
            setUpMoving(blueView)
        }
    }

    private fun setUpMoving(ballView: View) {
        val globalPosition =
            with(Rect()) {
                ballView.getGlobalVisibleRect(this)
                RectF(this)
            }

        val globalX = globalPosition.left
        val globalY = globalPosition.top
        Timber.d("down - global $globalX $globalY")
        var dx = 0f
        var dy = 0f

        ballView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    dx = event.rawX - globalX
                    dy = event.rawY - globalY
                    Timber.d("down - d $dx $dy")
                }
                MotionEvent.ACTION_MOVE -> {
                    val translationX = event.rawX - globalX - dx
                    val translationY = event.rawY - globalY - dy
                    Timber.d("move - translation $translationX $translationY")

                    v.translationX = translationX
                    v.translationY = translationY
                }
                MotionEvent.ACTION_UP -> {
                    animate(v, ballView.left.toFloat(), ballView.top.toFloat())
                }
            }

            true
        }
    }

    private fun animate(view: View, x: Float, y: Float) {
        Timber.d("animate - $x $y")
        SpringAnimation(view, DynamicAnimation.X, x).start()
        SpringAnimation(view, DynamicAnimation.Y, y).start()
    }
}
