function getTipReducereText(tipReducere) {

	var strTipReducere;

	if (tipReducere == 1)
		strTipReducere = '1 factura (reducere in pret)';
	else if (tipReducere == 2)
		strTipReducere = '2 facturi';
	else if (tipReducere == 3)
		strTipReducere = '1 factura (reducere separat)';

	return strTipReducere;

}

function getDocInsotitorText(tipDocument) {

	var strTipDocument;

	if (tipDocument == 1)
		strTipDocument = 'Factura';
	else if (tipDocument == 2)
		strTipDocument = 'Aviz de expeditie';

	return strTipDocument;

}

function getTipPlataText(tipPlata) {

	var strTipPlata;

	if (tipPlata == 'B')
		strTipPlata = 'Bilet la ordin';
	else if (tipPlata == 'C')
		strTipPlata = 'Cec';
	else if (tipPlata == 'E')
		strTipPlata = 'Plata in numerar';
	else if (tipPlata == 'O')
		strTipPlata = 'Ordin de plata';

	return strTipPlata;

}

function getRespIncasareText(tipResp) {

	var strTipResp;

	if (tipResp == 'AV')
		strTipResp = 'Agent vanzari';
	else if (tipResp == 'SO')
		strTipResp = 'Sofer';
	else if (tipResp == 'OF')
		strTipResp = 'Operator';

	return strTipResp;

}

function getTipTranspText(tipTransp) {

	var strTipTransp;

	if (tipTransp == 'TRAP')
		strTipTransp = 'Arabesque';
	else if (tipTransp == 'TCLI')
		strTipTransp = 'Client';
	else if (tipTransp == 'TFRN')
		strTipTransp = 'Furnizor';

	return strTipTransp;

}

function getStareComandaText(idStareComanda) {
	var textStare;

	if (idStareComanda == 1) {
		textStare = 'Comanda in curs de aprobare';
	} else if (idStareComanda == 4) {
		textStare = 'Comanda supusa unor conditii';
	}

	return textStare;

}

function getTipAngajat(tipAng) {

	var strTipResp = 'Nedefinit';

	if (tipAng == 'AV')
		strTipResp = 'Agent vanzari';
	else if (tipAng == 'SD')
		strTipResp = 'Sef departament';
	else if (tipAng == 'GS')
		strTipResp = 'Gestionar sef';
	else if (tipAng == 'DZ')
		strTipResp = 'Director zona';
	else if (tipAng == 'DADMIN')
		strTipResp = 'Director administrativ';
	else if (tipAng == 'GD')
		strTipResp = 'Gestionar';	

	return strTipResp;

}
