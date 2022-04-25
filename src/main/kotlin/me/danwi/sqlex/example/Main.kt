package me.danwi.sqlex.example

import me.danwi.sqlex.core.DaoFactory
import me.danwi.sqlex.example.dao.Repository


fun main() {
    val factory = DaoFactory("jdbc:mysql://localhost:3306/sqlex", "root", "1234qwer", Repository::class.java)
    factory.check()
}
