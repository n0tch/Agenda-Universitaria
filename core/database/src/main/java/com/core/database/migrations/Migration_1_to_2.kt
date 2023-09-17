package com.core.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val migration_1_2 = object : Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE notes ADD COLUMN createdAt INTEGER DEFAULT 0")
    }
}