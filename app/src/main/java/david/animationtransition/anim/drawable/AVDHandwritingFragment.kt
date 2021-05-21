package david.animationtransition.anim.drawable

import android.graphics.drawable.Animatable2
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import david.animationtransition.R

class AVDHandwritingFragment : Fragment() {

    lateinit var imageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_avd_center, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageView = view.findViewById(R.id.animated_image)
        imageView.setImageResource(R.drawable.avd_handwriting)

        view.setOnClickListener {
            (imageView.drawable as Animatable2).start()
        }
    }
}