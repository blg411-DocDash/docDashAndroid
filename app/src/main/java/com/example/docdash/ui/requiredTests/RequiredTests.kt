package com.example.docdash.ui.requiredTests

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.docdash.ui.taskDetails.TaskDetailsActivity
import com.example.docdash.ui.taskDetails.TaskDetailsViewModel


@Composable
fun RequiredTests(viewModel: RequiredTestsViewModel) {
    val context = LocalContext.current

    val taskDetailsIntent = Intent(context, TaskDetailsActivity::class.java)

    val startActivity: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()) { }

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
            TaskDetails()
            TestContainer(viewModel)
        }

        Button(
            onClick = {
                startActivity.launch(taskDetailsIntent)
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
fun TaskDetails(){
    val style = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.fonts)),
        fontWeight = FontWeight(700),
        color = colorResource(id = R.color.dark_blue)
    )
    InfoContainer {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Row (
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row{
                    Text(
                        text = stringResource(R.string.task_number),
                        style = style
                    )
                    Text(
                        text = stringResource(R.string.dummy_number),
                        style = style
                    )
                }
                Spacer(modifier = Modifier.width(80.dp))
                Row{
                    Text(
                        text = stringResource(R.string.task_due),
                        style = style
                    )
                    Text(
                        text = stringResource(R.string.dummy_date),
                        style = style
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row{
                    Text(
                        text = stringResource(R.string.patient_title),
                        style = style
                    )
                    Text(
                        text = stringResource(R.string.dummy_text_short),
                        style = style
                    )
                }
                Spacer(modifier = Modifier.width(85.dp))
                Row {
                    Text(
                        text = stringResource(R.string.patient_room_title),
                        style = style
                    )
                    Text(
                        text = stringResource(R.string.dummy_number),
                        style = style
                    )
                }
            }
        }
    }

}


@Composable
fun TestContainer(viewModel: RequiredTestsViewModel){
    val tempTestNos = listOf(
        "2144",
        "2145",
        "1013",
        "1012",
        "2144",
        "2145",
        "1013",
        "1012"
    )
    val testNamesPerNo = mapOf(
        "2144" to "CRP",
        "2145" to "Blood Pressure",
        "1013" to "Whole Blood Count",
        "1012" to "Temperature",
        "2144" to "CRP",
        "2145" to "Blood Pressure",
        "1013" to "Whole Blood Count",
        "1012" to "Temperature",
    )
    val testInformationPerNo = mapOf(
        "2144" to "CRP",
        "2145" to "Blood Pressure",
        "1013" to "Whole Blood Count",
        "1012" to "Temperature",
        "2144" to "CRP",
        "2145" to "Blood Pressure",
        "1013" to "Whole Blood Count",
        "1012" to "Temperature",
    )

    LazyColumn(
    ) {
        items(tempTestNos) { item ->
                TestBox(id = item,
                        name = testNamesPerNo[item],
                        information = stringResource(id = R.string.dummy_text))
            }

    }

}

@Composable
fun TestBox(id: String?, name: String?, information: String?){
    val style = TextStyle(
        fontSize = 17.sp,
        fontFamily = FontFamily(Font(R.font.fonts)),
        fontWeight = FontWeight(700),
        color = colorResource(id = R.color.dark_blue),
    )

    InfoContainer {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ){
            Row {
                Text(
                    text = stringResource(id = R.string.test_number),
                    style = style
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = id ?: "0",
                    style = style
                )
            }
            Text(
                text = name?:"",
                style = style
            )
            Text(
                text = information?:"",
                style = style
            )
            EditableTextField()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableTextField(){
    val context = stringResource(id = R.string.write_results)
    var text by remember { mutableStateOf(context)}

    TextField(
        value = text,
        onValueChange = {text = it},
        textStyle = TextStyle(
            fontSize = 17.sp,
            fontFamily = FontFamily(Font(R.font.fonts)),
            fontWeight = FontWeight(700),
            color = colorResource(id = R.color.dark_blue),
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 5.dp),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colorResource(id = R.color.app_box_gold)
        )

    )
}
@Composable
fun InfoContainer(content: @Composable () -> Unit) {
    Surface(modifier = Modifier
        .padding(vertical = 10.dp, horizontal = 15.dp)
        .fillMaxWidth(),
        color = colorResource(id = R.color.info_container_bg).copy(alpha = 0.9f),
        content = content)
}