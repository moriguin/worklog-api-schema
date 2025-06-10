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
 * @param code 
 * @param message 
 */
data class ApiError(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("code", required = true) val code: kotlin.Int,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("message", required = true) val message: kotlin.String
    ) {

}

