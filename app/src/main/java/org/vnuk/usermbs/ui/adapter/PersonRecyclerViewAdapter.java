package org.vnuk.usermbs.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.vnuk.usermbs.R;
import org.vnuk.usermbs.data.personAPI.entity.PersonEntity;
import org.vnuk.usermbs.databinding.PersonItemBinding;

import java.util.ArrayList;
import java.util.List;

public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.PersonViewHolder> {
    private static final String TAG = PersonRecyclerViewAdapter.class.getSimpleName();

    private List<PersonEntity> persons;
    private OnItemClickListener onItemClickListener;

    public PersonRecyclerViewAdapter() {
        this.persons = new ArrayList<>();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.v(TAG, "OnCreateViewHolder.");
        PersonItemBinding personItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.person_item, viewGroup, false);
        return new PersonViewHolder(personItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
        Log.v(TAG, "OnBindViewHolder.");
        PersonEntity person = persons.get(i);
        personViewHolder.bind(person, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return (persons == null) ? 0 : persons.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, PersonEntity item);
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        private PersonItemBinding personItemBinding;

        public PersonViewHolder(@NonNull PersonItemBinding personItemBinding) {
            super(personItemBinding.getRoot());
            this.personItemBinding = personItemBinding;
        }

        public void bind(PersonEntity item, OnItemClickListener onItemClickListener) {
            personItemBinding.setPerson(item);
            personItemBinding.executePendingBindings();
            itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, item);
                }
            });
        }
    }

    public void setPersons(List<PersonEntity> persons) {
        this.persons = persons;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
