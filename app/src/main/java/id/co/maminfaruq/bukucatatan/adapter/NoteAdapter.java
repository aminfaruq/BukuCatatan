package id.co.maminfaruq.bukucatatan.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import id.co.maminfaruq.bukucatatan.CustomOnItemClickListener;
import id.co.maminfaruq.bukucatatan.FormAddUpdateActivity;
import id.co.maminfaruq.bukucatatan.Note;
import id.co.maminfaruq.bukucatatan.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private ArrayList<Note> listNote;
    private Activity activity;

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Note> getListNote() {
        return listNote;
    }

    public void setListNote(ArrayList<Note> listNote) {
        this.listNote = listNote;
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NoteViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder noteViewHolder, final int i) {
        noteViewHolder.tvTitle.setText(getListNote().get(i).getTitle());
        noteViewHolder.tvDate.setText(getListNote().get(i).getDate());
        noteViewHolder.tvDescription.setText(getListNote().get(i).getDescription());
        noteViewHolder.cvNote.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);
                intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION,i);
                intent.putExtra(FormAddUpdateActivity.EXTRA_NOTE,getListNote().get(i));
                activity.startActivityForResult(intent,FormAddUpdateActivity.REQUEST_UPDATE);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return getListNote().size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDescription,tvDate;
        CardView cvNote;
        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }
}
