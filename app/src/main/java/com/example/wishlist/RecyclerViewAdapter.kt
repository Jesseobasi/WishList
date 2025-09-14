import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlist.R

class WishlistAdapter(private val items: MutableList<WishlistItem>) :
    RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    inner class WishlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.itemNameTv)
        val linkText: TextView = itemView.findViewById(R.id.itemLinkTv)
        val priceText: TextView = itemView.findViewById(R.id.itemPriceTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wishlist, parent, false)
        return WishlistViewHolder(view)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val item = items[position]
        holder.nameText.text = item.name
        holder.priceText.text = "$${item.price}"
        holder.linkText.text = item.link.ifEmpty { "Some Store" }

        holder.linkText.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: WishlistItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
}
