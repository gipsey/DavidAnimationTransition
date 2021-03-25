package david.animationtransition.anim.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import david.animationtransition.R
import kotlin.math.sin

class CanvasLoadingViewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_canvas_loading, container, false)
}

class LoadingView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val computer: LoadingComputations
    private val dotPaint: Paint

    init {
        computer = LoadingComputations(resources.displayMetrics.density)
        dotPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        dotPaint.color = -0x4f4f50
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(computer.dpToPx(300).toInt(), computer.dpToPx(300).toInt())
    }

    override fun onDraw(canvas: Canvas) {
        val time = System.currentTimeMillis()
        val radius = computer.dpToPx(3)
        val yOffset = 50

        canvas.drawCircle(
            computer.dpToPx(yOffset) - computer.dpToPx(12),
            computer.dpToPx(yOffset) - computer.dpToPx(20) * computer.verticalPosition(time, 0),
            radius, dotPaint
        )
        canvas.drawCircle(
            computer.dpToPx(yOffset),
            computer.dpToPx(yOffset) - computer.dpToPx(20) * computer.verticalPosition(time, 125),
            radius, dotPaint
        )
        canvas.drawCircle(
            computer.dpToPx(yOffset) + computer.dpToPx(12),
            computer.dpToPx(yOffset) - computer.dpToPx(20) * computer.verticalPosition(time, 250),
            radius, dotPaint
        )

        invalidate()
    }

    private class LoadingComputations(private val density: Float) {

        fun dpToPx(size: Int) = size * density

        fun verticalPosition(time: Long, offset: Long): Float {
            val time = (time + offset) % (ANIMATION_LENGTH + ANIMATION_PAUSE)

            if (time >= ANIMATION_LENGTH) return 0.0f

            val x = 2 * Math.PI * time / ANIMATION_LENGTH
            return ((sin(x - Math.PI / 2) + 1) / 2.0f).toFloat()
        }

        companion object {
            private const val ANIMATION_LENGTH: Long = 1000
            private const val ANIMATION_PAUSE: Long = 500
        }
    }
}
