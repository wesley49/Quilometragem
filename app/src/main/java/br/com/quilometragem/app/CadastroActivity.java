package br.com.quilometragem.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    Spinner spinnerMes;
    ArrayAdapter adapterMes;
    EditText quilometro;

    int idMes;
    String mes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        spinnerMes = (Spinner)findViewById(R.id.spinner_mes);
        quilometro = (EditText)findViewById(R.id.cad_quilometro);


        spinnerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mes= (String)adapterMes.getItem(i);
                idMes = (int)adapterMes.getItemId(i);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        iniciarAdapterSpinner();



    }

    public void Inserir(View view) {

        Double km = Double.parseDouble(quilometro.getText().toString());


        //Se for nulo não faça nada
        if(km == null)return;

        //cria o objeto quilometragem
        Quilometragem q = new Quilometragem(km,idMes,mes);

        List<Quilometragem> quilo = Select.from(Quilometragem.class).where(Condition.prop("mes").eq(mes)).list();

        if(quilo!=null && quilo.size()>0){
            Quilometragem qm = quilo.get(0);

            qm.setKm(km);
            qm.save();
        }else{
            q.save();
        }







        Toast.makeText(this,"id>>"+idMes,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"km>>"+km,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"mes>>"+mes,Toast.LENGTH_SHORT).show();

/*        if(q.save()==1){
            Toast.makeText(this,"Ta salvando",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"Verifica pq pode ter funcionado",Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"id>>"+idMes,Toast.LENGTH_SHORT).show();
        }*/

    }




    public void iniciarAdapterSpinner(){
        adapterMes = ArrayAdapter.createFromResource(this,R.array.mes,android.R.layout.simple_spinner_item);
        spinnerMes.setAdapter(adapterMes);
    }

    public void Visualizar(View view) {

        startActivity(new Intent(this,VisualizarActivity.class));

    }

    public void Bonus(View view) {

        startActivity(new Intent(this,BonusActivity.class));

    }
}
