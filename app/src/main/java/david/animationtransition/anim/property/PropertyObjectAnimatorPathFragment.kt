package david.animationtransition.anim.property

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import david.animationtransition.R

class PropertyObjectAnimatorPathFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_object_animator_with_path_interpolator, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.setOnClickListener {
            animate(view.findViewById(R.id.subject_view))
        }
    }

    private fun animate(view: View) {
        val width = (view.parent as ViewGroup).width.toFloat() - view.width.toFloat()

        val path = Path().apply {
            arcTo(0f, 0f, width, width, 270f, 359f, true)
        }

        with(ObjectAnimator.ofFloat(view, View.X, View.Y, path)) {
            duration = 2000
            start()
        }
    }
}
