package com.example.mk.proyectofinal;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mk on 31/05/2016.
 */
class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    // header titles
    // child data in format of header title, child title
    private HashMap<String, List<Productos>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> tipos, HashMap<String, List<Productos>> producto) {
        _listDataHeader = new ArrayList<>();
        for (String key : producto.keySet()) {
            System.out.println(key);
            _listDataHeader.add(key);

        }
        this._context = context;
        // this._listDataHeader =null;

        this._listDataChild = producto;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {

        System.out.println("HIJOOOOOOOOOOOOO");
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        System.out.println("HIJOOOOOOOOOOOOOID");
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {


        final String childText = getGroup(groupPosition).toString();
        final Productos expandedListText = (Productos) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.nombre);
        TextView precio = (TextView) convertView
                .findViewById(R.id.precio);


        txtListChild.setText(expandedListText.getProducto());
        precio.setText((int) expandedListText.getPrecio() + " €");
        //txtListChild.setText(childText);


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        System.out.println("PADREEEEEEEEEEEID");
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.tipo);
        ImageView imagen = (ImageView) convertView
                .findViewById(R.id.imagen);
        insertarImagenes(imagen, headerTitle);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void insertarImagenes(ImageView image, String tipo) {
        if (tipo.equals("Bebida")) {
            Context context = this._context;
            Picasso.with(context)
                    .load(R.drawable.jarra2)
                    .resize(250, 250)


                    .into(image)
            ;

        }
        if (tipo.equals("Carne")) {
            Context context = this._context;
            Picasso.with(context)
                    .load(R.drawable.carne3)
                    .resize(250, 250)


                    .into(image);

        }

    }
}

