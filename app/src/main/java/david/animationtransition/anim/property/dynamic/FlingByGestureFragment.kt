package david.animationtransition.anim.property.dynamic

import android.os.Bundle
import android.view.*
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.fragment.app.Fragment
import david.animationtransition.R
import timber.log.Timber

class FlingByGestureFragment : Fragment() {

    private var maxTranslationY = 0f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_fling_by_gesture, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val subjectView = view.findViewById<View>(R.id.subject_view)

        view.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                maxTranslationY = view.height.toFloat() - subjectView.height
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        val gestureDetector = GestureDetector(view.context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                Timber.d("onFling - ${e1?.action} - ${e2?.action} - $velocityX - $velocityY")

                FlingAnimation(subjectView, DynamicAnimation.TRANSLATION_Y)
                    .setStartVelocity(velocityY)
                    .setMinValue(0f)
                    .setMaxValue(maxTranslationY)
//                    .setMinimumVisibleChange(100f)
                    .start()

                return true
            }
        })

        view.setOnTouchListener { _: View, motionEvent: MotionEvent ->
            gestureDetector.onTouchEvent(motionEvent)
        }
    }
}
