package com.hashinology.todoapp.repo

import com.hashinology.todoapp.data.ToDoDao
import com.hashinology.todoapp.data.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ToDoRepo @Inject constructor(private val toDoDao: ToDoDao) {
    val getAllTasks: Flow<List<ToDoTask>> = toDoDao.getAll()
    val sortByLowPriority: Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<ToDoTask>{
        return toDoDao.getSelectedTask(taskId = taskId)
    }

    suspend fun addTask(toDoTask: ToDoTask){
        toDoDao.addTask(toDoTask)
    }
    suspend fun updateTask(toDoTask: ToDoTask){
        toDoDao.updateTask(toDoTask)
    }
    suspend fun deleteTask(toDoTask: ToDoTask){
        toDoDao.deleteTask(toDoTask)
    }

    suspend fun deleteAllTask(){
        toDoDao.deleteAllTasks()
    }

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>>{
        return toDoDao.searchDatabase(searchQuery = searchQuery)
    }
}