package com.example.icehardware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<product> ProductList = new ArrayList<>();

    public void setProductList(ArrayList<product>ProductList) {
        this.ProductList = ProductList;
    }
    public ProductAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return ProductList.size();
    }
    @Override
    public Object getItem(int i) {
        return ProductList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.product, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(itemView);
        product Product = (product)getItem(i);
        viewHolder.bind(Product);
        return itemView;
    }
    private class ViewHolder {
        private TextView txtBrand, txtName, txtJenis, txtHarga;
        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_nama);
            txtJenis = view.findViewById(R.id.txt_jenis);
            txtBrand = view.findViewById(R.id.txt_brand);
            txtHarga = view.findViewById(R.id.txt_harga);
        }
        void bind(product Product) {
            txtName.setText(Product.getNama());
            txtBrand.setText(Product.getBrand());
            txtJenis.setText(Product.getJenis());
            txtHarga.setText(Product.getHarga());
        }
    }
}
