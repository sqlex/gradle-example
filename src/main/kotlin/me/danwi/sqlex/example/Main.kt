package me.danwi.sqlex.example

import me.danwi.sqlex.core.DataSourceManager
import me.danwi.sqlex.example.dao.Repository
import me.danwi.sqlex.example.dao.RoleDao
import me.danwi.sqlex.example.dao.UserDao

fun main() {
    val dataSourceManager = DataSourceManager()
    val dataSource = dataSourceManager.getInstance(Repository::class.java)
    val userDao = dataSource.getInstance(UserDao::class.java)
    val roleDao = dataSource.getInstance(RoleDao::class.java)

    userDao.getAll(10, "123", null).firstOrNull()
    userDao.getAll(10, "123", listOf<String>()).firstOrNull()
    userDao.getAllByRole("123").firstOrNull()
    userDao.countsByRole.firstOrNull()

    roleDao.getAll("管").firstOrNull()
}
