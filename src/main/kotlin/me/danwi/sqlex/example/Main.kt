package me.danwi.sqlex.example

import me.danwi.sqlex.core.DaoFactory
import me.danwi.sqlex.core.exception.SqlExCheckException
import me.danwi.sqlex.example.dao.Repository


fun main() {
    val factory = DaoFactory(
        "jdbc:mysql://localhost:3306/sqlex?tinyInt1isBit=false&yearIsDateType=true",
        "root",
        "1234qwer",
        Repository::class.java
    )
    try {
        factory.check()
    } catch (ex: SqlExCheckException) {
        ex.tables.forEach { t ->
            t.columns.forEach { f ->
                println("数据库异常: ${t.name} - ${f.name} - ${f.typeName}")
            }
        }
    }
}
