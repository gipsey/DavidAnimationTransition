package david.animationtransition.examples.drawable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import david.animationtransition.R
import timber.log.Timber

class AVDHeartFragment : Fragment() {

    private lateinit var imageView: ImageView
    private var isChecked = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_avd_center, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageView = view.findViewById(R.id.animated_image)
        imageView.setImageResource(R.drawable.animated_selector_avd_heart)

        view.setOnClickListener { animate() }
    }

    private fun animate() {
        Timber.d("animate $isChecked")

        isChecked = !isChecked
        val state = IntArray(if (isChecked) android.R.attr.state_checked else android.R.attr.state_empty)

        imageView.setImageState(state, true)
    }
}
