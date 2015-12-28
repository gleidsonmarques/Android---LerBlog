import java.net.URL;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class LerBlogActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setListAdapter(new ArrayAdapter<Entrada>(this,
		  android.R.layout.simple_list_item_1, carregar()));
	}

	public ArrayList<Entrada> carregar() {
		ArrayList<Entrada> lista = new ArrayList<Entrada>();
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = factory.newPullParser();
			URL url = new URL(getResources().getString(R.string.stEndereco));
			xpp.setInput(url.openStream(), null);
			if (xpp != null) {
				int eventType = xpp.getEventType();
				String currentTag = null;
				boolean isEntry = false;
				Entrada entrada = null;
				while (eventType != XmlPullParser.END_DOCUMENT) {
					if (eventType == XmlPullParser.START_TAG) {
						currentTag = xpp.getName();
						if ("entry".equals(currentTag)) {
							isEntry = true;
						}
					} else if (isEntry && eventType == XmlPullParser.TEXT) {
						if (isEntry && "title".equals(currentTag)) {
							entrada = new Entrada(xpp.getText());
						}
						if (isEntry && "content".equals(currentTag)) {
							entrada.setDescricao(xpp.getText());
							lista.add(entrada);
						}
					}
					eventType = xpp.next();
				}
			}
		} catch (Exception e) {
		}
		return lista;
	}

}
