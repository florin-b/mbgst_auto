package mobigest.queries.articole;

public class SqlQueries {

	public static String getMasiniNeincarcate_old() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select masina,numarb from ( ");
		sqlString.append(" select b.masina,b.numarb, ROW_NUMBER() OVER(PARTITION BY b.masina,b.numarb order by masina,data_e) AS NRp ");
		sqlString.append(" from websap.borderouri b join sapprd.vttk k on b.numarb=k.tknum ");
		sqlString.append(" where b.sttrg = 2 and k.mandt='900' AND k.tplst = :filiala and k.shtyp = '1110' ");
		sqlString.append(" and not exists(select 1 from sapprd.zsfarsitinc where document = b.numarb) ");
		sqlString.append(" order by masina,data_e) ");
		sqlString.append(" where nrp=1 ");

		return sqlString.toString();
	}
	
	
	public static String getMasiniNeincarcate(){
		StringBuilder sqlString = new StringBuilder();
		
		 sqlString.append(" select masina,numarb,numarb_ant from ( " );
		 sqlString.append(" select b.masina,b.numarb,bant.numarb as numarb_ant, ROW_NUMBER() OVER(PARTITION BY b.masina,b.numarb order by b.masina,b.data_e) AS NRp ");
		 sqlString.append(" from websap.borderouri b join sapprd.vttk k on b.numarb=k.tknum ");
		 sqlString.append(" left join websap.borderouri bant on bant.masina=b.masina and bant.sttrg>2 ");
		 sqlString.append(" where b.sttrg = 2 and k.mandt='900' AND k.tplst = :filiala and k.shtyp = '1110' ");
		 sqlString.append(" and not exists(select 1 from sapprd.zsfarsitinc where document = b.numarb) ");
		 sqlString.append(" order by b.masina,b.data_e) where nrp=1 "); 
		
		return sqlString.toString();
	}
	
	

	public static String getUnitLogAngajat() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append("select filiala from personal  where cod=? ");

		return sqlString.toString();
	}

	public static String setSfarsitIncarcare() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" insert into sapprd.zsfarsitinc(mandt, document, codsofer, data, ora) ");
		sqlString.append(" values ('900',?,?, (select to_char(sysdate,'yyyymmdd') from dual), (select to_char(sysdate,'hh24mi') from dual) )");

		return sqlString.toString();

	}

	public static String setSfarsitIncImg() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" insert into zsfarsitincfoto(document, img) ");
		sqlString.append(" values (?,?) ");

		return sqlString.toString();

	}

}
