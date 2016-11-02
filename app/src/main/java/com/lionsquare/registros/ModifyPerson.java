package com.lionsquare.registros;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyPerson extends Activity implements View.OnClickListener {


    private Button updateBtn, deleteBtn;
    private EditText nameTv, telTv, addressTv, curpTv, rfcTv, nscTV, aforeTv;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_person);

        setTitle("Modify Record");


        dbManager = new DBManager(this);
        dbManager.open();

        nameTv = (EditText) findViewById(R.id.name);
        telTv = (EditText) findViewById(R.id.tel);
        addressTv = (EditText) findViewById(R.id.address);
        curpTv = (EditText) findViewById(R.id.curp);
        rfcTv = (EditText) findViewById(R.id.rfc);
        nscTV = (EditText) findViewById(R.id.nsc);
        aforeTv = (EditText) findViewById(R.id.afore);


        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String tel = intent.getStringExtra("tel");
        String address = intent.getStringExtra("address");
        String curp = intent.getStringExtra("curp");
        String rfc = intent.getStringExtra("rfc");
        String nsc = intent.getStringExtra("nsc");
        String afore = intent.getStringExtra("afore");


        _id = Long.parseLong(id);


        nameTv.setText(name);
        telTv.setText(tel);
        addressTv.setText(address);
        curpTv.setText(curp);
        rfcTv.setText(rfc);
        nscTV.setText(nsc);
        aforeTv.setText(afore);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:

                updateContact();
                break;

            case R.id.btn_delete:
                deleteContact();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
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



        }
        return super.onOptionsItemSelected(item);
    }

    private void delete() {

        dbManager.delete(_id);
        this.returnHome();
    }

    private void update() {

        String name = nameTv.getText().toString();
        String tel = telTv.getText().toString();
        String address = addressTv.getText().toString();
        String curp = curpTv.getText().toString();
        String rfc = rfcTv.getText().toString();
        String nsc = nscTV.getText().toString();
        String afore = aforeTv.getText().toString();

        dbManager.update(_id, name, tel, address, curp, rfc, nsc, afore);
        this.returnHome();

    }

    private void deleteContact() {
        final Context context = ModifyPerson.this;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.are_your_sure))
                .setTitle(context.getString(R.string.delete_divice))
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setNegativeButton(context.getString(R.string.msg_no),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton(context.getString(R.string.msg_si),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delete();

                            }
                        }).show();
    }

    private void updateContact() {
        final Context context = ModifyPerson.this;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.are_your_sure))
                .setTitle(context.getString(R.string.update_divice))
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setNegativeButton(context.getString(R.string.msg_no),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton(context.getString(R.string.msg_si),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                update();

                            }
                        }).show();
    }
}
