function getTVA(infoArticol) {

	var valTVA = 0.0;
	var tokInfo = infoArticol.split(';');

	for (var i = 0; i < tokInfo.length; i++) {

		if (tokInfo[i].indexOf('MWST') != -1) {
			var tvaTok = tokInfo[i].split(':');
			valTVA = parseFloat(tvaTok[1].replace(',', '.'));

		}
	}

	return valTVA;

}

function getTVAFormula(datePret) {

	var valTVA = parseFloat(getTVA(datePret.infoArticol))
			/ parseFloat(datePret.cantitate) * parseFloat(datePret.multiplu);

	return valTVA.toFixed(2);

}

function getProcentFact(articol) {

	var procentFactNumarator = articol.pretLista / articol.cantitate
			* articol.multiplu - articol.pretUnitar;

	var procentFactNumitor = articol.pretLista / articol.cantitate
			* articol.multiplu;

	return ((procentFactNumarator / procentFactNumitor) * 100).toFixed(2);

}

function getProcentAprob(articol) {

	
	return ((1 - articol.pretUnitar
			/ (articol.pretLista / articol.cantitate * articol.multiplu)) * 100)
			.toFixed(2);

}

function getValoareArticol(articol) {
	return ((articol.pretUnitar / articol.multiplu) * articol.cantitate).toFixed(2);

}

function isCantitateValid(cantitate) {
	return $.isNumeric(cantitate);

}
