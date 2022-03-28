package me.danwi.sqlex.example

import me.danwi.sqlex.core.DatabaseManager
import me.danwi.sqlex.example.dao.RoleDao
import me.danwi.sqlex.example.dao.UserDao

fun main() {
    val databaseManager = DatabaseManager()
    val userDao = databaseManager.getInstance(UserDao::class.java)
    val roleDao = databaseManager.getInstance(RoleDao::class.java)

    userDao.getAll(10, "123", null).firstOrNull()
    userDao.getAll(10, "123", listOf<String>()).firstOrNull()
    userDao.getAllByRole("123").firstOrNull()
    userDao.countsByRole.firstOrNull()

    roleDao.getAll("管").firstOrNull()
}
