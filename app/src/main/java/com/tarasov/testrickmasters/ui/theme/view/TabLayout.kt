package com.tarasov.testrickmasters.ui.theme.view

//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material3.Tab
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
import com.tarasov.testrickmasters.presentation.MainViewModel
//import com.tarasov.testrickmasters.presentation.TabRow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.tarasov.testrickmasters.presentation.TabRow

@Composable
fun TabLayout(viewModel: MainViewModel) {
    val tabIndex = viewModel.tabIndex.observeAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex.value!!) {
            viewModel.tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex.value!! == index,
                    onClick = { viewModel.updateTabIndex(index) },
                )
            }
        }

        when (tabIndex.value) {
            0 -> ListCameraScreen(viewModel = viewModel)
            1 -> ListDoorScreen(viewModel = viewModel)
        }
    }
}