/**
  * Copyright 2021 bejson.com 
  */
package com.happy.video.vo.debankapi;

import lombok.Data;

@Data
public class Pay_token {

    private String id;
    private String chain;
    private String name;
    private String symbol;
    private String display_symbol;
    private String optimized_symbol;
    private int decimals;
    private String logo_url;
    private String protocol_id;
    private double price;
    private boolean is_verified;
    private boolean is_core;
    private boolean is_wallet;
    private long time_at;
    private double amount;
}