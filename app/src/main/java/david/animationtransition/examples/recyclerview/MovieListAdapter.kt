package com.softvision.mvvm.jetpack.ui.movie

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import david.animationtransition.R
import david.animationtransition.examples.recyclerview.MovieAdapterItem
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieListAdapter(context: Context) : ListAdapter<MovieAdapterItem, MovieListAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    private val inflater = LayoutInflater.from(context)

    init {
        submitList(List(10) { MovieAdapterItem.PlaceHolder })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(inflater.inflate(R.layout.movie_list_item, parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        when (val item = getItem(position)) {
            is MovieAdapterItem.Movie -> holder.bind(item)
            MovieAdapterItem.PlaceHolder -> holder.bindPlaceHolder()
        }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val placeHolderAnimator =
            ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f, 1f).apply {
                duration = PLACEHOLDER_FADE_DURATION
                repeatCount = ValueAnimator.INFINITE
            }

        init {
            (itemView as ViewGroup).isTransitionGroup = true
        }

        fun bind(movie: MovieAdapterItem.Movie) {
            placeHolderAnimator.end()

            with(itemView) {
                image_view.background = null
                title_text_view.background = null
                overview_text_view.background = null

                rating_bar.rating = movie.vote
                image_view.setImageDrawable(null)
                title_text_view.text = movie.title
                overview_text_view.text = movie.overview
            }

            if (adapterPosition > lastBoundPosition) applyAppearanceAnimator()

            lastBoundPosition = adapterPosition
        }

        fun bindPlaceHolder() {
            placeHolderAnimator.currentPlayTime = (SystemClock.elapsedRealtime() - adapterPosition * 40L) % PLACEHOLDER_FADE_DURATION
            placeHolderAnimator.start()

            with(itemView) {
                image_view.setImageResource(R.drawable.image_placeholder)
                title_text_view.setBackgroundResource(R.drawable.text_placeholder)
                overview_text_view.setBackgroundResource(R.drawable.text_long_placeholder)

                rating_bar.rating = 0f
                image_view
                title_text_view.text = null
                overview_text_view.text = "\n"
            }
        }

        private fun applyAppearanceAnimator() =
            ObjectAnimator.ofPropertyValuesHolder(
                itemView,
                PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f),
                PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, itemView.measuredHeight.toFloat(), 0f)
            ).apply {
                duration = APPEARANCE_TRANSLATION_DURATION
                start()
            }
    }

    companion object {
        private const val PLACEHOLDER_FADE_DURATION = 1000L
        private const val APPEARANCE_TRANSLATION_DURATION = 400L
        private var lastBoundPosition = -1

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieAdapterItem>() {
            override fun areItemsTheSame(oldItem: MovieAdapterItem, newItem: MovieAdapterItem) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: MovieAdapterItem, newItem: MovieAdapterItem) = oldItem == newItem
        }
    }
}