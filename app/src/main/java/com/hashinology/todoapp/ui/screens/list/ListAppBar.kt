@file:OptIn(ExperimentalMaterial3Api::class)

package com.hashinology.todoapp.ui.screens.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.hashinology.todoapp.R
import com.hashinology.todoapp.component.PriorityItem
import com.hashinology.todoapp.data.models.Priority
import com.hashinology.todoapp.ui.theme.LARGE_PADDING
import com.hashinology.todoapp.ui.theme.ToDoAppTheme
import com.hashinology.todoapp.ui.viewmodels.SharedViewModel
import com.hashinology.todoapp.util.Action
import com.hashinology.todoapp.util.SearchAppBarState
import com.hashinology.todoapp.util.TrailingIconState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    /*
    when(sharedViewModel.searchAppBarState.value){}
     */
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                },
                onSortClicked = {},
                onDeleteAllClicked = {
                    sharedViewModel.action.value = Action.DELETE_ALL
                }
            )
        }

        else -> {
            SearchAppbar(
                text = searchTextState,
                onTextChange = { newText ->
                    sharedViewModel.searchTextState.value = newText
                },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {
                    sharedViewModel.searchDatabase(searchQuery = it)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.list_screens_title),
                color = MaterialTheme.colorScheme.secondary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteAllClicked = onDeleteAllClicked
            )
        }
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit
) {
    SearchAction(onSearchClicked)
    SortAction(onSortClicked)
    DeleteAllAction(onDeleteAllClicked = onDeleteAllClicked)
}

@Composable
fun SortAction(onSortClicked: (Priority) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(R.drawable.ic_filter),
            contentDescription = stringResource(R.string.sort_action),
            tint = MaterialTheme.colorScheme.secondary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.LOW)
                },
                text = { PriorityItem(Priority.LOW) }
            )
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.HIGH)
                },
                text = { PriorityItem(Priority.HIGH) }
            )
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.NONE)
                },
                text = { PriorityItem(Priority.NONE) }
            )
        }
        /*// Creating otherway of DropMenue + items
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {expanded.value = false}
        ) {
            repeat(3){
                DropdownMenuItem(
                    onClick = {
                        expanded.value = false
                        when(it){
                            0 -> {
                                //Low
                                onSortClicked(Priority.LOW)
                            }
                            1 -> {
                                //Medium
                                onSortClicked(Priority.MEDIUM)
                            }
                            2 -> {
                                //High
                                onSortClicked(Priority.High)
                            }
                        }
                    },
                    text = {
                        PriorityItem(Priority.entries[it])
                    }
                )
            }
        }*/
    }
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(
        onClick = { onSearchClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_action),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun DeleteAllAction(
    onDeleteAllClicked: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_more_vert),
            contentDescription = stringResource(R.string.sort_action),
            tint = MaterialTheme.colorScheme.secondary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    onDeleteAllClicked()
                    expanded = false
                },
                text = {
                    Text(
                        modifier = Modifier.padding(start = LARGE_PADDING),
                        text = stringResource(R.string.delete_all_action),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            )
        }
    }
}

@Composable
fun SearchAppbar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var trailingIconState by remember {
        mutableStateOf(
            TrailingIconState.READY_TO_DELETE
        )
    }
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {
                    onTextChange(it)
                },
                placeholder = {
                    Text(
                        modifier = Modifier.alpha(0.5f),
                        text = stringResource(R.string.search_placeholder),
                        color = Color.White,

                        )
                },
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                ),
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        modifier = Modifier.alpha(0.8f),
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(R.string.search_icon),
                            tint = MaterialTheme.colorScheme.secondary,
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            when(trailingIconState){
                                TrailingIconState.READY_TO_DELETE -> {
                                    onTextChange("")
                                    trailingIconState = TrailingIconState.READY_TO_CLOSEE
                                }
                                TrailingIconState.READY_TO_CLOSEE -> {
                                    if (text.isNotEmpty()){
                                        onTextChange("")
                                    }else{
                                        onCloseClicked()
                                        trailingIconState = TrailingIconState.READY_TO_DELETE
                                    }
                                }
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = stringResource(R.string.close_icon),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearchClicked(text) }
                ),
                colors = TextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.secondary,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
        }
    )
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DefaultListAppBarPreview() {
    ToDoAppTheme {
        DefaultListAppBar(onSearchClicked = {}, onSortClicked = {}, onDeleteAllClicked = {})
    }
}

@Composable
@Preview
fun SearchAppbarPreview() {
    ToDoAppTheme {
        SearchAppbar(text = "", onTextChange = {}, onCloseClicked = {}, onSearchClicked = {})
    }
}