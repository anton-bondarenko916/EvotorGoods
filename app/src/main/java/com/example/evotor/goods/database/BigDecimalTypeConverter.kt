package com.example.evotor.goods.database

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.math.RoundingMode

class BigDecimalTypeConverter {

    @TypeConverter
    fun fromBigDecimal(number: BigDecimal?): Double {
        return number?.setScale(2)?.toDouble() ?: 0.0
    }

    @TypeConverter
    fun toBigDecimal(number: Double?): BigDecimal {
        return if (number == null) {
            BigDecimal.ZERO
        } else {
            BigDecimal(number).setScale(2, RoundingMode.CEILING)
        }
    }
}