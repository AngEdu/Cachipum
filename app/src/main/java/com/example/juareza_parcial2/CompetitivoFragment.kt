package com.example.juareza_parcial2

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import de.hdodenhof.circleimageview.CircleImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CompetitivoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CompetitivoFragment : Fragment()
{
    private var PosBot: Int = 3
    private var PosTuya: Int = 4
    private var Victoria: Boolean = false

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
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
        //return inflater.inflate(R.layout.fragment_competitivo, container, false)
        val Vista: View = inflater.inflate(R.layout.fragment_competitivo, container, false)

        Vista.findViewById<TextView>(R.id.lbl_Resultado2).visibility = View.INVISIBLE
        Vista.findViewById<Button>(R.id.btn_NuevoJuego).visibility = View.INVISIBLE
        //Rotación de las imágenes para su efectividad
        Vista.findViewById<ImageView>(R.id.img_TijeraPapel2).rotation = 180.0F
        Vista.findViewById<ImageView>(R.id.img_PapelPiedra2).rotation = 315.0F
        Vista.findViewById<ImageView>(R.id.img_PiedraTijera2).rotation = 45.0F

        val btnPiedra = Vista.findViewById<ImageButton>(R.id.btnPiedra2)
        val btnPapel = Vista.findViewById<ImageButton>(R.id.btnPapel2)
        val btnTijera = Vista.findViewById<ImageButton>(R.id.btnTijera2)
        val btnSiguiente = Vista.findViewById<Button>(R.id.btn_NuevoJuego)

        btnPiedra.setOnClickListener {
            Juegote(0, Vista)
        }

        btnPapel.setOnClickListener {
            Juegote(1, Vista)
        }

        btnTijera.setOnClickListener {
            Juegote(2, Vista)
        }

        btnSiguiente.setOnClickListener {
            NuevoJuego(Vista)
        }

        return Vista
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CompetitivoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CompetitivoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun Juegote (Jugador:Int, Vista:View)
    {
        val lblResultado = Vista.findViewById<TextView>(R.id.lbl_Resultado2)

        val imgBot = Vista.findViewById<ImageView>(R.id.img_EleccionBot2)
        val imgJug = Vista.findViewById<ImageView>(R.id.img_EleccionJug2)

        val imgPos1 = Vista.findViewById<CircleImageView>(R.id.img_Pos1)
        val imgPos2 = Vista.findViewById<CircleImageView>(R.id.img_Pos2)
        val imgPos3 = Vista.findViewById<CircleImageView>(R.id.img_Pos3)
        val imgPos4 = Vista.findViewById<CircleImageView>(R.id.img_Pos4)
        val imgPos5 = Vista.findViewById<CircleImageView>(R.id.img_Pos5)
        val imgPos6 = Vista.findViewById<CircleImageView>(R.id.img_Pos6)

        /*
            0 = PIEDRA
            1 = PAPEL
            2 = TIJERA
        */
        var Botsote = (0..2).random() //Genera un número aleatorio del 0 al 2

        //SECCIÓN PARA PONER LA IMAGEN DE LA ELECCIÓN
        PonerImagenPorqueRepetirloDosVecesYaMeMolestaPonerloEnCodigo(imgBot,Botsote)
        PonerImagenPorqueRepetirloDosVecesYaMeMolestaPonerloEnCodigo(imgJug,Jugador)

        if(Jugador == Botsote) //Empate
            PosBot = PosBot
        else if ((Jugador == 0 && Botsote == 2)||(Jugador==1&&Botsote==0)||(Jugador==2&&Botsote==1)) //Cuando el jugador gana
        {
            PosBot--
            PosTuya--
            Toast.makeText(context, "Has ganado, avanzas una casilla.", Toast.LENGTH_SHORT).show()
        }
        else
        {
            PosBot++
            PosTuya++
            Toast.makeText(context, "Has perdido, retrocedes una casilla.", Toast.LENGTH_SHORT).show()
        }

        //ESTA PARTE REVISA LAS POSICIONES Y PONE EN BLANCO EL RESTO

        imgPos1.setImageResource(R.drawable.blanco)
        imgPos2.setImageResource(R.drawable.blanco)
        imgPos3.setImageResource(R.drawable.blanco)
        imgPos4.setImageResource(R.drawable.blanco)
        imgPos5.setImageResource(R.drawable.blanco)
        imgPos6.setImageResource(R.drawable.blanco)

        when (PosBot)
        {
            1 -> imgPos1.setImageResource(R.drawable.baseline_android_24)
            2 -> imgPos2.setImageResource(R.drawable.baseline_android_24)
            3 -> imgPos3.setImageResource(R.drawable.baseline_android_24)
            4 -> imgPos4.setImageResource(R.drawable.baseline_android_24)
            5 -> imgPos5.setImageResource(R.drawable.baseline_android_24)
            6 -> imgPos6.setImageResource(R.drawable.baseline_android_24)
        }

        when (PosTuya)
        {
            1 -> imgPos1.setImageResource(R.drawable.baseline_person_24)
            2 -> imgPos2.setImageResource(R.drawable.baseline_person_24)
            3 -> imgPos3.setImageResource(R.drawable.baseline_person_24)
            4 -> imgPos4.setImageResource(R.drawable.baseline_person_24)
            5 -> imgPos5.setImageResource(R.drawable.baseline_person_24)
            6 -> imgPos6.setImageResource(R.drawable.baseline_person_24)
        }

        //VICTORIA
        if (PosTuya==1) //Cuando el jugador gana
        {
            lblResultado.setTextColor(Color.RED)
            lblResultado.text = "¡Has ganao!\nFelicidades, eres muy bueno"
            Victoria = true
        }
        else if (PosBot==6)
        {
            lblResultado.setTextColor(Color.BLUE)
            lblResultado.text = "¡Has perdío!\nAhí pa' la otra"
            Victoria = true
        }
        if(Victoria)
        {
            //Mensaje y botón de nuevo juego
            lblResultado.textSize = 23F
            lblResultado.textAlignment = View.TEXT_ALIGNMENT_CENTER
            lblResultado.visibility = View.VISIBLE
            Vista.findViewById<Button>(R.id.btn_NuevoJuego).visibility = View.VISIBLE

            //Inhabilitar los demás botones
            Vista.findViewById<ImageButton>(R.id.btnPiedra2).isEnabled = false
            Vista.findViewById<ImageButton>(R.id.btnPapel2).isEnabled = false
            Vista.findViewById<ImageButton>(R.id.btnTijera2).isEnabled = false
        }
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

    fun NuevoJuego(Vista: View)
    {
        if(Victoria)
        {
            PosBot = 3
            PosTuya = 4
            Victoria = false
            Vista.findViewById<ImageButton>(R.id.btnPiedra2).isEnabled = true
            Vista.findViewById<ImageButton>(R.id.btnPapel2).isEnabled = true
            Vista.findViewById<ImageButton>(R.id.btnTijera2).isEnabled = true

            Vista.findViewById<CircleImageView>(R.id.img_Pos1).setImageResource(R.drawable.blanco)
            Vista.findViewById<CircleImageView>(R.id.img_Pos2).setImageResource(R.drawable.blanco)
            Vista.findViewById<CircleImageView>(R.id.img_Pos3).setImageResource(R.drawable.baseline_android_24)
            Vista.findViewById<CircleImageView>(R.id.img_Pos4).setImageResource(R.drawable.baseline_person_24)
            Vista.findViewById<CircleImageView>(R.id.img_Pos5).setImageResource(R.drawable.blanco)
            Vista.findViewById<CircleImageView>(R.id.img_Pos6).setImageResource(R.drawable.blanco)

            Vista.findViewById<TextView>(R.id.lbl_Resultado2).visibility = View.INVISIBLE
            Vista.findViewById<Button>(R.id.btn_NuevoJuego).visibility = View.INVISIBLE
        }
    }
}