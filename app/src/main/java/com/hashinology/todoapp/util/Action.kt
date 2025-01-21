package com.hashinology.todoapp.util

enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}

fun String?.toAction(): Action{
    return when{
        this == "Add" -> {
            Action.ADD
        }
        this == "Update" -> {
            Action.UPDATE
        }
        this == "Delete" -> {
            Action.DELETE
        }
        this == "Delete All" -> {
            Action.DELETE_ALL
        }
        this == "Undo" -> {
            Action.UNDO
        }
        else -> {
            Action.NO_ACTION
        }
    }
}