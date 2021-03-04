package david.animationtransition.examples.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.softvision.mvvm.jetpack.ui.movie.MovieListAdapter
import david.animationtransition.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecyclerViewLoadingStaggeringFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_recycler_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = MovieListAdapter(view.context)

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = object : DefaultItemAnimator() {
            // We animate item additions on our side, so disable it in RecyclerView.
            override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
                dispatchAddFinished(holder)
                dispatchAddStarting(holder)
                return false
            }
        }

        lifecycleScope.launch {
            delay(2000)
            adapter.submitList(null)

            TransitionManager.beginDelayedTransition(recyclerView, StaggerTransition())
            adapter.submitList(movies)
        }
    }
}
