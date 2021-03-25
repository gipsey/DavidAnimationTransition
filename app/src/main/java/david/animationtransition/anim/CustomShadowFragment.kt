package david.animationtransition.anim

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.AsyncTaskLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import david.animationtransition.R
import kotlinx.android.synthetic.main.fragment_custom_shadow.*
import kotlin.math.abs

class CustomShadowFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_custom_shadow, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        carousel.setHasFixedSize(true)
        LinearSnapHelper().attachToRecyclerView(carousel)
        carousel.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val center = recyclerView.width / 2

                (0 until recyclerView.childCount).forEach { i ->
                    val child = recyclerView.getChildAt(i) as? IconView ?: return@forEach
                    val cx = (child.left + child.right) / 2
                    val offsetPx = abs(center - cx)
                    child.offset = offsetPx / center.toFloat()
                }
            }
        })

        val loaderCallbacks = object : LoaderManager.LoaderCallbacks<MutableList<Drawable>> {

            override fun onCreateLoader(id: Int, args: Bundle?) = AppIconLoader(view.context.applicationContext)

            override fun onLoadFinished(loader: Loader<MutableList<Drawable>>, icons: MutableList<Drawable>) {
                loading.visibility = View.GONE
                carousel.adapter = AppAdapter(icons)
            }

            override fun onLoaderReset(loader: Loader<MutableList<Drawable>>) {}
        }

        loaderManager.initLoader(0, Bundle.EMPTY, loaderCallbacks)
    }

    private class AppIconLoader(context: Context) : AsyncTaskLoader<MutableList<Drawable>>(context) {

        private val icons = mutableListOf<Drawable>()

        override fun onStartLoading() {
            if (icons.isNotEmpty()) {
                deliverResult(icons)
            } else {
                forceLoad()
            }
        }

        override fun loadInBackground(): MutableList<Drawable>? {
            val pm = context.packageManager
            val launchableAppIcons = mutableListOf<Drawable>()
            val launcherIntent = Intent().apply { addCategory(Intent.CATEGORY_LAUNCHER) }
            pm.getInstalledApplications(0).forEach { appInfo ->
                launcherIntent.`package` = appInfo.packageName
                // only show launch-able apps
                if (pm.queryIntentActivities(launcherIntent, 0).isNotEmpty()) {
                    launchableAppIcons += appInfo.loadUnbadgedIcon(pm)
                }
            }

            return launchableAppIcons
        }

        override fun deliverResult(data: MutableList<Drawable>?) {
            data?.let {
                icons.addAll(it)
            }

            super.deliverResult(data)
        }
    }

    class AppAdapter(private val icons: List<Drawable>) : RecyclerView.Adapter<AppViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
            return AppViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.app_item, parent, false) as IconView
            )
        }

        override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
            holder.iconView.icon = icons[position]
        }

        override fun getItemCount() = icons.size
    }

    class AppViewHolder(val iconView: IconView) : RecyclerView.ViewHolder(iconView)
}