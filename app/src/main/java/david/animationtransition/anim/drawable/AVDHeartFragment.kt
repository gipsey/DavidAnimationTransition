package david.animationtransition.anim.drawable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import david.animationtransition.R
import timber.log.Timber

class AVDHeartFragment : Fragment() {

    private var isSelected = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_avd_center, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageView = view.findViewById<ImageView>(R.id.animated_image)
        imageView.setImageResource(R.drawable.animated_selector_avd_heart)

        view.setOnClickListener {
            Timber.d("animate $isSelected")
            isSelected = !isSelected
            imageView.isSelected = isSelected
        }
    }
}