import 'package:flutter/material.dart';
import 'package:flutter_application_1/src/pages/libros/pages/ui/widgets/favoritos/CartAppBar.dart';
import 'package:flutter_application_1/src/pages/libros/pages/ui/widgets/favoritos/ItemFavoritosW.dart';

class Favoritos extends StatelessWidget {
  const Favoritos({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
        children: [
          CartAppBar(),
          Container(
            // height: 700,
            padding:EdgeInsets.only(top:15),
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.only(
                topRight: Radius.circular(35),
                topLeft: Radius.circular(35)
              )
            ),
            child: Column(
              children: [
                ItemFavoritosW(),
              ],
            ),
          )
        ],
      ),
    );
  }
}