package com.example.docdash.ui.patientDetails

import android.icu.text.IDNA.Info
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
        .fillMaxSize()) {
        HeaderRow()
        OuterContainer()
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
        .padding(horizontal = 22.dp, vertical = 28.dp)
        .background(color = colorResource(R.color.container_bg))
        .fillMaxSize()) {
        Spacer(modifier = Modifier.height(10.dp))
        InfoContainer {
            Text(
                text = "Name \nMary Copper",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.fonts)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF04385F),
                    textDecoration = TextDecoration.Underline,
                ),
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 3.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        InfoContainer {
            Text(
                text = "Room \nZ02",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.fonts)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF04385F),
                    textDecoration = TextDecoration.Underline,
                ),
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 3.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun InfoContainer(content: @Composable RowScope.() -> Unit) {
    Surface(modifier = Modifier
        .padding(horizontal = 12.dp)
        .background(color = colorResource(id = R.color.info_container_bg))
        .fillMaxSize()) {
        content
    }
}