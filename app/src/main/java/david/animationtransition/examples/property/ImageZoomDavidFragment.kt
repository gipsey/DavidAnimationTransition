package david.animationtransition.examples.property

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import david.animationtransition.R
import timber.log.Timber

class ImageZoomDavidFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_image_zoom, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val small = view.findViewById<ImageView>(R.id.small_image)

//        view.post {
//            scaleImageByCenterCrop(small)
//        }

        small.setOnClickListener {
            animate(view, small)
        }
    }

    private fun animate(root: View, imageView: ImageView) {
        val endHeight = imageView.height.toFloat() / imageView.width.toFloat() * root.width.toFloat()
        Timber.d("endHeight=$endHeight")

        val endX = root.x
        val endY = (root.height / 2f) - (endHeight / 2f)
        val scale = root.width / imageView.width.toFloat()
//        val scale = root.height / imageView.height.toFloat()

        Timber.d("endX=$endX endY=$endY scale=$scale")

        imageView.pivotX = 0f
        imageView.pivotY = 0f

        ObjectAnimator.ofPropertyValuesHolder(
            imageView,
            PropertyValuesHolder.ofFloat(View.X, endX),
            PropertyValuesHolder.ofFloat(View.Y, endY),
            PropertyValuesHolder.ofFloat(View.SCALE_X, scale),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, scale)
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    imageView.scaleType = ImageView.ScaleType.FIT_CENTER
                }
            })
            duration = 3000
            start()
        }
    }

    /**
     * NOT WORKING
     * Scales the image inside it's View without modifying the bounds of the view.
     * This could be possible by:
     * 1. scaling the View based on View-drawable ratio
     * 2. setting View's bounds to the original (???)
     */
    private fun scaleImageByCenterCrop(imageView: ImageView) {
//        val imageViewBounds = Rect().apply {
//            imageView.getGlobalVisibleRect(this)
//        }
//        imageView.x = imageViewBounds.left.toFloat()
//        imageView.y = imageViewBounds.top.toFloat()
//        imageView.layoutParams = LinearLayout.LayoutParams(imageViewBounds.width(), imageViewBounds.height())
//        imageView.invalidate()

        val viewWidth = imageView.width.toFloat()
        val viewHeight = imageView.height.toFloat()

        val image = (imageView.drawable as BitmapDrawable).bitmap
        val imageWidth = image.width.toFloat()
        val imageHeight = image.height.toFloat()

        val viewRatio = viewWidth / viewHeight
        val imageRatio = imageWidth / imageHeight

        Timber.d("view = ${viewWidth}x${viewHeight} image = ${imageWidth}x${imageHeight}")
        Timber.d("viewRatio = $viewRatio imageRatio = $imageRatio")

        var imageScale: Float

        if (viewRatio > imageRatio) {// a view szelesebb mint az image
            Timber.d("1")
            imageScale = imageWidth / viewWidth
        } else {
            Timber.d("2")
            imageScale = imageHeight / viewHeight
        }

        imageView.scaleX = imageScale
        imageView.scaleY = imageScale

        Timber.d("$imageScale")
    }
}
