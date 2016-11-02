package com.lionsquare.registros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPerson extends AppCompatActivity implements View.OnClickListener {

    private Button addTodoBtn;
    private EditText nameEt, telEt, addressEt, curpEt, rfcEt, nscEt, aforeEd;

    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);


        setTitle("Agregar contacto");

        nameEt = (EditText) findViewById(R.id.name);
        telEt = (EditText) findViewById(R.id.tel);
        addressEt = (EditText) findViewById(R.id.address);
        curpEt = (EditText) findViewById(R.id.curp);
        rfcEt = (EditText) findViewById(R.id.rfc);
        nscEt = (EditText) findViewById(R.id.nsc);
        aforeEd = (EditText) findViewById(R.id.afore);

        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                add();
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            add();

        }
        return super.onOptionsItemSelected(item);
    }


    private void add() {

        String name = nameEt.getText().toString();
        String tel = telEt.getText().toString();
        String address = addressEt.getText().toString();
        String curp = curpEt.getText().toString();
        String rfc = rfcEt.getText().toString();
        String nsc = nscEt.getText().toString();
        String afore = aforeEd.getText().toString();

        dbManager.insert(name, tel, address, curp, rfc, nsc, afore);

        Intent main = new Intent(AddPerson.this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(main);

    }


}
