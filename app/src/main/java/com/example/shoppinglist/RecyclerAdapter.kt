package com.example.shoppinglist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.db.ItemEntity
import com.example.shoppinglist.db.RoomDBapp
import com.example.shoppinglist.DetailsActivity
import com.example.shoppinglist.touch.ItemTouchHelperCallback
import java.io.Serializable
import java.util.*

class RecyclerAdapter(val viewModel: MyViewModel, org: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(), ItemTouchHelperCallback  {

    var Items = ArrayList<ItemEntity>()

    var context=org




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        context=parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int, )
    {
        val currentItem=Items[position]
        holder.itemName.text="Name: "+currentItem.name
        holder.itemHUF.text="Price in HUF: "+currentItem.price.toString()
        holder.itemCheckbox.isChecked=currentItem.bought
        holder.itemImage.setImageResource(symbolFinder(currentItem.category))
        holder.itemDetails.setOnClickListener(View.OnClickListener {
            val intent = Intent(context,DetailsActivity::class.java)
            intent.putExtra("123",currentItem as Serializable)
            startActivity(context, intent, null)
        }
        )
        holder.itemCheckbox.setOnClickListener {
            currentItem.bought= !currentItem.bought
            viewModel.update(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return Items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {

        var itemCheckbox: CheckBox
        var itemName: TextView
        var itemHUF: TextView
        var itemImage: ImageView
        var itemDetails: Button

        init {
            itemCheckbox=itemView.findViewById(R.id.checkbox)
            itemName=itemView.findViewById(R.id.Name)
            itemHUF=itemView.findViewById(R.id.PriceHUF)
            itemImage=itemView.findViewById(R.id.symbol)
            itemDetails=itemView.findViewById(R.id.details)
        }
    }

    fun deleteItem(position: Int) {
        viewModel.delete(Items.get(position))

    }

    override fun onDismissed(position: Int) {
        deleteItem(position)

    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(Items, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun symbolFinder(a : String): Int{
        when(a) {
            "Food" -> return R.drawable.outline_local_dining_24
            "Electronics"-> return R.drawable.outline_devices_24
            else -> return R.drawable.outline_library_books_24
        }

    }
}