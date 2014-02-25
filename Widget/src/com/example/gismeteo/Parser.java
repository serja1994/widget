package com.example.gismeteo;

import java.util.ArrayList;

public class Parser {
	public ArrayList<String> parserHigh(ArrayList<String> str, int k) {
		String string, bufferdata, buffertime, bufferall, dopstring, buf1, buf2, buf3, buf4;
		if (k == 0) {
			k = 4;
		} else if (k == 1) {
			k = 12;
		} else if (k == 2) {
			k = 20;
		} else {
			k = 28;
		}
		string = str.get(k);

		bufferdata = string.substring(string.indexOf("day=") + 5,
				string.indexOf("month=") - 2)
				+ '.'
				+ string.substring(string.indexOf("month=") + 7,
						string.indexOf("year=") - 2)
				+ '.'
				+ string.substring(string.indexOf("year=") + 6,
						string.indexOf("hour=") - 2);

		buffertime = string.substring(string.indexOf("hour=") + 6,
				string.indexOf("tod=") - 2);

		if ((Integer.parseInt(buffertime) >= 0)
				&& (Integer.parseInt(buffertime) < 6))
			buffertime = "ночь";
		else if ((Integer.parseInt(buffertime) >= 6)
				&& (Integer.parseInt(buffertime) < 12))
			buffertime = "утро";
		else if ((Integer.parseInt(buffertime) >= 12)
				&& (Integer.parseInt(buffertime) < 18))
			buffertime = "день";
		else if ((Integer.parseInt(buffertime) >= 18)
				&& (Integer.parseInt(buffertime) < 24))
			buffertime = "вечер";

		string = str.get(k + 3);
		int ke = 0;
		dopstring = string.substring(string.indexOf("max=") + 5,
				string.indexOf("min=") - 2);
		ke = Integer.parseInt(dopstring);
		dopstring = string.substring(string.indexOf("min=") + 5,
				string.indexOf("/>") - 1);
		ke = (ke + Integer.parseInt(dopstring)) / 2;
		if (ke > 0)
			bufferall = "+" + ke + ", ";
		else
			bufferall = "" + ke + ", ";

		string = str.get(k + 1);
		dopstring = string.substring(string.indexOf("cloudiness=") + 12,
				string.indexOf("precipitation=") - 2);
		ke = Integer.parseInt(dopstring);
		if (ke == 0)
			bufferall = bufferall + "ясно" + ", ";
		else if (ke == 1)
			bufferall = bufferall + "переменная облачность" + ", ";
		else if (ke == 2)
			bufferall = bufferall + "облачно" + ", ";
		else
			bufferall = bufferall + "пасмурно" + ", ";

		dopstring = string.substring(string.indexOf("precipitation=") + 15,
				string.indexOf("rpower=") - 2);
		ke = Integer.parseInt(dopstring);
		if (ke == 10)
			bufferall = bufferall + "без осадков";
		else if (ke == 9)
			bufferall = bufferall + "н/д";
		else if (ke == 8)
			bufferall = bufferall + "гроза";
		else if ((ke == 7) || (ke == 6))
			bufferall = bufferall + "снег";
		else if (ke == 5)
			bufferall = bufferall + "ливень";
		else
			bufferall = bufferall + "дождь";
		// vtoraia chast
		string = str.get(k + 2);
		dopstring = string.substring(string.indexOf("max=") + 5,
				string.indexOf("min=") - 2);
		ke = Integer.parseInt(dopstring);
		dopstring = string.substring(string.indexOf("min=") + 5,
				string.indexOf("/>") - 1);
		ke = (ke + Integer.parseInt(dopstring)) / 2;
		buf1 = "Давление: " + ke + " мм ртст ";

		string = str.get(k + 4);
		dopstring = string.substring(string.indexOf("direction=") + 11,
				string.indexOf("/>") - 1);
		ke = Integer.parseInt(dopstring);
		if (ke == 0)
			buf2 = "" + "Ветер: " + "северный, ";
		else if (ke == 1)
			buf2 = "" + "Ветер: " + "северо-восточный, ";
		else if (ke == 2)
			buf2 = "" + "Ветер: " + "восточный, ";
		else if (ke == 3)
			buf2 = "" + "Ветер: " + "юго-восточный, ";
		else if (ke == 4)
			buf2 = "" + "Ветер: " + "южный, ";
		else if (ke == 5)
			buf2 = "" + "Ветер: " + "юго-западный, ";
		else if (ke == 6)
			buf2 = "" + "Ветер: " + "западный, ";
		else
			buf2 = "" + "Ветер: " + "северо-западный, ";
		buf2 = buf2
				+ string.substring(string.indexOf("min=") + 5,
						string.indexOf("max=") - 2)
				+ '-'
				+ string.substring(string.indexOf("max=") + 5,
						string.indexOf("direction=") - 2) + " м/с";

		string = str.get(k + 5);
		dopstring = string.substring(string.indexOf("max=") + 5,
				string.indexOf("min=") - 2);
		ke = Integer.parseInt(dopstring);
		dopstring = string.substring(string.indexOf("min=") + 5,
				string.indexOf("/>") - 1);
		ke = (ke + Integer.parseInt(dopstring)) / 2;
		buf3 = "Относительная влажность: " + ke + "%";
		int ke2, b;
		string = str.get(k + 6);
		dopstring = string.substring(string.indexOf("max=") + 5,
				string.indexOf("/>") - 1);
		ke = Integer.parseInt(dopstring);

		dopstring = string.substring(string.indexOf("min=") + 5,
				string.indexOf("max=") - 2);

		ke2 = Integer.parseInt(dopstring);
		if (ke > ke2) {
			b = ke;
			ke = ke2;
			ke2 = b;
		}
		if (ke > 0)
			buf4 = "Комфортная температура: +" + ke;
		else
			buf4 = "Комфортная температура: " + ke;
		if (ke > 0)
			buf4 = buf4 + "...+" + ke2;
		else
			buf4 = buf4 + "..." + ke2;
		str.clear();
		str.add(bufferdata);
		str.add(buffertime);
		str.add(bufferall);
		str.add(buf1);
		str.add(buf2);
		str.add(buf3);
		str.add(buf4);
		return str;
	}

