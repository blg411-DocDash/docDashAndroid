package com.example.docdash.ui.taskDetails

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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.example.docdash.R

@Preview
@Composable
fun TaskDetails() {
    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.background))
            .padding(bottom = 20.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            HeaderRow()
            TaskContainer()
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)

        ) {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark_blue)),
            ) {
                Text(
                    text = stringResource(id = R.string.task_pool),
                    style = TextStyle(
                        fontSize = 27.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }

            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark_blue)),
            ) {
                Text(
                    text = stringResource(id = R.string.my_tasks),
                    style = TextStyle(
                        fontSize = 27.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
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
        Spacer(modifier = Modifier.width(25.dp))
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
        color = colorResource(id = R.color.dark_blue)
    )
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 28.dp)
            .background(color = colorResource(R.color.container_bg)),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFE3DE56))
                .padding(8.dp)
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
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_green)),
            shape = RoundedCornerShape(size = 10.dp),
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 30.dp)
                .shadow(elevation = 15.dp, spotColor = Color(0x80000000), ambientColor = Color(0x80000000))
        ) {
            Text(
                text = stringResource(id = R.string.take_task),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.fonts)),
                    fontWeight = FontWeight(700),
                    color = colorResource(id = R.color.dark_blue),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@Composable
fun TaskDescription(){
    InfoContainer{
        Column(
            modifier = Modifier
                .padding(8.dp)
                .sizeIn(minHeight = 50.dp, maxHeight = 80.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.dummy_text_very_long),
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.fonts)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF04385F),
                )

            )
        }
    }
}

@Composable
fun PatientContainer(){
    InfoContainer{
        Column(
            modifier = Modifier
                .background(color = Color(0xE5E4FCFF))
                .padding(8.dp)
                .sizeIn(minHeight = 50.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Row{
                Text(
                    text = stringResource(R.string.patient_title),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF04385F),
                        textDecoration = TextDecoration.Underline,
                    )
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(R.string.dummy_text_short),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF04385F),
                    )
                )
            }
            Row {
                Text(
                    text = stringResource(R.string.patient_room_title),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF04385F),
                        textDecoration = TextDecoration.Underline,
                    )
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(R.string.dummy_text_short),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF04385F),
                    )
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark_blue)),
                shape = RoundedCornerShape(size = 10.dp),
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 5.dp)
                    .align(Alignment.CenterHorizontally)

            ) {
                Text(
                    text = stringResource(id = R.string.patient_details),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}

@Composable
fun TestContainer(){
    InfoContainer{
        Column(
            modifier = Modifier
                .background(color = Color(0xE5E4FCFF))
                .padding(8.dp)
                .sizeIn(minHeight = 80.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Text(
                    text = stringResource(id = R.string.tests),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF04385F),
                        textDecoration = TextDecoration.Underline,
                    )
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(R.string.dummy_text),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF04385F),
                    )
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark_blue)),
                shape = RoundedCornerShape(size = 10.dp),
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 5.dp)
                    .align(Alignment.CenterHorizontally)

            ) {
                Text(
                    text = stringResource(id = R.string.required_tests),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}

@Composable
fun InfoContainer(content: @Composable () -> Unit) {
    Surface(modifier = Modifier
        .padding(horizontal = 12.dp, vertical = 5.dp)
        .fillMaxWidth(),
        color = colorResource(id = R.color.info_container_bg).copy(alpha = 0.9f),
        content = content)
}

