package david.animationtransition.examples.transition

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionInflater
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import david.animationtransition.R

class ActivityTransitionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_transition_1_activity, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageView = view.findViewById<ImageView>(R.id.image_view)

        imageView.setOnClickListener {
            val activity = requireActivity()

            activity.window.allowReturnTransitionOverlap = false
            activity.window.reenterTransition = Slide(Gravity.END)
            activity.window.exitTransition = Slide(Gravity.END)

            val intent = Intent(view.context, Transition1Activity::class.java)
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity, imageView, "star").toBundle()

            startActivity(intent, bundle)
        }
    }
}

class Transition1Activity : AppCompatActivity() {

    override fun onAttachedToWindow() {
        window.allowEnterTransitionOverlap = false
        window.enterTransition = Slide(Gravity.END)
        window.exitTransition = Slide(Gravity.END)

        val sharedElementTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        window.sharedElementEnterTransition = sharedElementTransition
        window.sharedElementExitTransition = sharedElementTransition
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_1)
    }
}
