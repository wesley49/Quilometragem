package br.com.quilometragem.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class VisualizarActivity extends AppCompatActivity {

    ListView list_item;
    Double km;
    int idMes;
    String mes;
    public static QuilometragemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        list_item = (ListView)findViewById(R.id.listaVisualizar);

        List<Quilometragem> lstQuilometro = Quilometragem.listAll(Quilometragem.class);

        adapter = new QuilometragemAdapter(this, lstQuilometro);

        list_item.setAdapter(adapter);
    }

    //Classe do Adapter
    public static class QuilometragemAdapter extends ArrayAdapter<Quilometragem> {
        public QuilometragemAdapter(Context context, List<Quilometragem> items){
            super(context,0,items);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

            View v = convertView;

            if(v == null){

                v = LayoutInflater.from(getContext()).inflate(R.layout.list_item,null);

            }

            Quilometragem item = getItem(position);

            TextView Mes = v.findViewById(R.id.mes_do_ano);
            TextView Km = v.findViewById(R.id.quilometragem);

            Mes.setText(item.getMes());
            Km.setText("Km "+item.getKm());

            return v;
        }
    }


}
