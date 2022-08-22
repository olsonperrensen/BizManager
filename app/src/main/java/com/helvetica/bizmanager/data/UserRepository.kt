package com.helvetica.bizmanager.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    var readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}