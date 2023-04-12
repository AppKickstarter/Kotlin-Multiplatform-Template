package com.lduboscq.appkickstarter.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.lduboscq.appkickstarter.model.Person
import com.lduboscq.appkickstarter.ui.Image

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonDetailScreenContent(person: Person, navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(person.name) },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Rounded.ArrowBack, null)
                    }
                }
            )
        }
    ) {
        Card(
            Modifier
                .fillMaxSize()
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(horizontal = 16.dp)
        ) {
            Row(Modifier.padding(16.dp)) {
                Image(
                    "https://i.pravatar.cc/150?img=${person.id}",
                    Modifier.size(50.dp).clip(CircleShape)
                )
                Spacer(Modifier.width(4.dp))
                Column {
                    Text(person.name)
                    Text(person.role)
                }
            }
        }
    }
}
