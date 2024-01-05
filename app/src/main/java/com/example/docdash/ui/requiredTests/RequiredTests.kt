package com.example.docdash.ui.requiredTests

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.docdash.R
import com.example.docdash.data.serviceData.response.TestGetResponse
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.docdash.ui.taskDetails.TaskDetailsActivity
import com.example.docdash.ui.taskDetails.TaskDetailsViewModel
import com.example.docdash.utils.DateTimeHandler
import com.google.gson.Gson


@Composable
fun RequiredTests(viewModel: RequiredTestsViewModel) {
    val context = LocalContext.current

    val taskDetailsPage = Intent(context, TaskDetailsActivity::class.java)

    val gson = Gson()
    gson.toJson(viewModel.taskDetailsLiveData.value)?.let {
        taskDetailsPage.putExtra("taskDetails", it)
    }
    taskDetailsPage.putExtra("taskID", viewModel.taskDetailsLiveData.value?.id)

    Log.d("RequiredTests", "Tests: ${viewModel.testList}")
    taskDetailsPage.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT

    Column(modifier = Modifier
        .background(color = colorResource(R.color.background))
        .padding(bottom = 20.dp)
        .fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            HeaderRow()
            TaskDetails(viewModel)
            TestContainer(viewModel)
        }

        Button(
            onClick = {
                ContextCompat.startActivity(context, taskDetailsPage, null)
            },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark_blue)),
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .align(Alignment.End)
        ) {
            Text(
                text = stringResource(id = R.string.back_to_task_details),
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.fonts)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )
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
        Text(text = stringResource(id = R.string.required_tests),
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
fun TaskDetails(viewModel: RequiredTestsViewModel){
    val style = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.fonts)),
        fontWeight = FontWeight(700),
        color = colorResource(id = R.color.dark_blue)
    )
    InfoContainer {
        Column(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {

            Row{
                Text(
                    text = stringResource(R.string.task_number),
                    style = style
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = viewModel.taskDetailsLiveData.value?.id?:"",
                    style = style
                )
            }
            Row{
                Text(
                    text = stringResource(R.string.task_due),
                    style = style
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = DateTimeHandler.epochSecondsToDateTime(viewModel.taskDetailsLiveData.value?.deadline?: 0).substring(0, 10),
                    style = style
                )
            }
            Row{
                Text(
                    text = stringResource(R.string.patient_title),
                    style = style
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = viewModel.taskDetailsLiveData.value?.patient?.name?:"",
                    style = style
                )
            }

            Row {
                Text(
                    text = stringResource(R.string.patient_room_title),
                    style = style
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = viewModel.taskDetailsLiveData.value?.entry?.room?:"",
                    style = style
                )
            }

        }
    }

}


@Composable
fun TestContainer(viewModel: RequiredTestsViewModel){

    val testList by viewModel.testList.observeAsState(initial = emptyList())
    items(testList, viewModel)

}
@Composable
fun InfoContainer(content: @Composable () -> Unit) {
    Surface(modifier = Modifier
        .padding(vertical = 10.dp, horizontal = 15.dp)
        .fillMaxWidth(),
        color = colorResource(id = R.color.info_container_bg).copy(alpha = 0.9f),
        content = content)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableTextField(item: TestGetResponse, viewModel: RequiredTestsViewModel) {
    val itemText = item.result
    var text by remember(itemText) { mutableStateOf(itemText ?: "Write Results...") }
    var isEditing by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 5.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = {
                if (text == "Write Results...") {
                    text = ""
                } else if (isEditing && item.status != "closed" ){
                    text = it
                }
            },
            textStyle = TextStyle(
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.fonts)),
                fontWeight = FontWeight(700),
                color = colorResource(id = R.color.dark_blue),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .heightIn(min = 50.dp), // Set the minimum height as needed
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.app_box_gold)
            ),
        )
        Spacer(modifier = Modifier.width(5.dp))
        Button(
            onClick = {
                isEditing = false

                viewModel.updateTestResult(id = item.id, result = text)
            },
             colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.dark_blue)
            )
        ) {
            Text(
                text = "Save",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.fonts)),
                    fontWeight = FontWeight(700),
                    color = colorResource(id = R.color.info_container_bg),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }

}

@Composable
fun TestBox(item: TestGetResponse, viewModel: RequiredTestsViewModel){
    InfoContainer {
        Column{
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 5.dp)
                    .background(
                        when (item.status) {
                            "open" -> {
                                colorResource(id = R.color.light_green)
                            }

                            "in progress" -> {
                                colorResource(id = R.color.dark_blue)
                            }

                            else -> {
                                colorResource(id = R.color.light_gray)
                            }
                        }
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.name ?: "",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = colorResource(id = R.color.dark_blue),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(10.dp)
                )
                IconButton(
                    onClick = {
                        //viewModel.updateTestResult(id = item.id, result = text)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.tick_icon),
                        contentDescription = "Save Icon",
                        tint = colorResource(id = R.color.dark_blue)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {


                Text(
                    text = item.information ?: "",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(500),
                        color = colorResource(id = R.color.dark_blue),
                    )
                )
                EditableTextField(item, viewModel)

            }
        }
    }
}

@Composable
fun items(items: List<TestGetResponse>?, viewModel: RequiredTestsViewModel) {
    LazyColumn(
    ) {
        items?.let { itemList ->
            items(itemList.size) { index ->

                TestBox(itemList[index], viewModel)
            }
        }
    }
}

