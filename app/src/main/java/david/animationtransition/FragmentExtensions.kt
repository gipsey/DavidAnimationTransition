package david.animationtransition

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * Sets [View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR] and executes requesting the recalculation of window insets.
 * Recalculation is needed in order to treat the hiding/showing of status bar by avoiding cropped layout (i.e. [BottomNavigationView])
 */
fun Fragment.setLightStatusBarWithRequestApplyInsets() = setLightStatusBar()?.apply { window.decorView.requestApplyInsets() }

fun Fragment.setLightStatusBar() = activity?.apply {
    window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
}

fun Fragment.setDefaultStatusBar(@ColorRes background: Int) = activity?.let {
    it.window.statusBarColor = ContextCompat.getColor(it, background)
    it.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
}

fun Fragment.showContentFullscreen() = activity?.let {
    it.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
}

fun Fragment.showContentFullscreenWithLightStatusBarIcons() = activity?.let {
    it.window.statusBarColor = ContextCompat.getColor(it, android.R.color.transparent)
    it.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
}

fun Fragment.showContentFullscreenWithDarkStatusBarIcons() = activity?.let {
    it.window.statusBarColor = ContextCompat.getColor(it, android.R.color.transparent)
    it.window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
}

fun Fragment.setDarkStatusBar() = activity?.let {
    it.window.statusBarColor = ContextCompat.getColor(it, R.color.dark)
    it.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
}
