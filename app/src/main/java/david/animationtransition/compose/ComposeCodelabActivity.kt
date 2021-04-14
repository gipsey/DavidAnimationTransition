package david.animationtransition.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import david.animationtransition.compose.ui.theme.DavidAnimationTransitionTheme
import timber.log.Timber

class ComposeCodelabActivity : ComponentActivity() {

    private lateinit var viewModel: ComposeCodelabViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel = viewModel()
            Main()
        }
    }

    @Preview
    @Composable
    fun Main() {
        val clicks by viewModel.clicksData.observeAsState(0)
        val name by viewModel.nameData.observeAsState()

        Timber.d("recomposition - main")

        DavidAnimationTransitionTheme {
            Surface(
                color = MaterialTheme.colors.primary,
                elevation = 12.dp
            ) {
                Timber.d("recomposition - Surface")

                Column {
                    Timber.d("recomposition - Column")

                    Greeting("Android")

                    Divider(
                        color = Color.Black,
                        modifier = Modifier
                            .height(10.dp)
                            .padding(4.dp)
                    )

                    CounterButton(clicks) { viewModel.onClicksChange(it) }

                    NameTextField(name) { viewModel.onNameChange(it) }
                }
            }
        }
    }

    @Composable
    private fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Composable
    private fun CounterButton(clicks: Int, clickCallback: (Int) -> Unit) {
        Timber.d("recomposition - CounterButton")
        Button(
            onClick = { clickCallback(clicks + 1) }
        ) {
            Timber.d("recomposition - Button - clicks = $clicks")
            Text("Clicks count is $clicks")
        }
    }

    @Composable
    private fun NameTextField(name: String?, nameChangeCallback: (String) -> Unit) {
        Timber.d("recomposition - NameTextField")

        Surface(
            color = MaterialTheme.colors.background
        ) {
            Column {
                Divider(
                    Modifier.height(24.dp)
                )

                OutlinedTextField(
                    label = { Text("Your name") },
                    value = name ?: "",
                    onValueChange = nameChangeCallback
                )
            }
        }
    }
}

class ComposeCodelabViewModel : ViewModel() {

    private val _clicksData = MutableLiveData<Int>()
    private val _nameData = MutableLiveData<String>()

    val clicksData: LiveData<Int> = _clicksData
    val nameData: LiveData<String> = _nameData

    fun onClicksChange(clicks: Int) {
        Timber.d("onClicksChange - $clicks")
        _clicksData.value = clicks
    }

    fun onNameChange(name: String) {
        Timber.d("onNameChange - $name")
        _nameData.value = name
    }
}