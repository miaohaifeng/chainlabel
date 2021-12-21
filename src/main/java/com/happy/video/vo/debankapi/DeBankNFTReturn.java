
package com.happy.video.vo.debankapi;
import lombok.Data;

import java.util.Date;

@Data
public class DeBankNFTReturn {

    private String id;
    private String contract_id;
    private String inner_id;
    private String chain;
    private Date name;
    private String description;
    private String content_type;
    private String content;
    private int total_supply;
    private String detail_url;
    private String contract_name;
    private boolean is_erc721;
    private int amount;
    private Pay_token pay_token;
    private double usd_price;

}