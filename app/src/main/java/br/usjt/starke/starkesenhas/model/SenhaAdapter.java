package br.usjt.starke.starkesenhas.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.usjt.starke.starkesenhas.R;

import static br.usjt.starke.starkesenhas.model.Senha.DF_SENHA;

public class SenhaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Senha> senhas;

    public SenhaAdapter(Context context, ArrayList<Senha> senhas) {
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
            TextView est1 = view.findViewById(R.id.item_senha_estimativa_1);
            TextView est2 = view.findViewById(R.id.item_senha_estimativa_2);
            SenhaViewHolder viewHolder = new SenhaViewHolder();
            viewHolder.setNome(nome);
            viewHolder.setEstimativa1(est1);
            viewHolder.setEstimativa2(est2);
            view.setTag(viewHolder);
        }

        Senha senha = senhas.get(position);

        SenhaViewHolder viewHolder = (SenhaViewHolder)view.getTag();
        String est1 = DF_SENHA.format(senha.getEstimativaFila());
        String est2 = DF_SENHA.format(senha.getEstimativaAtendimento());
        viewHolder.getNome().setText(senha.getNome());
        viewHolder.getEstimativa1().setText(est1);
        viewHolder.getEstimativa2().setText(est2);

        return view;
    }
}
