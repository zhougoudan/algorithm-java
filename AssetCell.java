package com.assignment;

import java.io.Serializable;
/*Create a class as the data template*/

public class AssetCell implements Serializable {
    public int rank;
    public String name;
    public String symbol;
    public double markeyCapCleaned;
    public double circulating;
    public String _1h;
    public String _24h;
    public String _7d;

    /*constructor to initialize the object*/
    public AssetCell(int rank, String name, String symbol, double markeyCapCleaned, double circulating, String _1h, String _24h, String _7d) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.markeyCapCleaned = markeyCapCleaned;
        this.circulating = circulating;
        this._1h = _1h;
        this._24h = _24h;
        this._7d = _7d;
    }
    /*show the object content*/
    public void show(){
        System.out.println("Rank,Name,Symbol,Markey Cap (cleaned),Circulating (cleaned),% 1h,% 24h,% 7d");
        System.out.println(this.rank + ", " +this.name + ", " +this.symbol + ", " +this.markeyCapCleaned + ", " +this.circulating + ", " + this._1h + ", " + this._24h + ", " +this._7d);
    }
}
