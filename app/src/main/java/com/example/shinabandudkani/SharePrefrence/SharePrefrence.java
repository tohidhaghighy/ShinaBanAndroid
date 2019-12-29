package com.example.shinabandudkani.SharePrefrence;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shinabandudkani.DomainLayer.SaveSetting;

public class SharePrefrence  {

        private static final String USER_SHARED_PREF_NAME = "user_shared_pref";
        // if lang=0 english if lang=1 arabic if lang=2 kurdish
        private static final String KEY_Lang = "lang";
        private static final String KEY_First_Time = "first";
        private static final String KEY_City = "city";
        private SharedPreferences sharedPreferences;

        public SharePrefrence(Context context) {
            sharedPreferences = context.getSharedPreferences(USER_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        }

        public void saveUser(SaveSetting user) {
            if (user != null) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_Lang, user.getLanguage().toString());
                editor.putInt(KEY_First_Time, user.getFirsttime());
                editor.putInt(KEY_City,user.getCityId());
                editor.apply();
            }
        }

        public SaveSetting getUser() {
            SaveSetting user = new SaveSetting();
            user.setLanguage(sharedPreferences.getString(KEY_Lang, ""));
            user.setFirsttime(sharedPreferences.getInt(KEY_First_Time, 0));
            user.setCityId(sharedPreferences.getInt(KEY_City, 0));
            return user;
        }
}

