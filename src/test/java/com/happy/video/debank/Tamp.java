package com.happy.video.debank;

public class Tamp {
    static String tmp = "项目\n" +
            "PancakeSwap\n" +
            "Venus\n" +
            "MDEX\n" +
            "Ellipsis\n" +
            "CoinWind\n" +
            "Autofarm\n" +
            "Alpaca Finance\n" +
            "Biswap\n" +
            "Belt Finance\n" +
            "Pancake Bunny\n" +
            "Beefy.Finance\n" +
            "Wault Finance\n" +
            "ApeSwap\n" +
            "BTCST\n" +
            "Nerve\n" +
            "BakerySwap\n" +
            "ForTube\n" +
            "DODO\n" +
            "CREAM\n" +
            "Nominex\n" +
            "Linear\n" +
            "Rabbit Finance\n" +
            "dForce\n" +
            "Solo.top\n" +
            "MOBOX\n" +
            "Swamp.finance\n" +
            "Tranchess\n" +
            "1inch.exchange\n" +
            "BabySwap\n" +
            "Warden Swap\n" +
            "Bunicorn\n" +
            "Definix\n" +
            "ACryptoS\n" +
            "WePiggy\n" +
            "My DeFi Pet\n" +
            "Cross Pool\n" +
            "PureSwap\n" +
            "bZx\n" +
            "Kalata\n" +
            "Qubit\n" +
            "Annex Finance\n" +
            "Alpha Homora\n" +
            "AutoShark Finance\n" +
            "Bscex LaunchpoolX\n" +
            "Pancake Hunny\n" +
            "Moonpot\n" +
            "Cashcow Finance\n" +
            "BinaryX\n" +
            "Feeder Finance\n" +
            "Dopple Finance";

    public static void main(String[] args) {
        String[] split = tmp.split("\n");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i].replace(" "," "));;
        }
    }
}
