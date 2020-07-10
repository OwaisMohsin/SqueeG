package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user5 on 12-01-2018.
 */

public class GETPaymentGatewayKeys {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("primary")
    @Expose
    private List<Primary> primary = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Primary> getPrimary() {
        return primary;
    }

    public void setPrimary(List<Primary> primary) {
        this.primary = primary;
    }

    public class Primary {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("gateway_name")
        @Expose
        private String gatewayName;
        @SerializedName("gateway_type")
        @Expose
        private String gatewayType;
        @SerializedName("api_key")
        @Expose
        private String apiKey;
        @SerializedName("value")
        @Expose
        private String value;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_dt")
        @Expose
        private String createdDt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGatewayName() {
            return gatewayName;
        }

        public void setGatewayName(String gatewayName) {
            this.gatewayName = gatewayName;
        }

        public String getGatewayType() {
            return gatewayType;
        }

        public void setGatewayType(String gatewayType) {
            this.gatewayType = gatewayType;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedDt() {
            return createdDt;
        }

        public void setCreatedDt(String createdDt) {
            this.createdDt = createdDt;
        }

    }

}
