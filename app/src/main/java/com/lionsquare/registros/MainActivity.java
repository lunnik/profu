package com.lionsquare.registros;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lionsquare.registros.Beans.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private DBManager dbManager;

    private ListView listView;

    private Cursor cursor;

    AdapterPerson adapterPerson;

    private ArrayList<Person> arrayList = new ArrayList<Person>();
    private List<Person> contentsOriginal= new ArrayList<Person>();

    final String[] from = new String[]{DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.TEL, DatabaseHelper.ADDRESS, DatabaseHelper.CURP, DatabaseHelper.RFC, DatabaseHelper.NSC};

    final int[] to = new int[]{R.id.id, R.id.name, R.id.tel, R.id.addres, R.id.curp, R.id.rfc, R.id.nds};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);
        dbManager.open();
        cursor = dbManager.fetch();


        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        //adapter = new SimpleCursorAdapter(this, R.layout.item_person, cursor, from, to, 0);
        //adapter.notifyDataSetChanged();

        //listView.setAdapter(adapter);
        adapterPerson = new AdapterPerson();
        arrayList.clear();
        contentsOriginal.clear();
        arrayList = dbManager.getFavList();
        contentsOriginal = dbManager.getFavList();
        adapterPerson.notifyDataSetChanged();
        listView.setAdapter(adapterPerson);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView nameTextView = (TextView) view.findViewById(R.id.name);
                TextView telTextView = (TextView) view.findViewById(R.id.tel);
                TextView addressView = (TextView) view.findViewById(R.id.addres);
                TextView curpextView = (TextView) view.findViewById(R.id.curp);
                TextView rfcTextView = (TextView) view.findViewById(R.id.rfc);
                TextView nscTextView = (TextView) view.findViewById(R.id.nds);
                TextView aforeTextView = (TextView) view.findViewById(R.id.nds);

                String id = idTextView.getText().toString();
                String name = nameTextView.getText().toString();
                String tel = telTextView.getText().toString();
                String address = addressView.getText().toString();
                String curp = curpextView.getText().toString();
                String rfc = rfcTextView.getText().toString();
                String nsc = nscTextView.getText().toString();
                String afore = aforeTextView.getText().toString();


                Intent modify_intent = new Intent(getApplicationContext(), ModifyPerson.class);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("tel", tel);
                modify_intent.putExtra("address", address);
                modify_intent.putExtra("curp", curp);
                modify_intent.putExtra("rfc", rfc);
                modify_intent.putExtra("nsc", nsc);
                modify_intent.putExtra("afore", afore);

                startActivity(modify_intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        adapterPerson.setFilter(contentsOriginal);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        arrayList.clear();
                        arrayList = dbManager.getFavList();
                        adapterPerson.notifyDataSetChanged();
                        Log.e("prueba", "de expacion");
                        return true; // Return true to expand action view
                    }
                });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddPerson.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Person> filteredModelList = filter(arrayList, newText);
        adapterPerson.setFilter(filteredModelList);
        return false;
    }

    private List<Person> filter(List<Person> models, String query) {
        query = query.toLowerCase();

        final List<Person> filteredModelList = new ArrayList<>();
        for (Person model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


    class AdapterPerson extends BaseAdapter {


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(MainActivity.this);
                v = vi.inflate(R.layout.item_person, null);
            }
            TextView tvId, tvName, tvTeL, tvAddress, tvCurp, tvRfc, tvNds, tvAfore;

            tvId = (TextView) v.findViewById(R.id.id);
            tvName = (TextView) v.findViewById(R.id.name);
            tvTeL = (TextView) v.findViewById(R.id.tel);
            tvAddress = (TextView) v.findViewById(R.id.addres);
            tvCurp = (TextView) v.findViewById(R.id.curp);
            tvRfc = (TextView) v.findViewById(R.id.rfc);
            tvNds = (TextView) v.findViewById(R.id.nds);
            tvAfore = (TextView) v.findViewById(R.id.afore);

            Person item = arrayList.get(position);
            tvId.setText(item.getId());
            tvName.setText(item.getName());
            tvTeL.setText(item.getTel());
            tvAddress.setText(item.getAddress());
            tvCurp.setText(item.getCurp());
            tvRfc.setText(item.getRfc());
            tvNds.setText(item.getNds());
            tvAfore.setText(item.getAfore());


            return v;
        }

        public void setFilter(List<Person> listaReitings) {
            arrayList = new ArrayList<>();
            arrayList.addAll(listaReitings);
            notifyDataSetChanged();
        }


    }

}
