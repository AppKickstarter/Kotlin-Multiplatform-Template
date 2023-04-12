package com.lduboscq.appkickstarter.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
fun ListScreenContent(
    persons: List<Person>,
    navigateToDetail: (Person) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("KMP Starter OS")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            Modifier
                .padding(paddingValues)
                .padding(32.dp)
        ) {
            items(persons) { person ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        "https://i.pravatar.cc/150?img=${person.id}",
                        Modifier.size(50.dp).clip(CircleShape)
                    )
                    Spacer(Modifier.width(4.dp))
                    TextButton({
                        navigateToDetail(person)
                    }) {
                        Text("See more info about ${person.name}")
                    }
                }
            }
        }
    }
}
