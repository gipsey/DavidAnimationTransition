package david.animationtransition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewTreeLifecycleOwner

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewTreeLifecycleOwner.set(window.decorView, this)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            open(MenuFragment::class.java, false)
    }

    fun open(fragmentClass: Class<out Fragment>, addToBackStack: Boolean = true) {
        val fragment = fragmentClass.newInstance()

        val transaction = supportFragmentManager
            .beginTransaction()
            .setPrimaryNavigationFragment(fragment)
            .replace(R.id.fragment_container, fragment)

        if (addToBackStack) transaction.addToBackStack(null)

        transaction.commit()
    }
}
