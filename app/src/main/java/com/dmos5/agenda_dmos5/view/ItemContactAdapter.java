package com.dmos5.agenda_dmos5.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dmos5.agenda_dmos5.R;
import com.dmos5.agenda_dmos5.dao.ContactDao;
import com.dmos5.agenda_dmos5.model.Contact;

import java.util.List;

public class ItemContactAdapter extends RecyclerView.Adapter<ItemContactAdapter.ContactsViewHolder>  {

    private ContactDao mContactDao;
    private Context mContext;
    private List<Contact> mContactList;
    private static RecyclerItemClickListener mClickListener;

    public ItemContactAdapter(List<Contact> mSiteList, Context mContext) {
        this.mContactList = mSiteList;
        mContactDao = new ContactDao(mContext);
        mContext = mContext;
    }

    public void setClickListener(RecyclerItemClickListener clickListener) {
        ItemContactAdapter.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_contact, parent, false);
        ContactsViewHolder viewHolder = new ContactsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.txtvFirstName.setText(mContactList.get(position).getFirstName());
        holder.txtvLastName.setText(mContactList.get(position).getLastName());
        holder.txtvMobilenumber.setText(mContactList.get(position).getPhoneNumber());
        holder.txtvPhonenumber.setText(mContactList.get(position).getMobileNumber());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Os atributos disponíveis no layout
        public TextView txtvFirstName;
        public TextView txtvLastName;
        public TextView txtvPhonenumber;
        public TextView txtvMobilenumber;

        public ImageView imgvIcon;


        /*
        O Construtor recupera os elementos de layout
         */
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtvFirstName    = itemView.findViewById(R.id.txtv_firstname);
            txtvLastName     = itemView.findViewById(R.id.txtv_lastname);
            txtvPhonenumber  = itemView.findViewById(R.id.txtv_phoneNumber);
            txtvMobilenumber = itemView.findViewById(R.id.txtv_mobileNumber);
            imgvIcon = itemView.findViewById(R.id.img_icon);
            itemView.setOnClickListener(this);
        }

        /*
        Aqui tratamos o clique no item e não em elementos do item.
         */
        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(getAdapterPosition());
        }

    }

}
