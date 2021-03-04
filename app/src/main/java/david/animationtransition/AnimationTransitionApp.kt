package david.animationtransition

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber
import timber.log.Timber.DebugTree

class AnimationTransitionApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())
        else
            Timber.plant(ReleaseTree())
    }
}

private class ReleaseTree : Timber.Tree() {

    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR)
            Log.e(tag, message, t)
        else if (priority == Log.WARN)
            Log.w(tag, message, t)
    }
}