	public void parserLow(ArrayList<String> str, ArrayList<DataToList> data) {
		int k = 4;
		String string, bufferdata, buffertime, bufferall, dopstring;
		for (int i = 0; i < 4; i++) {
			string = str.get(k);

			bufferdata = string.substring(string.indexOf("day=") + 5,
					string.indexOf("month=") - 2)
					+ '.'
					+ string.substring(string.indexOf("month=") + 7,
							string.indexOf("year=") - 2)
					+ '.'
					+ string.substring(string.indexOf("year=") + 6,
							string.indexOf("hour=") - 2);

			buffertime = string.substring(string.indexOf("hour=") + 6,
					string.indexOf("tod=") - 2);

			if ((Integer.parseInt(buffertime) >= 0)
					&& (Integer.parseInt(buffertime) < 6))
				buffertime = "ночь";
			else if ((Integer.parseInt(buffertime) >= 6)
					&& (Integer.parseInt(buffertime) < 12))
				buffertime = "утро";
			else if ((Integer.parseInt(buffertime) >= 12)
					&& (Integer.parseInt(buffertime) < 18))
				buffertime = "день";
			else if ((Integer.parseInt(buffertime) >= 18)
					&& (Integer.parseInt(buffertime) < 24))
				buffertime = "вечер";

			string = str.get(k + 3);
			int ke = 0;
			dopstring = string.substring(string.indexOf("max=") + 5,
					string.indexOf("min=") - 2);
			ke = Integer.parseInt(dopstring);
			dopstring = string.substring(string.indexOf("min=") + 5,
					string.indexOf("/>") - 1);
			ke = (ke + Integer.parseInt(dopstring)) / 2;
			if (ke > 0)
				bufferall = "+" + ke + ", ";
			else
				bufferall = "" + ke + ", ";

			string = str.get(k + 1);
			dopstring = string.substring(string.indexOf("cloudiness=") + 12,
					string.indexOf("precipitation=") - 2);
			ke = Integer.parseInt(dopstring);
			if (ke == 0)
				bufferall = bufferall + "ясно" + ", ";
			else if (ke == 1)
				bufferall = bufferall + "переменная облачность" + ", ";
			else if (ke == 2)
				bufferall = bufferall + "облачно" + ", ";
			else
				bufferall = bufferall + "пасмурно" + ", ";

			dopstring = string.substring(string.indexOf("precipitation=") + 15,
					string.indexOf("rpower=") - 2);
			ke = Integer.parseInt(dopstring);
			if (ke == 10)
				bufferall = bufferall + "без осадков";
			else if (ke == 9)
				bufferall = bufferall + "н/д";
			else if (ke == 8)
				bufferall = bufferall + "гроза";
			else if ((ke == 7) || (ke == 6))
				bufferall = bufferall + "снег";
			else if (ke == 5)
				bufferall = bufferall + "ливень";
			else
				bufferall = bufferall + "дождь";

			data.add(new DataToList(bufferdata, buffertime, bufferall));
			k = k + 8;
		}
	}
}
