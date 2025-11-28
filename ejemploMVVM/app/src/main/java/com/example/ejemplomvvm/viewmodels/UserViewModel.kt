package com.example.ejemplomvvm.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ejemplomvvm.dal.model.User
import com.example.ejemplomvvm.dal.model.UserRepository
import androidx.compose.runtime.State

class UserViewModel : ViewModel() {
    private val _repo = UserRepository
    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> get() = _users

    init {
        loadUsers()
    }

    private fun loadUsers() {
        _users.value = _repo.getUsers()
    }
    fun insertUser(user:User){
        _repo.insert(user)
        loadUsers()
    }
}
