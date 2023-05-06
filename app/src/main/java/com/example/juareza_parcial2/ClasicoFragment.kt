package com.example.juareza_parcial2

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ClasicoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClasicoFragment : Fragment()
{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val Vista: View = inflater.inflate(R.layout.fragment_clasico, container, false)
        //val imgTijeraPapel = Vista.findViewById<ImageView>(R.id.img_TijeraPapel)

        Vista.findViewById<TextView>(R.id.lbl_Resultado).visibility = View.INVISIBLE
        Vista.findViewById<ImageView>(R.id.img_TijeraPapel).rotation = 180.0F
        Vista.findViewById<ImageView>(R.id.img_PapelPiedra).rotation = 315.0F
        Vista.findViewById<ImageView>(R.id.img_PiedraTijera).rotation = 45.0F

        val btnPiedra = Vista.findViewById<ImageButton>(R.id.btnPiedra)
        val btnPapel = Vista.findViewById<ImageButton>(R.id.btnPapel)
        val btnTijera = Vista.findViewById<ImageButton>(R.id.btnTijera)

        btnPiedra.setOnClickListener {
            Juegote(0, Vista)   }

        btnPapel.setOnClickListener {
            Juegote(1, Vista)   }

        btnTijera.setOnClickListener {
            Juegote(2, Vista)   }

        return Vista
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ClasicoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ClasicoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun Juegote (Jugador:Int, Vista:View)
    {
        val lblResultado = Vista.findViewById<TextView>(R.id.lbl_Resultado)

        val imgBot = Vista.findViewById<ImageView>(R.id.img_EleccionBot)
        val imgJug = Vista.findViewById<ImageView>(R.id.img_EleccionJug)

        /*
            0 = PIEDRA
            1 = PAPEL
            2 = TIJERA
        */
        var Botsote = (0..2).random() //Genera un número aleatorio del 0 al 2

        //SECCIÓN PARA PONER LA IMAGEN DE LA ELECCIÓN
        PonerImagenPorqueRepetirloDosVecesYaMeMolestaPonerloEnCodigo(imgBot,Botsote)
        PonerImagenPorqueRepetirloDosVecesYaMeMolestaPonerloEnCodigo(imgJug,Jugador)

        //RESULTADOS
        if(Jugador == Botsote) //Empate
        {
            lblResultado.setTextColor(Color.DKGRAY)
            lblResultado.text = "Empate"
        }
        else if ((Jugador == 0 && Botsote == 2)||(Jugador==1&&Botsote==0)||(Jugador==2&&Botsote==1)) //Cuando el jugador gana
        {
            lblResultado.setTextColor(Color.RED)
            lblResultado.text = "¡Has ganao!\nFelicidades, eres muy bueno"
        }
        else
        {
            lblResultado.setTextColor(Color.BLUE)
            lblResultado.text = "¡Has perdío!\nAhí pa' la otra"
        }

        lblResultado.textSize=28F
        lblResultado.textAlignment = View.TEXT_ALIGNMENT_CENTER
        lblResultado.visibility = View.VISIBLE
    }

    fun PonerImagenPorqueRepetirloDosVecesYaMeMolestaPonerloEnCodigo (label: ImageView, Cosa:Int)
    {
        if(Cosa == 0)
            label.setImageResource(R.drawable.piedra)
        else if(Cosa == 1)
            label.setImageResource(R.drawable.papel)
        else if(Cosa == 2)
            label.setImageResource(R.drawable.tijeras)
        else
            label.setImageResource(R.drawable.baseline_question_mark_24)
    }
}