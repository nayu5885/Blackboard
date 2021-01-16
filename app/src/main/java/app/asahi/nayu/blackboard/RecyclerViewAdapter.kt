package app.asahi.nayu.blackboard

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list.view.*

class RecyclerViewAdapter(private val context:Context):
    RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder>(){
        val items : MutableList<BoardData> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.list,parent,false)
        return ViewHolder(view)
          }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.contentTextView.text=item.contentTextView
        holder.titleTextView.text=item.titleTextView
    }

    fun addAll(items:List<BoardData>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val contentTextView:TextView = view.findViewById(R.id.contentTextView)
        val titleTextView:TextView = view.findViewById(R.id.titleTextView)
    }

}