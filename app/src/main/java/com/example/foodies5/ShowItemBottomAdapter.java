package com.example.foodies5;

import android.content.Context;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShowItemBottomAdapter extends RecyclerView.ViewHolder {
    TextView Show_Item_Name,Show_Item_Count,Show_Item_Price;

    public ShowItemBottomAdapter(@NonNull View itemView) {
        super(itemView);
    }

    public  void set_user_ordered_food_details(Context context,String show_item_name,String show_item_count,String  show_item_price)
    {
        Show_Item_Name=itemView.findViewById(R.id.show_item_name);
        Show_Item_Count=itemView.findViewById(R.id.show_item_count);
        Show_Item_Price=itemView.findViewById(R.id.show_item_price);

        Show_Item_Name.setText(show_item_name);
        Show_Item_Count.setText(show_item_count);
        Show_Item_Price.setText(String.valueOf(Integer.parseInt(show_item_count)*Integer.parseInt(show_item_price)));

    }

//
//    List<Show_chosen_Items> list_show_chosen_items;
//
//    public ShowItemBottomAdapter(List<Show_chosen_Items> list_show_chosen_items) {
//        this.list_show_chosen_items = list_show_chosen_items;
//    }
//
//
//    @NonNull
//    @Override
//    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//       View iview= LayoutInflater.from(parent.getContext()).inflate(R.layout.show_items_on_bottom_sheet_dialog,parent,false);
//        return new ItemViewHolder(iview);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
//        holder.show_item_name.setText(list_show_chosen_items.get(position).getShow_item_name());
//        holder.show_item_count.setText(list_show_chosen_items.get(position).getShow_item_count());
//        holder.show_item_price.setText(list_show_chosen_items.get(position).getShow_item_price());
//    }
//
//    @Override
//    public int getItemCount() {
//        return list_show_chosen_items.size();
//    }
//
//    public static class ItemViewHolder extends RecyclerView.ViewHolder {
//        TextView show_item_name,show_item_count,show_item_price;
//
//        public ItemViewHolder(@NonNull View itemView) {
//            super(itemView);
//            show_item_name=itemView.findViewById(R.id.show_item_name);
//            show_item_count=itemView.findViewById(R.id.show_item_count);
//            show_item_price=itemView.findViewById(R.id.show_item_price);
//        }
//    }



}
