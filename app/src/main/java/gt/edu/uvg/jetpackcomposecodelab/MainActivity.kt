package gt.edu.uvg.jetpackcomposecodelab

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gt.edu.uvg.jetpackcomposecodelab.ui.theme.JetpackComposeCodelabTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeCodelabTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier){
        if(shouldShowOnboarding){
            OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
        }else {
            Greetings()
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
    // TODO: This state should be hoisted


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick =  onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun Greetings(modifier: Modifier = Modifier, names: List<String> = List(1000) {"$it"}){
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)){
        items(items = names){
            name -> Greeting(name = name)
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState(
        if(expanded) 48.dp else 0.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)

    )
    Surface (
        color = MaterialTheme.colorScheme.primary,
                modifier = modifier.padding(vertical= 4.dp, horizontal = 8.dp)
    ){
        Row (modifier = Modifier.padding(24.dp)) {
            Column (modifier = modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))){
                Text(
                    text = "Hello",
                )
                Text(
                    text = "$name!",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
                )
                if (expanded) Text(text="AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
            }
            IconButton(onClick = {expanded = !expanded}) {
                if (expanded){
                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Show more")
                }else{
                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Show more")
                }

            }

        }


    }


}







// ...



@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    JetpackComposeCodelabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true, name = "Greeting Preview Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp  = 320)
@Composable
fun GreetingsPreview() {
    JetpackComposeCodelabTheme {
        Greetings()
    }
}

@Preview(showBackground = true, name = "Greeting Preview", widthDp  = 320)
@Composable
fun GreetingsPreviewDark() {
    JetpackComposeCodelabTheme {
        Greetings()
    }
}

@Preview
@Composable
fun MyAppPreview(){
    JetpackComposeCodelabTheme {
        MyApp(Modifier.fillMaxSize())

    }
}