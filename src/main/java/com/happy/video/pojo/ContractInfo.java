package com.happy.video.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContractInfo {
    private String email;
    private String telephone;
    private String facebook;
    private String weibo;
    private String twitter;
    private String telegram;
    private String redditlink;

    public ContractInfo(String email, String telephone) {
        this.email = email;
        this.telephone = telephone;
    }
}
