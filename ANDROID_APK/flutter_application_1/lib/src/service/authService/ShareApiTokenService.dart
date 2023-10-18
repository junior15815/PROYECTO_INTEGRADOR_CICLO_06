import 'dart:convert';

import 'package:api_cache_manager/api_cache_manager.dart';
import 'package:api_cache_manager/models/cache_db_model.dart';
import 'package:flutter/material.dart';
import 'package:flutter_application_1/src/model/auth/AuthResponseModel.dart';

class ShareApiTokenService {
  static Future<bool> isLoggedIn() async {
    var isKeyExist = await APICacheManager().isAPICacheKeyExist("login_details");
    return isKeyExist;
  }

  static Future<AuthResponseModel?> loginDetails () async {
    
    var isKeyExist = await APICacheManager().isAPICacheKeyExist("login_details");
    
    if(isKeyExist){
      var cacheData = await APICacheManager().getCacheData("login_details");

      return authResponseJson(cacheData.syncData);
    }
  }

  static Future<void> setLoginDetails(
    AuthResponseModel model,
  )async{
    APICacheDBModel cacheDBModel = APICacheDBModel(
      key: "login_details",
       syncData: jsonEncode(model.toJson()),
      );

      await APICacheManager().addCacheData(cacheDBModel);
  }

  static Future<void> logout(BuildContext context) async{
    await APICacheManager().deleteCache("login_details");
    Navigator.pushNamedAndRemoveUntil(
      context, 
      '/login', 
      (route) => false
      );
  }



}