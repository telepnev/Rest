package com.imgur.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonPropertyOrder({
        "data",
        "success",
        "status"
})
@NoArgsConstructor
@Data
public class CommonResponse <AnyData> {

    @JsonProperty("data")
    private AnyData accountData;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("status")
    private Integer status;
}
