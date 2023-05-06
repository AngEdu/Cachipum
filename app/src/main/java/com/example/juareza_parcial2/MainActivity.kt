package com.example.juareza_parcial2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.juareza_parcial2.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ReemplazarFragment(ClasicoFragment())

        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId)
            {
                R.id.Clasico -> ReemplazarFragment(ClasicoFragment())
                R.id.Competitivo -> ReemplazarFragment(CompetitivoFragment())
                else ->{ReemplazarFragment(CompetitivoFragment())}
            }//when
            true
        }//setOnItemSelectedListener
    }//onCreate

    private fun ReemplazarFragment(fragment : Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FrameContainer,fragment)
        fragmentTransaction.commit()
    }//ReemplazarFragment
}