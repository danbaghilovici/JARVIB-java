package model.telegram.available_methods;


import http.QueryAttribute;

public class TelegramBaseMethod {
    private final String methodName;
    private final QueryAttribute[] parameters;


    public TelegramBaseMethod(String methodName, QueryAttribute[] methodParams) {
        this.methodName = methodName;
        this.parameters = methodParams;
    }

    public QueryAttribute updateFieldValue(String fieldName,Object newFieldValue){
        if (parameters ==null){
            return null;
        }
        int index= getIndexOf(fieldName);
        if (index>=0){
            parameters[index].setValue(newFieldValue);
            return parameters[index];
        }else{
            return null;
        }
    }

    public QueryAttribute updateFieldValue(int fieldIndex,Object newFieldValue){
        if (parameters ==null){
            return null;
        }
        parameters[fieldIndex].setValue(newFieldValue);
        return parameters[fieldIndex];
    }

    private int getIndexOf(String fieldName){
        if (parameters ==null){
        return -1;
    }
        for (int i = 0; i< parameters.length; i++){
            if (parameters[i].getName().equals(fieldName)){
                return i;
            }
        }
        return -1;
    }

    public String getFieldName(int index){
        return parameters[index].getName();
    }

    public String getFieldValue(int index){
        return parameters[index].getValue().toString();
    }

    public String getFieldName(String fieldName){
        int index=getIndexOf(fieldName);
        return index>=0? parameters[index].getName():null;
    }

    public String buildQuery(){
        String methodQuery="/"+ methodName;
        if (parameters ==null){
            return methodQuery;
        }
        for (int i = 0; i< parameters.length; i++){
            if (parameters[i].getValue()!=null){
                if (i==0){
                    methodQuery+="?";
                }
                methodQuery+= parameters[i].getName()+"="+ parameters[i].getValue()+"&";
            }
            if (i== parameters.length-1){
                methodQuery=methodQuery.substring(0,methodQuery.length()-1);
            }
        }
        return methodQuery;
    }
}
