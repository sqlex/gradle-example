package me.danwi.sqlex.example

import me.danwi.sqlex.core.DaoFactory
import me.danwi.sqlex.example.dao.Repository
import me.danwi.sqlex.example.dao.RoleDao
import me.danwi.sqlex.example.dao.UserDao

fun main() {
    val factory = DaoFactory(
        "jdbc:mysql://localhost:3306/sqlex",
        "root",
        "1234qwer",
        Repository::class.java
    )
    // 执行迁移任务
    factory.migrate()
    // 比对差异
    factory.check()

    val userDao = factory.getInstance(UserDao::class.java)
    userDao.findAll(10, "123", null).firstOrNull()
    userDao.findAll(10, "123", listOf<String>()).firstOrNull()
    userDao.findAllByRole("123").firstOrNull()
    userDao.findCountsByRole().firstOrNull()
    
    val roleDao = factory.getInstance(RoleDao::class.java)
    roleDao.findAll("管").firstOrNull()
}
