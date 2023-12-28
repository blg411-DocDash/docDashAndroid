package com.example.docdash.ui.patientDetails

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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.docdash.R

@Preview
@Composable
fun PatientDetails() {
    Column(modifier = Modifier
        .background(color = colorResource(R.color.background))
        .padding(bottom=20.dp)
        .fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            HeaderRow()
            OuterContainer()
        }

        Button(
            onClick = { /*TODO*/ },
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
fun OuterContainer() {
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
                Text(text = stringResource(id = R.string.dummy_text),
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
                Text(text = stringResource(id = R.string.dummy_text_short),
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
                Text(text = stringResource(id = R.string.dummy_text_short),
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
                        Text(text = "1.72cm",
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
                        Text(text = "72kg",
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
                            text = stringResource(id = R.string.age),
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = FontFamily(Font(R.font.fonts)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF04385F),
                                textDecoration = TextDecoration.Underline,
                            ),
                        )
                        Text(text = stringResource(id = R.string.dummy_number),
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
        val tempTestDates = listOf(
            "04.12.2023",
            "03.12.2023",
            "03.12.2023"
        )
        val testResultsPerDate = mapOf(
            "04.12.2023" to listOf("CRP", "Whole Blood Count"),
            "03.12.2023" to listOf("Blood Pressure", "Temperature"),
            "03.12.2023" to listOf("Blood Pressure", "Temperature"),
        )
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
                LazyColumn(
                ) {
                    items(tempTestDates) { item ->
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = item,
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = FontFamily(Font(R.font.fonts)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF04385F),
                            ),)
                        for (value in testResultsPerDate[item]!!) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFF04385F),
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .padding(vertical = 2.dp, horizontal = 4.dp)
                                .fillMaxWidth()
                            ) {
                                Text(text = value,
                                    modifier = Modifier.padding(2.dp),
                                    style = TextStyle(
                                        fontSize = 17.sp,
                                        fontFamily = FontFamily(Font(R.font.fonts)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF04385F),
                                    ),)
                            }
                        }
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(1.dp))
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