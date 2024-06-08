package com.example.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratoriocalificado03.databinding.ItemTeacherBinding
import com.squareup.picasso.Picasso

class TeacherViewHolder (view: View): RecyclerView.ViewHolder(view){
    private val binding = ItemTeacherBinding.bind(view)

    fun bind(teacher: Teacher){
        binding.tvName.text = teacher.name
        binding.tvLastName.text = teacher.last_name
        binding.tvEmail.text = teacher.email

        //Picaso es una dependia que nos permite cargar imagenes de internet :)
        Picasso.get()
            .load(teacher.image_url)
            .into(binding.ivTeacher)


        //Funcinalidades de los click
        //Telefono
        binding.cardViewTeacher.setOnClickListener {

            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${teacher.phone_number}")
               // data = Uri.parse("+51 :${teacher.phone}")
            }
            it.context.startActivity(intent)
        }

        //Email
        binding.cardViewTeacher.setOnLongClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${teacher.email}")
                putExtra(Intent.EXTRA_SUBJECT, "Consulta")
                putExtra(Intent.EXTRA_TEXT, "Estimado profesor ${teacher.name}, Buenas Tardes.")
            }
            it.context.startActivity(intent)
            true
        }
    }

}