package awd.mis.appstore.tools;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;


/**
 * 执行条件
 */
public class Term implements Cloneable {
	
	protected static final String DATE_FORMAT = "yyyy-MM-dd";
	
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * 字段
     */
    private String column;

    /**
     * 值
     */
    private Object value;

    /**
     * 链接类型
     */
    private Type type = Type.and;

    /**
     * 条件类型
     */
    private String termType = TermType.eq;

    /**
     * 嵌套的条件
     */
    private List<Term> terms = new LinkedList<>();

    
    public static boolean isDateString(String datevalue, String dateFormat) {
        if (!StringUtils.isEmpty(datevalue)) {
            return false;
        }
        try {
            SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
            java.util.Date dd = fmt.parse(datevalue);
            if (datevalue.equals(fmt.format(dd))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Term or(String term, Object value) {
        return or(term, TermType.eq,value);
    }

    public Term and(String term, Object value) {
        return and(term, TermType.eq,value);
    }

    public Term or(String term, String termType, Object value) {
        Term queryTerm = new Term();
        queryTerm.setTermType(termType);
        queryTerm.setColumn(term);
        queryTerm.setValue(getValue(value));
        queryTerm.setType(Type.or);
        terms.add(queryTerm);
        return this;
    }
    
    private Object getValue(Object value) {    	
		String valuestr=StringUtils.isEmpty(value)?"":value.toString();
		if(isDateString(valuestr,DATE_FORMAT)||isDateString(valuestr,DATE_TIME_FORMAT)) {
			String formate="";
			if(valuestr.length()==10) {
				formate=DATE_FORMAT;
			}
			if(valuestr.length()==19) {
				formate=DATE_TIME_FORMAT;
			}
			try {
				DateTimeFormatter e = DateTimeFormat.forPattern(formate);
				return e.parseDateTime(valuestr).toDate();
			} catch (Exception arg2) {
				return null;
			}
			 
		}else {
			return value;
		}
	}

	public Term or(String term, String termType, Object[] value) {
    	for(int i=0;i<value.length;i++) {
    		Term queryTerm = new Term();
    		if("like".equals(termType) || "nlike".equals(termType)) {
                queryTerm.setValue("%"+value[i]+"%");
            }else {
            	queryTerm.setValue(getValue(value[i]));
            }
            queryTerm.setTermType(termType);
            queryTerm.setColumn(term);
            queryTerm.setType(Type.or);
            terms.add(queryTerm);
    	}
        return this;
    }

    public Term and(String term, String termType, Object value) {
        Term queryTerm = new Term();
        queryTerm.setTermType(termType);
        queryTerm.setColumn(term);
        queryTerm.setValue(getValue(value));
        queryTerm.setType(Type.and);
        terms.add(queryTerm);
        return this;
    }
    
    public Term and(String term, String termType, Object[] value) {
//    	System.out.println("========================");
    	if("btw".equals(termType)||"nbtm".equals(termType)) {
    		Object[] values=new Object[2];
    		for(int i=0;i<value.length;i++) {
    			if(value.length==1) {
    				values[0]=getValue(value[0]);
    				values[1]=getValue(value[0]);
    			}else {
    				values[i]=getValue(value[i]);
    			}
    		}
    		Term queryTerm = new Term();
    		queryTerm.setTermType(termType);
            queryTerm.setColumn(term);
            queryTerm.setType(Type.and);
            queryTerm.setValue(values);            
            terms.add(queryTerm);
    	}else {
    		if(value.length>1) {
    			for(int i=0;i<value.length;i++) {
            		Term queryTerm = new Term();
            		queryTerm.setTermType(termType);
                    queryTerm.setColumn(term);
                    queryTerm.setType(Type.or);
            		if("like".equals(termType) || "nlike".equals(termType)) {
                        queryTerm.setValue("%"+value[i]+"%");
                    }else if ("rightlike".equals(termType)){
                    	queryTerm.setTermType(TermType.like);
                    	queryTerm.setValue(value[i]+"%");
                    }else {
                    	queryTerm.setValue(getValue(value[i]));
                    }
                    terms.add(queryTerm);                
            	}
    		}else {
    			for(int i=0;i<value.length;i++) {
            		Term queryTerm = new Term();
            		queryTerm.setTermType(termType);
                    queryTerm.setColumn(term);
                    queryTerm.setType(Type.and);
            		if("like".equals(termType) || "nlike".equals(termType)) {
                        queryTerm.setValue("%"+value[i]+"%");
                    }else if ("rightlike".equals(termType)){
                    	queryTerm.setTermType(TermType.like);
                    	queryTerm.setValue(value[i]+"%");
                    }else {
                    	queryTerm.setValue(getValue(value[i]));
                    }
                    terms.add(queryTerm);                
            	}
    		}
    		
    	}
    	
        return this;
    }

    public Term nest() {
        return nest(null, null);
    }

    public Term orNest() {
        return orNest(null, null);
    }

    public Term nest(String term, Object value) {
        Term queryTerm = new Term();
        queryTerm.setType(Type.and);
        queryTerm.setColumn(term);
        queryTerm.setValue(getValue(value));
        terms.add(queryTerm);
        return queryTerm;
    }

    public Term orNest(String term, Object value) {
        Term queryTerm = new Term();
        queryTerm.setType(Type.or);
        queryTerm.setColumn(term);
        queryTerm.setValue(getValue(value));
        terms.add(queryTerm);
        return queryTerm;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        if (column == null) return;
        if (column.contains("$")) {
            String tmp[] = column.split("[$]");
            setTermType(tmp[1]);
            column = tmp[0];
        }
        this.column = column;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTermType() {
        return termType.toLowerCase();
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public Term addTerm(Term term) {
        terms.add(term);
        return this;
    }

    @Override
    public Term clone() {
        Term term = new Term();
        term.setColumn(column);
        term.setValue(getValue(value));
        term.setTermType(termType);
        term.setType(type);
        terms.forEach(t -> term.addTerm(t.clone()));
        return term;
    }

    public enum Type {
        or, and;

        public static Type fromString(String str) {
            try {
                return Type.valueOf(str.toLowerCase());
            } catch (Exception e) {
                return and;
            }
        }
    }


}
