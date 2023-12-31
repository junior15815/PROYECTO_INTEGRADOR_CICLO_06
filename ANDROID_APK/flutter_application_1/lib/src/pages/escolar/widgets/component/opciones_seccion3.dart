import 'package:flutter/material.dart';
import 'package:flutter_application_1/src/pages/escolar/widgets/component/grid/cuadricula_escolar.dart';
import 'package:flutter_application_1/src/pages/escolar/widgets/component/grid/lista_escolar.dart';
import 'package:flutter_application_1/src/pages/escolar/widgets/ui/Detalle_screen_escolar.dart';
import 'package:snippet_coder_utils/hex_color.dart';

class Seccion3Escolar extends StatefulWidget {
  const Seccion3Escolar({Key? key}) : super(key: key);

  @override
  State<Seccion3Escolar> createState() => _Seccion3EscolarState();
}

class _Seccion3EscolarState extends State<Seccion3Escolar> {
  bool isGrid = false; // Controla la visualización inicial

  Widget _buildTitleSection() {
    return Container(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              _buildTitle(),
              _buildIcon(),
            ],
          ),
        ],
      ),
    );
  }

  Widget _buildTitle() {
    return Container(
      padding: EdgeInsets.only(left: 65),
      child: Column(
        children: [
          Text(
            "Courses",
            style: TextStyle(
              color: HexColor("#0e1b4d"),
              fontSize: 22,
              fontWeight: FontWeight.bold,
              letterSpacing: 1,
              wordSpacing: 2,
            ),
          ),
          Container(
            margin: EdgeInsets.only(top: 5),
            width: 70.0,
            color: HexColor('#F82249'),
            height: 3.0,
          ),
        ],
      ),
    );
  }

  Widget _buildIcon() {
    return Container(
      padding: EdgeInsets.only(left: 50),
      child: InkWell(
        onTap: () {
          setState(() {
            // Cambia entre la visualización de cuadrícula y lista
            isGrid = !isGrid;
          });
        },
        child: Icon(isGrid ? Icons.grid_on : Icons.list), // Cambia los íconos según el estado
      ),
    );
  }

  Widget _buildGridView(BuildContext context) {
    return GridViewEscolar();
  }

  Widget _buildListView(BuildContext context) {
    return ListViewEscolar();
  }

  Widget _buildBody(BuildContext context) {
    return isGrid ? _buildGridView(context) : _buildListView(context);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(10),
      child: Column(
        children: [
          _buildTitleSection(),
          SizedBox(height: 50),
          _buildBody(context), // Utiliza la función _buildBody para mostrar la vista adecuada
        ],
      ),
    );
  }
}
