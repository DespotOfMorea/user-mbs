package org.vnuk.usermbs.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.data.room.entity.Buyer;
import org.vnuk.usermbs.databinding.BuyerItemBinding;

import java.util.ArrayList;
import java.util.List;

public class BuyerRecyclerViewAdapter extends RecyclerView.Adapter<BuyerRecyclerViewAdapter.BuyerViewHolder> {
    private static final String TAG = BuyerRecyclerViewAdapter.class.getSimpleName();
    private List<Buyer> buyers;

    public BuyerRecyclerViewAdapter() {
        this.buyers = new ArrayList<>();
    }

    @NonNull
    @Override
    public BuyerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.v(TAG, "OnCreateViewHolder.");
        BuyerItemBinding buyerItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.buyer_item, viewGroup, false);
        return new BuyerViewHolder(buyerItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerViewHolder buyerViewHolder, int i) {
        Log.v(TAG, "OnBindViewHolder.");
        Buyer buyer = buyers.get(i);
        buyerViewHolder.buyerItemBinding.setBuyer(buyer);
    }

    @Override
    public int getItemCount() {
        return (buyers == null) ? 0 : buyers.size();
    }

    public class BuyerViewHolder extends RecyclerView.ViewHolder {
        private BuyerItemBinding buyerItemBinding;

        public BuyerViewHolder(@NonNull BuyerItemBinding buyerItemBinding) {
            super(buyerItemBinding.getRoot());
            this.buyerItemBinding = buyerItemBinding;
        }
    }

    public void setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
        notifyDataSetChanged();
    }
}
