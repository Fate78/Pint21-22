package com.pint.roombookerfinal.Models;

import com.google.gson.annotations.SerializedName;

public class Authenticate {
    @SerializedName("UtilizadorInput")
    public String utilizadorInput;

    @SerializedName("PalavraPasse")
    public String palavraPasse;

    public Authenticate(String utilizadorInput, String palavraPasse){
        this.utilizadorInput = utilizadorInput;
        this.palavraPasse = palavraPasse;
    }
}
