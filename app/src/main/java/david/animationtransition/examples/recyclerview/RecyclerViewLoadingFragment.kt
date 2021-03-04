package david.animationtransition.examples.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.softvision.mvvm.jetpack.ui.movie.MovieListAdapter
import david.animationtransition.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecyclerViewLoadingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_recycler_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = MovieListAdapter(view.context)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            delay(4000)
            adapter.submitList(movies)
            recyclerView.scrollToPosition(0)
        }
    }
}
