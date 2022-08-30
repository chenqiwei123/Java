package com.example.demo.Lock;

import lombok.Getter;

public enum CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"),THREE(3,"燕"),Four(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");
    @Getter
    private Integer id;
    @Getter
    private String Name;

    CountryEnum(Integer id, String name) {
        this.id = id;
        Name = name;
    }
    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] countryEnums=CountryEnum.values();
        for (CountryEnum anEnum : countryEnums) {
            if (anEnum.getId()==index){
                return anEnum;
            }
        }
        return null;
    }
}
