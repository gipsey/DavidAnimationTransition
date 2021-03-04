package david.animationtransition.examples

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import david.animationtransition.R
import kotlinx.android.synthetic.main.fragment_motion3.*

class MotionLayoutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        inflater.inflate(R.layout.fragment_motion1, container, false)
//        inflater.inflate(R.layout.fragment_motion2, container, false)
//
//        val duration = motion_layout.definedTransitions[0].duration
//        Log.d("MainActivity", "duration $duration")

        return inflater.inflate(R.layout.fragment_motion3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        appbar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val seekPosition = -verticalOffset / appbar_layout.totalScrollRange.toFloat()

            Log.d("OnOffsetChangedListener", "$verticalOffset $seekPosition")
            motion_layout.progress = seekPosition
        })
    }
}
