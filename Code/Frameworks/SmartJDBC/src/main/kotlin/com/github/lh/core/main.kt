package com.github.lh.core

import java.sql.DriverManager

/**
 *
 * @author <a href="mailto: 393803588@qq.com">刘涵(Hanl)</a>
 * By 2017/12/26
 */
fun main(args: Array<String>) {
    DriverManager.getConnection(
            "jdbc:mysql://192.168.3.134:3306/test",
            "liuhan",
            "liuhan"
    ).use {
        it.prepareStatement("select * from page_handle")
                .executeQuery().apply {
            val columns = (1 until this.metaData.columnCount).map { this.metaData.getColumnName(it) }
            println(columns)
            val dataArr = arrayListOf<Any>()
            while (this.next()) {
                dataArr.add(columns.map { this.getObject(it) })
            }
        }
    }
}

class RowData {
    lateinit var data: Array<Any>

    fun fillData(data: Array<Any>) {
        this.data = data
    }
}