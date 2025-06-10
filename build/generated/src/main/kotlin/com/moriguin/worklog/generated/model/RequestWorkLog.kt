package com.moriguin.worklog.generated.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import jakarta.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param date 日付 (YYYY-MM-DD 形式)
 * @param commH 当日のコミュニケーション時間 (時間単位)
 * @param workH 当日のコーディングなどの作業時間 (時間単位)
 * @param memo メモ
 */
data class RequestWorkLog(

    @field:Valid
    @get:Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$")
    @Schema(example = "null", required = true, description = "日付 (YYYY-MM-DD 形式)")
    @get:JsonProperty("date", required = true) val date: java.time.LocalDate,

    @Schema(example = "null", required = true, description = "当日のコミュニケーション時間 (時間単位)")
    @get:JsonProperty("comm_h", required = true) val commH: kotlin.Int,

    @Schema(example = "null", required = true, description = "当日のコーディングなどの作業時間 (時間単位)")
    @get:JsonProperty("work_h", required = true) val workH: kotlin.Int,

    @get:Size(max=500)
    @Schema(example = "null", description = "メモ")
    @get:JsonProperty("memo") val memo: kotlin.String? = null
    ) {

}

