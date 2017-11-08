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

import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

public class BonusActivity extends AppCompatActivity {

    ListView list_item;
    QuilometroAdapter adapter;
    Double resultado = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);

        list_item = (ListView)findViewById(R.id.list_bonus);

//        List<Quilometragem>lstQuilometragem = Quilometragem.(Quilometragem.class);
        List<Quilometragem> lstQuilometragem = Select.from(Quilometragem.class).orderBy("ID_MES").list();
        List<Quilometragem> calcList = new ArrayList();

        for(int i=0;i<lstQuilometragem.size();++i){
            Quilometragem item = lstQuilometragem.get(i);

            if(item.getKm()<=4000){
                resultado += item.getKm()*1.5;
            }
            if(item.getKm()>4000){
                resultado += item.getKm()*1.25;
            }

            Quilometragem km = new Quilometragem();
            km.setMes(item.getMes());
            km.setKm(resultado);
            calcList.add(km);
        }

        adapter = new QuilometroAdapter(this,calcList);

        list_item.setAdapter(adapter);

    }

    private class QuilometroAdapter extends ArrayAdapter<Quilometragem>{
        public QuilometroAdapter(Context context, List<Quilometragem> items){
            super(context, 0, items);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            View v = convertView;

            if (v == null){
                v = LayoutInflater.from(getContext()).inflate(R.layout.list_item,null);
            }

            Quilometragem item = getItem(position);

            TextView mes = v.findViewById(R.id.mes_do_ano);
            TextView km = v.findViewById(R.id.quilometragem);
            //setar o bonus

            mes.setText(item.getMes());
            km.setText("Bonus:"+item.getKm());
            //setar o bonus de cada mês

            return v;
        }
    }
}
