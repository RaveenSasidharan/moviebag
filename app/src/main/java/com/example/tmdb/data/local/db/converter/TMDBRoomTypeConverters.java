package com.example.tmdb.data.local.db.converter;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.tmdb.data.model.ProductionCompany;
import com.example.tmdb.data.model.ProductionCountry;
import com.example.tmdb.data.model.SpokenLanguage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TMDBRoomTypeConverters {

    @TypeConverter
    public static String ProductionCountryToString(List<ProductionCountry> productionCountryList)
    {
        if (productionCountryList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductionCountry>>() {
        }.getType();
        String json = gson.toJson(productionCountryList, type);
        return json;

    }

    @TypeConverter
    public static List<ProductionCountry> StringToProductionCountry(String string)
    {
        if (string == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductionCountry>>() {
        }.getType();
        List<ProductionCountry> productionCountryList = gson.fromJson(string, type);
        return productionCountryList;
    }

    @TypeConverter
    public static String ProductionCompanyToString(List<ProductionCompany> productionCompanyList)
    {
        if (productionCompanyList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductionCompany>>() {
        }.getType();
        String json = gson.toJson(productionCompanyList, type);
        return json;
    }

    @TypeConverter
    public static List<ProductionCompany> StringToProductionCompany(String string)
    {
        if (string == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductionCompany>>() {
        }.getType();
        List<ProductionCompany> productionCompanyList = gson.fromJson(string, type);
        return productionCompanyList;
    }

    @TypeConverter
    public static String SpokenLanguageToString(List<SpokenLanguage> spokenLanguageList)
    {
        if (spokenLanguageList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SpokenLanguage>>() {
        }.getType();
        String json = gson.toJson(spokenLanguageList, type);
        return json;
    }


    @TypeConverter
    public static List<SpokenLanguage> StringToSpokenLanguage(String string)
    {
        if (string == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SpokenLanguage>>() {
        }.getType();
        List<SpokenLanguage> spokenLanguageList = gson.fromJson(string, type);
        return spokenLanguageList;
    }

    @TypeConverter
    public static String IntegerToString(List<Integer> integerList)
    {
        if (integerList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        String json = gson.toJson(integerList, type);
        return json;
    }


    @TypeConverter
    public static List<Integer> StringToIntegerList(String string)
    {
        if (string == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        List<Integer> integerList = gson.fromJson(string, type);
        return integerList;
    }

    @TypeConverter
    public static String StringListToString(List<String> stringList)
    {
        if (stringList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        String json = gson.toJson(stringList, type);
        return json;
    }


    @TypeConverter
    public static List<String> StringToStringList(String string)
    {
        if (string == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> stringList = gson.fromJson(string, type);
        return stringList;
    }
}
