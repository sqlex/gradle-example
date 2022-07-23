package me.danwi.sqlex.example

import me.danwi.sqlex.core.DaoFactory
import me.danwi.sqlex.core.query.*
import me.danwi.sqlex.example.dao.Repository
import me.danwi.sqlex.example.dao.RoleDao
import me.danwi.sqlex.example.dao.User
import me.danwi.sqlex.example.dao.UserDao
import me.danwi.sqlex.example.dao.UserTable
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun main() {
    val factory = DaoFactory(
        "jdbc:mysql://localhost:3306/sqlex?connectionTimeZone=SERVER",
        "root",
        "1234qwer",
        Repository::class.java
    )
    // 执行迁移任务
    factory.migrate()
    // 比对差异
    factory.check()

    val userTable = factory.getInstance(UserTable::class.java)
    //删除所有用户
    userTable.delete().where(true.arg).execute()
    //插入用户
    val user1 = User.forInsert("fake id", "fake name")
    userTable.insertWithoutNull(user1)
    //查找
    val user2 = userTable.findById("fake id")!!
    assert(user1.id == user2.id)
    assert(user1.name == user2.name)
    //更新
    userTable.update().setAge(1).where(UserTable.Id eq "fake id").execute()
    val user3 = userTable.findById("fake id")!!
    assert(user3.age == 1)
    //删除
    userTable.delete().where(UserTable.Id eq "fake id").execute()
    val user4 = userTable.findById("fake id")
    assert(user4 == null)
    //保存并返回
    val user5 = userTable.save(User.forInsert("fake id", "fake name"))!!
    assert(user5.age == null)
    //保存或更新
    user5.age = 1
    user5.createAt = OffsetDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)
    val user6 = userTable.saveOrUpdate(user5)!!
    user6.createAt = user6.createAt.atZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime()
    assert(user5 == user6)

    //sqlm查询
    val userDao = factory.getInstance(UserDao::class.java)
    userDao.findAll(10, "123", null).firstOrNull()
    userDao.findAll(10, "123", listOf<String>()).firstOrNull()
    userDao.findAllByRole("123").firstOrNull()
    userDao.findCountsByRole().firstOrNull()

    val roleDao = factory.getInstance(RoleDao::class.java)
    roleDao.findAll("管").firstOrNull()
}
