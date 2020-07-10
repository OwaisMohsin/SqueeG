package com.example.co.squeeg.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.example.co.squeeg.ChatRoomActivity;
import com.example.co.squeeg.Model.POSTMessages;
import com.example.co.squeeg.R;
import com.example.co.squeeg.utils.AppConstants;

/**
 * Created by Prasad on 11/14/2017.
 */

public class MessageChatAdapter extends RecyclerView.Adapter<MessageChatAdapter.MyViewHolder> {
    Context mContext;
    List<POSTMessages.Chat_detail> details;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public MessageChatAdapter(Context mContext, List<POSTMessages.Chat_detail> details) {
        this.mContext = mContext;
        this.details = details;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_message_chat, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (!details.get(position).getProfile_image().isEmpty())
            Picasso.get().load(AppConstants.BASE_URL + details.get(position).getProfile_image()).placeholder(R.drawable.ic_no_image_100).into(holder.imageMessagerPf);
            //holder.imageMessagerPf.setImageURI(Uri.parse(AppConstants.BASE_URL + details.get(position).getProfile_image()));
        holder.messangerName.setText(details.get(position).getFirstname());
        holder.messangerMsg.setText(details.get(position).getLast_message());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = fmt.parse(details.get(position).getUtc_time());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");
            String hrMins = fmtOut.format(date);
            holder.messangerTime.setText(hrMins);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imageMessagerPf;
        private TextView messangerName, messangerMsg, messangerTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageMessagerPf = (CircleImageView) itemView.findViewById(R.id.iv_message_profile_img);
            messangerName = (TextView) itemView.findViewById(R.id.tv_messager_name);
            messangerMsg = (TextView) itemView.findViewById(R.id.tv_messager_last_message);
            messangerTime = (TextView) itemView.findViewById(R.id.tv_messager_last_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callChatRoom = new Intent(mContext, ChatRoomActivity.class);
                    callChatRoom.putExtra("user_id", details.get(getAdapterPosition()).getUser_id());
                    callChatRoom.putExtra("chat_id", details.get(getAdapterPosition()).getChat_id());
                    callChatRoom.putExtra("chat_name", details.get(getAdapterPosition()).getFirstname());
                    callChatRoom.putExtra("user_image", details.get(getAdapterPosition()).getProfile_image());
                    callChatRoom.putExtra("user_status", details.get(getAdapterPosition()).getUser_status());
                    mContext.startActivity(callChatRoom);


                }
            });
        }
    }
}
