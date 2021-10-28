package com.assignment;

import java.io.Serializable;
/*Create a class as the data template*/
public class TradeCell implements Serializable {
    /*constructor to initialize the object*/
    public TradeCell(String symbol, String baseAsset, String quoteAsset, double priceChange, double priceChangePercent, double weightedAvgPrice, double prevClosePrice, double lastPrice, double lastQty, double bidPrice, double bidQty, double askPrice, double askQty, double openPrice, double highPrice, double lowPrice, double volume, double quoteVolume, double openTime, double closeTime, double firstId, double lastId, double count) {
        this.symbol = symbol;
        this.baseAsset = baseAsset;
        this.quoteAsset = quoteAsset;
        this.priceChange = priceChange;
        this.priceChangePercent = priceChangePercent;
        this.weightedAvgPrice = weightedAvgPrice;
        this.prevClosePrice = prevClosePrice;
        this.lastPrice = lastPrice;
        this.lastQty = lastQty;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
        this.quoteVolume = quoteVolume;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.firstId = firstId;
        this.lastId = lastId;
        this.count = count;
    }
    public String symbol;
    public String baseAsset;
    public String quoteAsset;
    public double priceChange;
    public double priceChangePercent;
    public double weightedAvgPrice;
    public double prevClosePrice;
    public double lastPrice;
    public double lastQty;
    public double bidPrice;
    public double bidQty;
    public double askPrice;
    public double askQty;
    public double openPrice;
    public double highPrice;
    public double lowPrice;
    public double volume;
    public double quoteVolume;
    public double openTime;
    public double closeTime;
    public double firstId;
    public double lastId;
    public double count;

    /*show the object content*/
    public void show(){
        System.out.println("symbol,baseAsset,quoteAsset,priceChange,priceChangePercent," +
                "weightedAvgPrice,prevClosePrice,lastPrice,lastQty,bidPrice,bidQty,askPrice," +
                "askQty,openPrice,highPrice,lowPrice,volume,quoteVolume,openTime,closeTime,firstId," +
                "lastId,count");
        System.out.println(this.symbol + "," +
                this.baseAsset + "," +
                this.quoteAsset + "," +
                this.priceChange + "," +
                this.priceChangePercent + "," +
                this.weightedAvgPrice + "," +
                this.lastPrice + "," +
                this.lastQty + "," +
                this.bidPrice + "," +
                this.askPrice + "," +
                this.askQty + "," +
                this.openPrice + "," +
                this.highPrice + "," +
                this.lowPrice + "," +
                this.volume + "," +
                this.quoteVolume + "," +
                this.openTime + "," +
                this.closeTime + "," +
                this.firstId + "," +
                this.lastId + "," +
                this.count + ","
        );
    }
}
