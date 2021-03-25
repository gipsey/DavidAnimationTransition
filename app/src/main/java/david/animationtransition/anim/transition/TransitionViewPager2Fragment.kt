package david.animationtransition.anim.transition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.facebook.drawee.backends.pipeline.Fresco
import david.animationtransition.R
import kotlinx.android.synthetic.main.fragment_transition_viewpager2.view.*
import kotlinx.android.synthetic.main.layout_image_carousel_item.view.*
import kotlin.math.abs
import kotlin.math.max

class TransitionViewPager2Fragment : Fragment() {

    private val transformers = listOf(ZoomOut(), Depth(), BookFlip(), CardFlip())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_transition_viewpager2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        transformers.forEachIndexed { index, transformer ->
            view.transformer_radio_group.addView(
                RadioButton(requireContext())
                    .apply {
                        id = index
                        text = transformer.javaClass.simpleName
                    })
        }

        view.transformer_radio_group.setOnCheckedChangeListener { _, i ->
            setAdapter(view)
            view.view_pager.setPageTransformer(transformers[i])
        }

        setAdapter(view)
    }

    private fun setAdapter(view: View) {
        view.view_pager.adapter =
            ImageCarouselAdapter().apply {
                submitList(imageLinks)
            }
    }

    companion object {
        private val imageLinks = listOf(
            "https://scene7.zumiez.com/is/image/zumiez/pdp_hero/Vans-Kastle-Evening-Haze-Lilac-Windbreaker-Jacket-_306013-alt3-US.jpg",
            "https://scene7.zumiez.com/is/image/zumiez/pdp_hero/Vans-Kastle-Evening-Haze-Lilac-Windbreaker-Jacket-_159542-front-CA.jpg",
            "https://scene7.zumiez.com/is/image/zumiez/pdp_hero/Vans-Kastle-Evening-Haze-Lilac-Windbreaker-Jacket-_159542-back-CA.jpg",
            "https://scene7.zumiez.com/is/image/zumiez/pdp_hero/Vans-Kastle-Evening-Haze-Lilac-Windbreaker-Jacket-_159542-alt1-CA.jpg",
            "https://scene7.zumiez.com/is/image/zumiez/pdp_hero/Vans-Kastle-Evening-Haze-Lilac-Windbreaker-Jacket-_159542-alt2-CA.jpg"
        )
    }
}

class ImageCarouselAdapter : ListAdapter<String, ImageCarouselAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_image_carousel_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.image_view.controller =
            Fresco.newDraweeControllerBuilder()
                .setUri(getItem(position))
                .build()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }
}


class ZoomOut : ViewPager2.PageTransformer {

    companion object {
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f
    }

    override fun transformPage(view: View, position: Float) {
        view.apply {
            when {
                position < -1 -> { // [-Infinity,-1)
                    alpha = 0f
                }
                position <= 1 -> { // [-1,1]
                    val scaleFactor = max(MIN_SCALE, 1 - abs(position))

                    val vertMargin = height * (1 - scaleFactor) / 2
                    val horzMargin = width * (1 - scaleFactor) / 2

                    translationX =
                        if (position < 0)
                            horzMargin - vertMargin / 2
                        else
                            horzMargin + vertMargin / 2

                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    alpha = MIN_ALPHA + (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA))
                }
                else -> { // (1,+Infinity]
                    alpha = 0f
                }
            }
        }
    }
}

class Depth : ViewPager2.PageTransformer {

    companion object {
        private const val MIN_SCALE = .7f
    }

    override fun transformPage(view: View, position: Float) {
        view.apply {
            when {
                position < -1 -> { // [-Infinity,-1)
                    alpha = 0f
                }
                position <= 0 -> { // [-1,0]
                    alpha = 1f
                    translationX = 0f
                    translationZ = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1 -> { // [0,1]
                    alpha = 1 - position
                    translationX = view.width * -position
                    translationZ = -1f
                    val scale = MIN_SCALE + (1 - MIN_SCALE) * (1 - position)
                    scaleX = scale
                    scaleY = scale
                }
                else -> { // (1,+Infinity]
                    alpha = 0f
                }
            }
        }
    }
}

class BookFlip : ViewPager2.PageTransformer {
    var scaleAmountPercent = 5f
    var isEnableScale = true

    override fun transformPage(page: View, position: Float) {
        val percentage = 1 - abs(position)

        // Don't move pages once they are on left or right
        if (position > 0 && position <= 1) {
            page.translationX = -position * page.width
            page.translationY = 0f
            page.translationZ = -1f
            page.rotation = 0f
            if (isEnableScale) {
                val amount = (100 - scaleAmountPercent + scaleAmountPercent * percentage) / 100
                setSize(page, position, amount)
            }
        } else {
            page.visibility = View.VISIBLE
            flipPage(page, position, percentage)
        }
    }

    private fun flipPage(page: View, position: Float, percentage: Float) {
        page.cameraDistance = -30000f
        setVisibility(page, position)
        setTranslation(page)
        setPivot(page, 0f, page.height * 0.5f)
        setRotation(page, position, percentage)
    }

    private fun setPivot(page: View, pivotX: Float, pivotY: Float) {
        page.pivotX = pivotX
        page.pivotY = pivotY
    }

    private fun setVisibility(page: View, position: Float) {
        if (position < 0.5 && position > -0.5) {
            page.visibility = View.VISIBLE
        } else {
            page.visibility = View.INVISIBLE
        }
    }

    private fun setTranslation(page: View) {
        val viewPager = requireViewPager(page)
        val scroll = viewPager.scrollX - page.left
        page.translationX = scroll.toFloat()
        page.translationZ = 1f
    }

    private fun setSize(page: View, position: Float, percentage: Float) {
        page.scaleX = if (position != 0f && position != 1f) percentage else 1f
        page.scaleY = if (position != 0f && position != 1f) percentage else 1f
    }

    private fun setRotation(page: View, position: Float, percentage: Float) {
        page.rotationY =
            if (position > 0)
                -180 * (percentage + 1)
            else
                180 * (percentage + 1)
    }
}

class CardFlip : ViewPager2.PageTransformer {
    var isScalable = false

    override fun transformPage(page: View, position: Float) {
        val percentage = 1 - abs(position)
        page.cameraDistance = 30000f
        setVisibility(page, position)
        setTranslation(page)
        setSize(page, position, percentage)
        setRotation(page, position, percentage)
    }

    private fun setVisibility(page: View, position: Float) {
        if (position < 0.5 && position > -0.5) {
            page.visibility = View.VISIBLE
        } else {
            page.visibility = View.INVISIBLE
        }
    }

    private fun setTranslation(page: View) {
        val viewPager = requireViewPager(page)
        val scroll = viewPager.scrollX - page.left
        page.translationX = scroll.toFloat()
    }

    private fun setSize(page: View, position: Float, percentage: Float) {
        if (!isScalable) return

        page.scaleX = if (position != 0f && position != 1f) percentage else 1f
        page.scaleY = if (position != 0f && position != 1f) percentage else 1f
    }

    private fun setRotation(page: View, position: Float, percentage: Float) {
        if (position > 0)
            page.rotationY = -180 * (percentage + 1)
        else
            page.rotationY = 180 * (percentage + 1)
    }
}

private fun requireViewPager(page: View): ViewPager2 {
    val parent = page.parent
    val parentParent = parent.parent

    if (parent is RecyclerView && parentParent is ViewPager2) return parentParent

    throw IllegalStateException("Expected the page view to be managed by a ViewPager2 instance.")
}