package com.happy.video.toolbox;

public enum DeBankDataTypeEnum {
    tvl("锁仓量", "tvl"),
    borrow("借款总量", "borrow"),
    trade_volume("交易量", "trade_volume"),
    trade_count("交易次数", "trade_count"),
    trade_user("交易用户数", "trade_user"),
    liquidate("清算量", "liquidate"),
    oracle_call("语言机调用次数", "oracle_call"),
    oracle_contract("预言机调用合约数", "oracle_contract"),
    contract_call("合约交互数", "contract_call"),
    contract_user("交互用户数", "contract_user");



    private String  name;
    private String code;
    DeBankDataTypeEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
