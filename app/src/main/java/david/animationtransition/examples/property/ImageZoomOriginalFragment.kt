package david.animationtransition.examples.property

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import david.animationtransition.R

class ImageZoomOriginalFragment : Fragment() {

    // Hold a reference to the current animator, so that it can be canceled mid-way.
    private var currentAnimator: Animator? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_image_zoom_original, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val thumb1View = view.findViewById<View>(R.id.thumb_button_1)
        val expandedImageView = view.findViewById<ImageView>(R.id.expanded_image)
        val container = view.findViewById<View>(R.id.container)

        thumb1View.setOnClickListener { zoomImageFromThumb(container, thumb1View, expandedImageView, R.drawable.image_cat) }
    }

    private fun zoomImageFromThumb(container: View, thumbView: View, expandedView: ImageView, imageResId: Int) {
        currentAnimator?.cancel()

        // Load the high-resolution "zoomed-in" image.
        expandedView.setImageResource(imageResId)

        // Calculate the starting and ending bounds for the zoomed-in image.  This step involves lots of math. Yay, math.
        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBoundsInt)
        container.getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            // Extend start bounds horizontally
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // Extend start bounds vertically
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.alpha = 0f
        expandedView.visibility = View.VISIBLE

        expandedView.x = startBounds.left
        expandedView.y = startBounds.top
        expandedView.scaleX = startScale
        expandedView.scaleY = startScale

        expandedView.pivotX = 0f
        expandedView.pivotY = 0f

        expandedView.animate()
            .x(finalBounds.left)
            .y(finalBounds.top)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(3000)
            .setInterpolator(DecelerateInterpolator())
            .start()

        minimize(expandedView, startBounds, startScale, thumbView)
    }

    private fun minimize(expandedImageView: ImageView, startBounds: RectF, startScale: Float, thumbView: View) {
        expandedImageView.setOnClickListener {
            currentAnimator?.cancel()

            currentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = 3000
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }
                })
                start()
            }
        }
    }
}
