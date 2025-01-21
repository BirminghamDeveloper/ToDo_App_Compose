package com.hashinology.todoapp.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashinology.todoapp.data.models.Priority
import com.hashinology.todoapp.data.models.ToDoTask
import com.hashinology.todoapp.repo.ToDoRepo
import com.hashinology.todoapp.util.Action
import com.hashinology.todoapp.util.Constants
import com.hashinology.todoapp.util.Constants.MAX_TITLE_LENGTH
import com.hashinology.todoapp.util.RequestState
import com.hashinology.todoapp.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repo: ToDoRepo
): ViewModel() {
    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)

    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks
//    val allTasks = _allTasks.asStateFlow()

    fun getAllTasks(){
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repo.getAllTasks.collect{
                    _allTasks.value = RequestState.Success(it)
                }
            }
        }catch (e: Exception){
            _allTasks.value = RequestState.Error(e)
        }
    }

    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask

    fun getSelectedTask(taskId: Int){
        viewModelScope.launch {
            repo.getSelectedTask(taskId = taskId).collect{task ->
                _selectedTask.value = task
            }
        }
    }

    private fun addTask(){
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repo.addTask(toDoTask)
        }
    }

    fun handleDatabaseActions(action: Action){
        when(action){
            Action.ADD -> {
                addTask()
            }
            Action.UPDATE -> {

            }
            Action.DELETE -> {

            }
            Action.DELETE_ALL -> {

            }
            Action.UNDO -> {

            }
            else -> {

            }
        }
        this.action.value = Action.NO_ACTION
    }

    fun updateTaskFields(selectedTask: ToDoTask?){
        if (selectedTask != null){
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        }else{
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun updateTitle(newTitle: String){
        if(newTitle.length < MAX_TITLE_LENGTH){
            title.value = newTitle
        }
    }

    fun validateFields(): Boolean{
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}