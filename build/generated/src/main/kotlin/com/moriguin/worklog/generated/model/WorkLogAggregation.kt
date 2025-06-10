package com.moriguin.worklog.generated.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.moriguin.worklog.generated.model.WorkLog
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
 * @param totalH その月の合計労働時間 (時間単位)
 * @param commH その月の合計コミュニケーション時間 (時間単位)
 * @param workH その月の合計作業時間 (時間単位)
 * @param logs 日ごとのワークログ一覧
 */
data class WorkLogAggregation(

    @Schema(example = "null", required = true, description = "その月の合計労働時間 (時間単位)")
    @get:JsonProperty("total_h", required = true) val totalH: kotlin.Int,

    @Schema(example = "null", required = true, description = "その月の合計コミュニケーション時間 (時間単位)")
    @get:JsonProperty("comm_h", required = true) val commH: kotlin.Int,

    @Schema(example = "null", required = true, description = "その月の合計作業時間 (時間単位)")
    @get:JsonProperty("work_h", required = true) val workH: kotlin.Int,

    @field:Valid
    @Schema(example = "null", required = true, description = "日ごとのワークログ一覧")
    @get:JsonProperty("logs", required = true) val logs: kotlin.collections.List<WorkLog>
    ) {

}

