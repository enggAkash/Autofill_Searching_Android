package com.example.autofillrough1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akash Kumar on 18-Feb-18.
 */

public class UserAdapter extends ArrayAdapter<User> {

    private List<User> userList;
    private List<User> tempList;
    private List<User> suggestionList;


    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        userList = objects;
        tempList = new ArrayList<>(userList);
        suggestionList = new ArrayList<>();

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_raw_layout, parent, false);

        TextView textView = convertView.findViewById(R.id.simple_text);

        User user = userList.get(position);

        textView.setText(user.getName() + "  " + user.getMobile() + "  " + user.getEmail() + "  " + user.getAddress());

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return userFilter;
    }


    Filter userFilter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            User user = (User) resultValue;

            return user.getName() + "  " + user.getMobile() + "  " + user.getEmail() + "  " + user.getAddress();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                suggestionList.clear();
                constraint = constraint.toString().trim().toLowerCase();

                for (User user : tempList) {

                    if (user.getName().toLowerCase().contains(constraint)
                            || user.getMobile().toLowerCase().contains(constraint)
                            || user.getEmail().toLowerCase().contains(constraint)
                            || user.getAddress().toLowerCase().contains(constraint)) {

                        suggestionList.add(user);
                    }
                }

                filterResults.count = suggestionList.size();
                filterResults.values = suggestionList;

            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<User> uList = (ArrayList<User>) results.values;

            if (results != null && results.count > 0) {
                clear();
                for (User u : uList) {
                    add(u);
                    notifyDataSetChanged();
                }
            }

        }
    };

}
