package com.tony.androidxmlparser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<RssUOL> rssUol;
    AdaptadorRSS rssAdaptador;
    ListView listRss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listRss = (ListView) findViewById(R.id.listRss);

        new MinhaAsyncTask().execute();
    }

    public void retornaTask(ArrayList<RssUOL> _rssUol) {

        rssUol = _rssUol;
        if (rssUol != null) {
            rssAdaptador = new AdaptadorRSS(this, rssUol);
            listRss.setAdapter(rssAdaptador);
        }

    }

    private class MinhaAsyncTask extends AsyncTask<Void, Void, ArrayList<RssUOL>> {

        @Override
        protected ArrayList<RssUOL> doInBackground(Void... voids) {
            try {

                URL url = new URL("http://tecnologia.uol.com.br/ultnot/index.xml");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("item");

                ArrayList<RssUOL> rssUol = new ArrayList<>();

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    Element elemento = (Element) node;

                    NodeList noTitulo = elemento.getElementsByTagName("title");
                    Element eleTitulo = (Element) noTitulo.item(0);
                    noTitulo = eleTitulo.getChildNodes();

                    NodeList noLink = elemento.getElementsByTagName("link");
                    Element eleLink = (Element) noLink.item(0);
                    noLink = eleLink.getChildNodes();


                    RssUOL item = new RssUOL();
                    item.setTitulo(((Node) noTitulo.item(1)).getNodeValue());
                    item.setLink(((Node) noLink.item(1)).getNodeValue());

                    rssUol.add(item);
                }
                return rssUol;

            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }


        @Override
        protected void onPostExecute(ArrayList<RssUOL> rssUol) {
            retornaTask(rssUol);
        }
    }
}
