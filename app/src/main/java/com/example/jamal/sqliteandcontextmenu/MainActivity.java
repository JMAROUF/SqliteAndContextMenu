package com.example.jamal.sqliteandcontextmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private monSqLiteOpenHelper helper;
    private EditText txtDesc;
    private EditText txtPrix;
    private ProduitDataAccess data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Gestion produits");
        helper = new monSqLiteOpenHelper(this, "data.db", null, 1);
        data = new ProduitDataAccess(helper);
        actualiser();
    }

    public void ajouter_produit(View view) {
        txtDesc = (EditText) findViewById(R.id.description);
        txtPrix = (EditText) findViewById(R.id.prix);
        Produit prod = new Produit(txtDesc.getText().toString(),
                Float.parseFloat(txtPrix.getText().toString()));
        data.ajouterProduit(prod);

        actualiser();

    }

    // methode pour actualiser la listeView
    private void actualiser() {
        ListView listeV = (ListView) findViewById(R.id.listeView);
        registerForContextMenu(listeV);
        produiAdapter ada = new produiAdapter(this, data.listerProduit());
        listeV.setAdapter(ada);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_produit, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //cette methode sert à récupérer l'element selectionné
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //ici on récupére le code de l'element selectionné
        TextView lblCode = (TextView) info.targetView.findViewById(R.id.code_l);
        int code = Integer.parseInt(lblCode.getText().toString());
        // ici on immplémente les évenements ( ajout , modification et suppréssion)
        switch (item.getItemId()) {
            case R.id.supp:
                data.supprimerProduit(code);
                actualiser();
                break;
            case R.id.modif:
                txtDesc = (EditText) findViewById(R.id.description);
                txtPrix = (EditText) findViewById(R.id.prix);
                Produit pro = new Produit(txtDesc.getText().toString(), Float.parseFloat(txtPrix.getText().toString()));
                pro.setCode(code);
                data.modifierProduit(code, pro);
                actualiser();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
