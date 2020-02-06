package mobigest.controllers;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mobigest.beans.Info;
import mobigest.beans.MasinaNeincarcata;
import mobigest.model.comenzi.OperatiiMasini;
import mobigest.utils.Utils;

@Controller
@Scope("session")
public class ComenziController {

	private static final Logger logger = LogManager.getLogger(ComenziController.class);

	@RequestMapping(value = "/getMasiniNeincarcate", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<MasinaNeincarcata> getMasiniNeincarcate(String filiala) {

		return new OperatiiMasini().getMasiniNeincarcate(filiala);

	}

	@RequestMapping(value = "/setSfarsitIncarcare", method = RequestMethod.POST)
	@ResponseBody
	public Info setSfarsitIncarcare(String document, String codSofer, String image) {

		return new OperatiiMasini().trateazaSfarsitIncarcare(document, codSofer, image);
		
		 //return new OperatiiMasini().saveSfarsitIncImg(document, image);

		// new OperatiiMasini().setSfarsitIncarcare(document, codSofer);
		
		

	}

	@RequestMapping(value = "/valideazaMasina", method = RequestMethod.POST)
	@ResponseBody
	public MasinaNeincarcata valideazaMasina(String codUser, String filiala, String image) {

		return new OperatiiMasini().valideazaMasina(codUser, filiala, image);

	}

}
