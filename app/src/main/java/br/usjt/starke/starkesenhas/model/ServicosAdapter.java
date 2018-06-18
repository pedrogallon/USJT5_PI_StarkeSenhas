package br.usjt.starke.starkesenhas.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.usjt.starke.starkesenhas.R;

import static br.usjt.starke.starkesenhas.model.Senha.DF_SENHA;

public class ServicosAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Senha> senhas;

    public ServicosAdapter(Context context, ArrayList<Senha> senhas) {
        this.context = context;
        this.senhas = senhas;
    }

    @Override
    public int getCount() {
        return senhas.size();
    }

    @Override
    public Object getItem(int position) {
        return senhas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_senha, parent, false);

            TextView nome = view.findViewById(R.id.item_senha_nome);
            TextView inicio = view.findViewById(R.id.item_senha_status);
            TextView termino = view.findViewById(R.id.item_senha_estimativa);
            SenhaViewHolder viewHolder = new SenhaViewHolder();
            viewHolder.setNome(nome);
            viewHolder.setStatus(inicio);
            viewHolder.setEstimativa(termino);
            view.setTag(viewHolder);
        }

        Senha senha = senhas.get(position);

        SenhaViewHolder viewHolder = (SenhaViewHolder)view.getTag();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        viewHolder.getNome().setText(senha.getNome());
        if(senha.getDataEntrada() != null)
            viewHolder.getStatus().setText(sdf.format(senha.getDataEntrada()));
        else
            viewHolder.getStatus().setText("-");

        if(senha.getDataSaida() != null)
            viewHolder.getEstimativa().setText(sdf.format(senha.getDataSaida()));
        else
            viewHolder.getEstimativa().setText("-");
        return view;
    }
}
