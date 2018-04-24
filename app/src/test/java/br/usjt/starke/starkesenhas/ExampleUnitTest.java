package br.usjt.starke.starkesenhas;

import android.util.Log;


import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.starke.starkesenhas.model.Servico;
import br.usjt.starke.starkesenhas.model.StarkeNetwork;


public class ExampleUnitTest {

    @Test
    public void servicosIsNotEmpty() {
        String TAG = "TEST";
        ArrayList<Servico> servicos = new ArrayList<>();

        try {
            servicos = StarkeNetwork.listarServico("http://192.168.15.110:8081/starke_project/rest/servicos/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Servico s : servicos) {
           System.out.println( s.getId() + " : " + s.getNome());
        }
        Assert.assertEquals(servicos.isEmpty(), false);
    }

//        assertThat(servicos.isEmp, is(true));

}