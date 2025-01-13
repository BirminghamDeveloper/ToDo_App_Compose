package com.hashinology.todoapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashinology.todoapp.data.models.ToDoTask
import com.hashinology.todoapp.repo.ToDoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repo: ToDoRepo
): ViewModel() {
    private val _allTasks = MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTasks: StateFlow<List<ToDoTask>> = _allTasks
//    val allTasks = _allTasks.asStateFlow()

    fun getAllTasks(){
        viewModelScope.launch {
            repo.getAllTasks.collect{
                _allTasks.value = it
            }
        }
    }
}