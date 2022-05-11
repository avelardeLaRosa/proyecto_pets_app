package com.system.proyecto_pets.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.system.proyecto_pets.databinding.FragmentHomeBinding
import com.system.proyecto_pets.databinding.ItemPageBinding
import me.relex.circleindicator.CircleIndicator3
import org.w3c.dom.Text

class ViewPageAdapter(private var titulo:List<String>, private var detalles:List<String>,private var images:List<Int>): RecyclerView.Adapter<ViewPageAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(val binding:ItemPageBinding):RecyclerView.ViewHolder(binding.root){
        /*val itemTitle:TextView = itemView.findViewById(R.id.tvTitulo)
        val itemDetails:TextView = itemView.findViewById(R.id.tvAbout)
        val itemImage:ImageView = itemView.findViewById(R.id.ivImage)

        init {
            itemImage.setOnClickListener{ v: View ->
                val position:Int = adapterPosition
                Toast.makeText(itemView.context,"Presionaste el item #${position+1}", Toast.LENGTH_SHORT).show()
            }
        }*/

        val itemTitle:TextView = binding.tvTitulo
        val itemDetails:TextView = binding.tvAbout
        val itemImage:ImageView = binding.ivImage

        init {
            itemImage.setOnClickListener {v: View ->
                val position:Int = adapterPosition
                Toast.makeText(itemView.context,"Presionaste el item #${position+1}",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPageAdapter.Pager2ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPageBinding.inflate(inflater,parent,false)
        return Pager2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPageAdapter.Pager2ViewHolder, position: Int) {

        holder.itemTitle.text = titulo[position]
        holder.itemDetails.text = detalles[position]
        holder.itemImage.setImageResource(images[position])

    }

    override fun getItemCount(): Int {
        return titulo.size
    }


}