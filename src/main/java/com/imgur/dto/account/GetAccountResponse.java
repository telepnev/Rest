package com.imgur.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imgur.dto.CommonResponse;
import lombok.Data;


public class GetAccountResponse extends CommonResponse<GetAccountResponse.AccountData> {


    @Data
    public static class AccountData {

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("url")
        private String url;
        @JsonProperty("bio")
        private String bio;
        @JsonProperty("avatar")
        private String avatar;
        @JsonProperty("avatar_name")
        private String avatarName;
        @JsonProperty("cover")
        private String cover;
        @JsonProperty("cover_name")
        private String coverName;
        @JsonProperty("reputation")
        private Integer reputation;
        @JsonProperty("reputation_name")
        private String reputationName;
        @JsonProperty("created")
        private Integer created;
        @JsonProperty("pro_expiration")
        private Boolean proExpiration;
        @JsonProperty("user_follow")
        private UserFollow userFollow;
        @JsonProperty("is_blocked")
        private Boolean isBlocked;
    }

    @Data
    public static class UserFollow {
        @JsonProperty("status")
        public Boolean status;

    }
}