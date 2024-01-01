package com.example.docdash.ui.taskDetails

import android.content.Intent
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.example.docdash.R
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.ui.taskPool.TaskPoolActivity

@Composable
fun TaskDetails(task: TaskGetResponse) {
    // This is the context of the activity
    val context = LocalContext.current

    // Intents to launch task pool and my tasks activities
    val taskPoolIntent = Intent(context, TaskPoolActivity::class.java)
    val myTasksIntent = Intent(context, TaskPoolActivity::class.java)

    // This is the activity result launcher for starting the activity for result.
    val startActivity: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result -> }


    // If the taskGetResponse is null, then we will use default text values,
    // otherwise we will use the values from the taskGetResponse.
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
            TaskContainer(task)
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)

        ) {
            Button(
                onClick = {
                          startActivity.launch(taskPoolIntent)
                },
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
                onClick = {
                            startActivity.launch(myTasksIntent)
                },
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
fun TaskContainer(task: TaskGetResponse) {
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
                text = task.id ?: "0",
                style = style
            )
        }
        TaskDescription(task.information ?: "N/A")
        PatientContainer(task.patient?.name ?: "N/A", task.entry?.room ?: "N/A")

        // Create a string from the tests
        val testText: String = if (task.tests?.isNotEmpty() == true) {
            task.tests!!.joinToString(separator = "\n") { it.information ?: "N/A" }
        } else {
            "N/A"
        }

        TestContainer(testText)
        Button(
            onClick = {
                      // TODO

            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_green)),
            shape = RoundedCornerShape(size = 10.dp),
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 30.dp)
                .shadow(
                    elevation = 15.dp,
                    spotColor = Color(0x80000000),
                    ambientColor = Color(0x80000000)
                )
        ) {
            Text(
                // Take Task / Complete Task / Task Completed button is generated based on the task status
                text = if (task.status == "open") {
                    stringResource(id = R.string.take_task)
                } else if (task.status == "in progress") {
                    stringResource(id = R.string.complete_task)
                } else {
                    stringResource(id = R.string.task_completed)
                },
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
fun TaskDescription(taskDescription: String){
    InfoContainer{
        Column(
            modifier = Modifier
                .padding(8.dp)
                .sizeIn(minHeight = 50.dp, maxHeight = 80.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = taskDescription,
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
fun PatientContainer(patient: String, room: String){
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
                    text = patient,
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
                    text = room,
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
fun TestContainer(testDescription: String){
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
                    text = testDescription,
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

