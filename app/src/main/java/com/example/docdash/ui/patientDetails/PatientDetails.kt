package com.example.docdash.ui.patientDetails

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.docdash.R
import com.example.docdash.data.serviceData.response.TestGetResponse
import com.example.docdash.ui.taskDetails.TaskDetailsActivity
import com.example.docdash.utils.DateTimeHandler
import com.google.gson.Gson


@Composable
fun PatientDetails(viewModel: PatientDetailsViewModel) {
    val context = LocalContext.current

    val taskDetailsPage = Intent(context, TaskDetailsActivity::class.java)
    // You can pass data to the activity with putExtra, they need to be basic types (string, int, etc.)
    val gson = Gson()
    gson.toJson(viewModel.taskDetailsLiveData.value)?.let {
        taskDetailsPage.putExtra("taskDetails", it)
    }
    taskDetailsPage.putExtra("taskID", viewModel.taskDetailsLiveData.value?.id)

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
            OuterContainer(viewModel)
        }

        Button(
            onClick = {
                startActivity(context, taskDetailsPage, null)
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
                ))
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
        Text(text = stringResource(id = R.string.patient_details),
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
fun OuterContainer(viewModel: PatientDetailsViewModel) {
    Column(modifier = Modifier
        .padding(horizontal = 24.dp, vertical = 28.dp)
        .background(color = colorResource(R.color.container_bg))
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        InfoContainer {
            Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)) {
                Text(
                    text = stringResource(id = R.string.name),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF04385F),
                        textDecoration = TextDecoration.Underline,
                    ),
                )
                Text(
                    text = viewModel.patientDetailsLiveData.value?.name?: "",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF04385F),
                    )
                )

            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        InfoContainer {
            Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)) {
                Text(
                    text = stringResource(id = R.string.room),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF04385F),
                        textDecoration = TextDecoration.Underline,
                    ),
                )
                Text(text = viewModel.taskDetailsLiveData.value?.entry?.room?: "",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF04385F),
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        InfoContainer {
            Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)) {
                Text(
                    text = stringResource(id = R.string.disease),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF04385F),
                        textDecoration = TextDecoration.Underline,
                    ),
                )
                Text(
                    text = viewModel.taskDetailsLiveData.value?.entry?.admitted_for?: "Unknown",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF04385F),
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        InfoContainer {
            Column(modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 3.dp)
                .fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.height),
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = FontFamily(Font(R.font.fonts)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF04385F),
                                textDecoration = TextDecoration.Underline,
                            ),
                        )
                        Text(text = viewModel.patientDetailsLiveData.value?.height.toString().plus(" cm")?: "",
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = FontFamily(Font(R.font.fonts)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF04385F),
                            )
                        )
                    }
                    Column {
                        Text(
                            text = stringResource(id = R.string.weight),
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = FontFamily(Font(R.font.fonts)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF04385F),
                                textDecoration = TextDecoration.Underline,
                            ),
                        )
                        Text(
                            text = viewModel.patientDetailsLiveData.value?.weight.toString().plus(" kg")?: "",
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = FontFamily(Font(R.font.fonts)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF04385F),
                            )
                        )
                    }
                    Column {
                        Text(
                            text = stringResource(id = R.string.dob),
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = FontFamily(Font(R.font.fonts)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF04385F),
                                textDecoration = TextDecoration.Underline,
                            ),
                        )
                        Text(
                            text = DateTimeHandler.epochSecondsToDateTime(viewModel.patientDetailsLiveData.value?.dob?: 0).substring(0, 10),
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = FontFamily(Font(R.font.fonts)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF04385F),
                            )
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        InfoContainer {
            Column(modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 3.dp)) {
                Text(
                    text = stringResource(id = R.string.test_results),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.fonts)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF04385F),
                        textDecoration = TextDecoration.Underline,
                    ),
                )

                Items(items = viewModel.patientTests.value)
            }
        }
        Spacer(modifier = Modifier.height(1.dp))
    }
}

@Composable
fun ItemContent(item: TestGetResponse){

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.name ?: "",
            style = TextStyle(
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.fonts)),
                fontWeight = FontWeight(700),
                color = Color(0xFF04385F),
            ),
        )
        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFF04385F),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(vertical = 2.dp, horizontal = 4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = item.result ?: "Not completed",
                modifier = Modifier.padding(2.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.fonts)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF04385F),
                ),
            )

    }



}
@Composable
fun Items(items: List<TestGetResponse>?) {
    LazyColumn {
        items?.let { itemList ->
            items(itemList.size) { index ->
                ItemContent(itemList[index])
            }
        }
    }
}

@Composable
fun InfoContainer(content: @Composable () -> Unit) {
    Surface(modifier = Modifier
        .padding(horizontal = 12.dp)
        .fillMaxWidth(),
        color = colorResource(id = R.color.info_container_bg).copy(alpha = 0.9f),
        content = content)
}