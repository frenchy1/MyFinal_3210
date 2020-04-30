package com.example.myfinal_3210;

import java.util.HashMap;

public class HashMapToString extends HashMap<String, String> {
    String sKey = null;
    HashMapToString(String sKey){ this.sKey = sKey; }
    HashMapToString(){}
    @Override
    public String toString(){
        if(this.sKey != null){
            return this.get(this.sKey);
        }else{
            return super.toString();
        }
    }
}
