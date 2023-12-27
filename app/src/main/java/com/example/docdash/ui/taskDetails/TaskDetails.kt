package com.example.docdash.ui.taskDetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.docdash.ui.theme.DocDashTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.docdash.R
import retrofit2.http.Header

class TaskDetails : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskPage()
        }
    }
}

@Composable
fun TaskPage() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderRow()
            TaskContainer()
            Spacer(modifier = Modifier.height(60.dp) )
            Row {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFF2D6C9B)),
                    shape = RoundedCornerShape(size = 10.dp)
                    ) {
                    Text(
                        text = stringResource(R.string.task_pool)
                    )
                }
                Spacer(modifier = Modifier.width(60.dp) )
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFF2D6C9B)),
                    shape = RoundedCornerShape(size = 10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.my_tasks)
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderRow() {
    Row(modifier = Modifier
        .background(color = colorResource(R.color.dark_blue))
        .fillMaxWidth()
        .height(60.dp)
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Icon",
            tint = Color(0xFFFFFFFF),)
        Spacer(modifier = Modifier.width(60.dp))
        Text(text = stringResource(id = R.string.task_details),
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.fonts)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
            ),
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun TaskContainer(){
    val style = TextStyle(
        fontSize = 25.sp,
        fontFamily = FontFamily(Font(R.font.fonts)),
        fontWeight = FontWeight(700),
        color = Color(0xFF002540),
        )
    Column(
        modifier = Modifier
            .width(316.dp)
            .height(581.dp)
            .background(color = Color(0xFFB8F5E9)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .width(316.dp)
                .height(52.dp)
                .background(color = Color(0xFFE3DE56))
                .padding(5.dp),
        ){
            Text(
                text = stringResource(R.string.task_number),
                style = style
            )
            Text(
                text = stringResource(R.string.dummy_number),
                style = style
            )
        }
        TaskDescription()
        PatientContainer()
        TestContainer()
        Button(
            onClick = { /*TODO*/ } ,
            colors = ButtonDefaults.buttonColors(Color(0xFF2D6C9B)),
            shape = RoundedCornerShape(size = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.take_task)
            )
        }
    }
}

@Composable
fun TaskDescription(){
    Column(
        modifier = Modifier
            .width(291.dp)
            .height(91.40204.dp)
            .background(color = Color(0xE5E4FCFF)),
            verticalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(R.string.dummy_text),
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.fonts)),
                fontWeight = FontWeight(600),
                color = Color(0xFF04385F),
            )

        )
    }
}

@Composable
fun PatientContainer(){
    Column (
        modifier = Modifier
            .width(291.dp)
            .height(135.dp)
            .background(color = Color(0xE5E4FCFF)),
            verticalArrangement = Arrangement.Center

    ){
        Row (){
            Text(
                text = stringResource(R.string.patient_title)             )
            Text(
                text = stringResource(R.string.dummy_text_short)
            )
        }
        Row (){
            Text(
                text = stringResource(R.string.patient_room_title)            )
            Text(
                text = stringResource(R.string.dummy_text_short)
            )
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color(0xFF2D6C9B)),
            shape = RoundedCornerShape(size = 10.dp)

        ) {
            Text(
                text = stringResource(id = R.string.patient_details)
            )
        }
    }

}

@Composable
fun TestContainer(){
    Column (
        modifier = Modifier
            .width(291.dp)
            .height(135.dp)
            .background(color = Color(0xE5E4FCFF)),
        verticalArrangement = Arrangement.Center
    ){
        Row {
            Text(
                text = stringResource(id = R.string.tests)
            )
            Text(
                text = stringResource(R.string.dummy_text)
            )
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color(0xFF2D6C9B)),
            shape = RoundedCornerShape(size = 10.dp)
        )  {
            Text(
                text = stringResource(R.string.tests_details)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TaskDetailsPreview() {
    TaskPage()
}