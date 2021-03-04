package david.animationtransition.examples.drawable

import android.animation.ObjectAnimator
import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import david.animationtransition.R

class AVDSearchBackFragment : Fragment() {

    lateinit var imageView: ImageView
    var isSearchShowing = false

    val vectorAnimationCallback = object : Animatable2.AnimationCallback() {
        override fun onAnimationEnd(drawable: Drawable?) = setUpVectorAnimation()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_avd_end, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageView = view.findViewById(R.id.animated_image)
        setUpVectorAnimation()

        view.setOnClickListener {
            getTranslationAnimation().start()
            (imageView.drawable as Animatable2).start()
        }
    }

    fun setUpVectorAnimation() {
        (imageView.drawable as Animatable2?)?.unregisterAnimationCallback(vectorAnimationCallback)

        imageView.setImageResource(
            if (isSearchShowing)
                R.drawable.avd_back_to_search
            else
                R.drawable.avd_search_to_back
        )
        isSearchShowing = !isSearchShowing

        (imageView.drawable as Animatable2).registerAnimationCallback(vectorAnimationCallback)
    }

    fun getTranslationAnimation() =
        ObjectAnimator.ofFloat(
            imageView,
            View.TRANSLATION_X,
            if (imageView.translationX == 0f)
                -(requireView().width - imageView.width).toFloat()
            else
                0f
        ).apply {
            duration = resources.getInteger(R.integer.searchback_duration).toLong()
        }
}