package david.animationtransition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import david.animationtransition.examples.CustomShadowFragment
import david.animationtransition.examples.DefaultLayoutAnimationFragment
import david.animationtransition.examples.MotionLayoutFragment
import david.animationtransition.examples.canvas.CanvasLoadingViewFragment
import david.animationtransition.examples.drawable.*
import david.animationtransition.examples.lottie.LottieDrawableFragment
import david.animationtransition.examples.lottie.LottieProgramaticallyFragment
import david.animationtransition.examples.lottie.LottieXmlFragment
import david.animationtransition.examples.property.*
import david.animationtransition.examples.property.dynamic.*
import david.animationtransition.examples.recyclerview.RecyclerViewLoadingFragment
import david.animationtransition.examples.recyclerview.RecyclerViewLoadingStaggeringFragment
import david.animationtransition.examples.transition.*
import david.animationtransition.examples.transition.FragmentTransition2Fragment
import david.animationtransition.examples.viewanimation.ViewAnimationSimpleFragment

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_menu, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<RecyclerView>(R.id.menu_recycler_view).adapter = MenuAdapter()
    }

    private inner class MenuAdapter : RecyclerView.Adapter<MenuAdapter.TitleViewHolder>() {

        private val items = listOf(
            NavigationItem("Drawable - Animation", AnimatedDrawableFragment::class.java),
            NavigationItem("Drawable - Vector Animation", AVDFragment::class.java),
            NavigationItem("Drawable - Vector Animation - SearchBack", AVDSearchBackFragment::class.java),
            NavigationItem("Drawable - Vector Animation - Welcome UNFINISHED", AVDWelcomeFragment::class.java),
            NavigationItem("Drawable - Vector Animation - Heart", AVDHeartFragment::class.java),

            NavigationItem("View - Simple", ViewAnimationSimpleFragment::class.java),

            NavigationItem("Property - Tutorial - ObjectAnimator", PropertyTutorialObjectAnimatorFragment::class.java),
            NavigationItem("Property - Simple - ObjectAnimator", PropertySimpleObjectAnimatorFragment::class.java),
            NavigationItem("Property - Simple - ViewPropertyAnimator", PropertyViewPropertyAnimatorFragment::class.java),
            NavigationItem("Property - Path movement", PropertyObjectAnimatorPathFragment::class.java),
            NavigationItem("Property - CrossFade", PropertyCrossFadeFragment::class.java),
            NavigationItem("Property - Image zoom", ImageZoomOriginalFragment::class.java),
            NavigationItem("Property - Image zoom 2", ImageZoomDavidFragment::class.java),
            NavigationItem("Property - Circular reveal", PropertyCircularRevealFragment::class.java),
            NavigationItem("Property - Password", PropertyPasswordFragment::class.java),
            NavigationItem("Property - PushDown", PropertyPushDownFragment::class.java),

            NavigationItem("Dynamic", DynamicFragment::class.java),
            NavigationItem("Dynamic - Fling - by constant", FlingByConstantFragment::class.java),
            NavigationItem("Dynamic - Fling - by gesture", FlingByGestureFragment::class.java),
            NavigationItem("Dynamic - Spring", SpringFragment::class.java),
            NavigationItem("Dynamic - Spring - use case", SpringUseCaseFragment::class.java),

            NavigationItem("Transition - Junior", TransitionJuniorFragment::class.java),
            NavigationItem("Transition - Delayed", TransitionDelayedFragment::class.java),
            NavigationItem("Transition - Color", TransitionColorFragment::class.java),
            NavigationItem("Transition - ViewPager2", TransitionViewPager2Fragment::class.java),

            NavigationItem("Activity - Transition", ActivityTransitionFragment::class.java),

            NavigationItem("Fragment transition - 1", FragmentTransition1Fragment::class.java),
            NavigationItem("Fragment transition - 2", FragmentTransition2Fragment::class.java),
            NavigationItem("Fragment transition - card flip", FragmentTransitionCardFlipFragment::class.java),

            NavigationItem("Default Layout Animation", DefaultLayoutAnimationFragment::class.java),
            NavigationItem("Motion Layout 1", MotionLayoutFragment::class.java),
            NavigationItem("Custom Shadow", CustomShadowFragment::class.java),
            NavigationItem("Canvas - Loading indicator", CanvasLoadingViewFragment::class.java),

            NavigationItem("RecyclerView - loading", RecyclerViewLoadingFragment::class.java),
            NavigationItem("RecyclerView - loading + stagger", RecyclerViewLoadingStaggeringFragment::class.java),

            NavigationItem("Lottie - Xml", LottieXmlFragment::class.java),
            NavigationItem("Lottie - Programatically", LottieProgramaticallyFragment::class.java),
            NavigationItem("Lottie - Drawable", LottieDrawableFragment::class.java)
        )

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return TitleViewHolder(view)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: TitleViewHolder, position: Int) =
            holder.bind(items[position])

        inner class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val textView = itemView.findViewById<TextView>(android.R.id.text1)

            fun bind(item: NavigationItem) {
                textView.text = item.title
                itemView.setOnClickListener { (activity as MainActivity).open(item.fragment) }
            }
        }

        inner class NavigationItem(val title: String, val fragment: Class<out Fragment>)
    }
}
