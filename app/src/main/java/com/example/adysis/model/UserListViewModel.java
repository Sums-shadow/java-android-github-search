package com.example.adysis.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.adysis.R;

import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserListViewModel extends ArrayAdapter<gitUser> {
    private int resource;
public UserListViewModel(@NonNull Context context, int resource, List<gitUser> user){
    super(context,resource,user);
    this.resource=resource;

}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem=convertView;
        if(listViewItem==null){
            listViewItem= LayoutInflater.from(getContext()).inflate(resource,parent,false);

        }
        TextView login=listViewItem.findViewById(R.id.login);
        TextView star=listViewItem.findViewById(R.id.star);
        CircleImageView img=listViewItem.findViewById(R.id.img);

        star.setText(String.valueOf(getItem(position).score));
        login.setText(getItem(position).login);
        try {
            URL url=new URL(getItem(position).avatarUrl);
            Bitmap bitmap= BitmapFactory.decodeStream(url.openStream());
            img.setImageBitmap(bitmap);

        }catch (Exception e){

        }


        return listViewItem;

    }
}
