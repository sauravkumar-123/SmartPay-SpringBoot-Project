package com.smartpay.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;


//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"Status", "Details"})
@Generated("jsonschema2pojo")
public class TwoFactorResponse {

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Details")
    private String details;

    public TwoFactorResponse() {

    }

    public TwoFactorResponse(String status, String details) {
        super();
        this.status = status;
        this.details = details;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("Details")
    public String getDetails() {
        return details;
    }

    @JsonProperty("Details")
    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "TwoFactorResponse [status=" + status + ", details=" + details + "]";
    }

}
